package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping(value={"/{boardType}/board/insert", "/{boardType}/board/{boParent}/child"})
public class BoardInsertController {
	
	public static final String MODELNAME = "newBoard";
	
	@Inject
	private BoardService service;
	
	@ModelAttribute(MODELNAME)
	public BoardVO board() {
		return new BoardVO();
	}
	
	@GetMapping
	public String form() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String insert(@ModelAttribute(MODELNAME) BoardVO cmdObj) {
		service.createBoard(cmdObj);
		return "redirect:/{boardType}/board/"+cmdObj.getBoNo();
	}
}















