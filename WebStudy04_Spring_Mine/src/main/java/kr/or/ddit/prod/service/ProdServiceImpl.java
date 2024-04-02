package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

//각각의 로직이 실행될때 소요시간을 로그로 출력하라
@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	//@Inject 여기에 이 어노테이션이 없지만 위에  @RequiredArgsConstructor 이걸 설정했으므로, 생성자 인젝션을 받고있음
	private final ProdDAO dao;
	
	
//	Spring의 Value 어노테이션을 사용하여 외부에서 주입되는 속성값을 가져옴. appInfo.prodPath는 업로드된 상품 이미지가 저장될 경로를 나타냄 (해당경로에 폴더를 미리 생성해놔야함!!!!!!!!!)
	@Value("#{appInfo.prodPath}")
	private Resource prodFolder;

	private File saveFolder;
	
	@PostConstruct
	private void init() throws IOException {
		saveFolder = prodFolder.getFile();
	}
	
	/**
	 * 상품 등록이나 수정시 업로드된 상품 이미지인 이진데이터 저장
	 */
	private void processProdImage(ProdVO prod) {
		MultipartFile prodImage = prod.getProdImage();
		if(prodImage == null) {
			return;
		}
		
//트랜젝션 테스트를 위해 강제로 예외를 발생시키는 코드
//		if(1==1) {
//			throw new RuntimeException("강제 발생 예외");
//		}
		try {
//			Resource imageFile = prodFolder.createRelative(prod.getProdImg());//UUID로 만들어진 상대경로가 반환됨  => 이 객체를 이용하면, 시간지연이 발생해 사진이 한번에 로딩되지 않음.
			File imageFile = new File(saveFolder, prod.getProdImg());
			prodImage.transferTo(imageFile);	//파일 저장, 파일의 메타데이터를 분리한다. // 실제 파일 데이터를 생성한 UUID로 폴더 경로에 저장함
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} 
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = dao.insertProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;	//문자기반 데이터 DB에 저장
		if(result == ServiceResult.OK)
			processProdImage(prod);	//이진데이터 DB에 저장
		return result;
		//원자성 : 더이상 쪼갤수없는 가장 작은 단위. 위의 문자기반의 데이터와 이진데이터 각 2개가 원자임 => 이게 1개의 최소 트랜잭션단위임. 그래서 둘다 작업에 성공해야 1개의 트랜잭션이 성공한것임. 더이상 각각이 0.5로 쪼개질 수 없음.
	}

	@Override
	public List<ProdVO> retrieveProdList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectProdList(paging);
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(String.format("%s 에 해당하는 상품 없음.", prodId));
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = dao.updateProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;	////문자기반 데이터 DB에 저장
		if(result == ServiceResult.OK) {
			processProdImage(prod);//이진데이터 DB에 저장
		}
		return result;
	}

}

/*
 	사용자는 출금, 입금, 이체, 공과급납부를 하려고 한다.
 	이 4개의 업무는 모두  인증이라는 반복되는 로직이 실행된다.
 	마찬가지로, 모든 업무가 실행될때 각 4개의 업무는 사용 시점등이 시스템에 로그로 기록된다.
 	
 	부가관심사 : advice(어드바이스는 필수적으로 포인트것과(=설정) 세트가 돼야함. 이 한 세트가 aspect임.)
 	코어관심사 : target
 	위빙 : 엮다, 맞물려 작동하다, 결합되어 작동한다. 
 	aop : 분리된 관심사에 의해서, 타겟과 어드바이스가 분리가 된후, 런타임시 맞물려서 실행되는 구조
 	어드바이저 : 프레임워크가 제공해주는 어드바이스
 	
 	
 	
 	createProd()와 modifyProd()를 보면, 문자기반데이터와 이진데이터기반의 데이터가 둘다 성공돼야 한 메서드의 트랜젝션을 성공했다함. => 트랜젝션 관리가 필요함.
 	이진데이터 로직을 성공하고, 문자기반데이터를 실패했으면 이미 저장된 이진데이터를 롤백하고 아예 둘다 저장 안되고 등록이 안되버림. => 트랜잭션 관리가 정상적으로 되고 있음.
 	
 	
 	
 	*** 상품 등록과 상품 수정시 트랜잭션 관리기능이 중복되는 문제 ***
 		Core(핵심관심사) : 상품등록 & 상품수정
 		Cross-cutting(부가관심사) : 트랜잭션 관리기능
 		
 		Target : createProd, modifyProd
 				(타겟은 대부분 비즈니스로직이 됨. 때때로 DAO가 됨 => 트랜젝션은 컨트롤러를 대상으로 관리하지 않는다!) 
 				(retrieveProd, retrieveProdList도 타겟이 되긴 하지만 조회로직이니까 트랜젝션관리할 필요는없으므로 앞의 타겟과 위빙하진 않는다.)
 		Advice : 트랜잭션 관리 advice 구현(직접 구현할것이냐) or advisor(이미 구현돼있는 프레임워크를 쓸것이냐 - Spring은 이미 어드바이저를 갖고있음)
 		어떤 어드바이스와 어떤 타겟을 위빙할것인가?
 		이 위빙을 누가 해준는가? AspectJ Weaver
 		타겟은 상위컨테이너에서 쓰이므로 위빙하기위한 설정파일은 상위컨테이너에 위치해야함.
 		
 		Pointcut 작성(createProd, modifyProd)
 		==> aspect
 		
 		
 	*** 모든 로직의 실행 소요시간을 시스템 로그로 기록하라. ***
 	Core : 모든 로직은 MemberService, ProdService, BuyerService등이 됨...
 	Cross-cutting : 실행 소요시간을 시스템 로그로 기록하라
 	
 	=> Weaver가 있다면, Aop방법론으로 프로그래밍 할 수 있다.
 	AOP방법론으로 트랜젝션을 관리한다.
 */











