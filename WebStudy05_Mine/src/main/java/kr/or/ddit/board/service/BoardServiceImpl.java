package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.exception.BoardException;
import kr.or.ddit.board.exception.WriterAuthenticationException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.mybatis.mappers.BoardMapper;
import kr.or.ddit.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardMapper mapper;
	
	@Override
	public void createBoard(BoardVO board) {
		mapper.insert(board);
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		BoardVO board = mapper.select(boNo);
		if(board==null)
			throw new BoardException(String.format("%d 번 글 없음.", boNo));
		return board;
	}

	@Override
	public List<BoardVO> retrieveBoardList(PaginationInfo paging) {
		int totalRecord = mapper.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return mapper.selectList(paging);
	}

	@Override
	public BoardVO writerAuthenticate(BoardVO target) throws WriterAuthenticationException {
		BoardVO savedBoard = mapper.select(target.getBoNo());
		if(savedBoard==null)
			throw new BoardException(String.format("%d 번 글 없음.", target.getBoNo()));
		String savedPass = savedBoard.getBoPass();
		String inputPass = target.getBoPass();
		if(!savedPass.equals(inputPass))
			throw new WriterAuthenticationException(target.getBoNo());
		return savedBoard;
	}

	@Override
	public void modifyBoard(BoardVO board) {
		writerAuthenticate(board);
		mapper.update(board);
	}

	@Override
	public void removeBoard(BoardVO board) {
		writerAuthenticate(board);
		mapper.delete(board.getBoNo());
	}

}



















