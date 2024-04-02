package kr.or.ddit.buyer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Inject
	private BuyerDAO dao;

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = dao.selectBuyer(buyerId);
		if(buyer==null)
			throw new PKNotFoundException(String.format("%s 제조사 없음.", buyerId));
		return buyer;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectBuyerList(paging);
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		return dao.insertBuyer(buyer) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		return dao.updateBuyer(buyer) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
