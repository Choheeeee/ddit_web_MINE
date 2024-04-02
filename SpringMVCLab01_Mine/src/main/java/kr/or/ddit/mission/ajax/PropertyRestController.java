package kr.or.ddit.mission.ajax;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.mission.vo.PropertyVO;

/**
 * REST URI를 먼저 설계하기 (BASE URI가 다 똑같음 => class의 RequestMapping에 선언해 놓기)
 * 자원은 명사, 메서드는 동사로 표현
 * REST API는 서버와 클라이언트가 데이터를 JSON / XML으로 받는다.
 * 명사(자원의 식별자) : /mission/ajax/property
 *	/mission/ajax/property(GET) : 전체 조회
 *	/mission/ajax/property/a001(GET) 한건 조회 <=> /mission/ajax/property?what=a001 (자원의 식별자를 파라미터로 보내냐, 경로변수 형태로 보내냐의 차이)
 *	/mission/ajax/property/a001(PUT) : 한건 수정
 *	/mission/ajax/property/a001(DELETE) : 한건 삭제
 *	/mission/ajax/property/a001(POST) : 신규 등록
 **/

//@Controller
@RestController //모든 핸들러들마다 @ResponseBody가 중복되므로, @RestController를 통해 클래스 전체에 컨트롤러 어노테이션을 설정함 => 빈 등록

//클라이언트에서 보낸 요청을 서버사이드에선 처리 즉, 소비해야한다. 따라서 이 consume 키워드는 클라이언트에서 보낸 요청의 타입을 의미함.
@RequestMapping(value = "/mission/ajax/property", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyRestController {

	@GetMapping
//	@ResponseBody //응답바디에 자바객체인 Collection이 아닌 마샬링해서 JSON객체로 바로 내보낸다는 의미)
	public Collection<PropertyVO> getAll() { 
		return null;
	}
	
	@GetMapping("{propertyName}")
//	@ResponseBody
	public PropertyVO getOne(@PathVariable String propertyName){
		return null;
	}
	
	
	@PostMapping
//	@ResponseBody 
	public Map<String, Object> insert(@RequestBody PropertyVO newProp){ //@RequestBody : 클라이언트로부터 오는 Post요청 바디에 JSON객체인 PropertyVO를 담고있다는 의미
		//성공, 실패메서드 등을 모두 포함할 수 있는 자료구조가 필요하므로, 반환타입은 Map이 됨.
		return null;
	}
	
	@PutMapping("{propertyName}")
//	@ResponseBody
	public Map<String, Object> update(@RequestBody PropertyVO newProp){
		return null;
	}
	
	
	@DeleteMapping("{propertyName}")
//	@ResponseBody
	public Map<String, Object> delete(@PathVariable String propertyName){
		return null;
	}
	
	
	
}
