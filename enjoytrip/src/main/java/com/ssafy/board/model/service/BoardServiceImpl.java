package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	@Autowired
	private BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao; 
	}
	
	@Override
	public List<BoardDto> listArticle() throws Exception {
		return boardDao.listArticle();
	}
	
	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
	}
	
	@Override
	public void deleteArticle(int articleNo) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		boardDao.deleteArticle(articleNo);
	}
	
	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public List<BoardDto> searchArticle(Map<String, String> params) throws Exception {
		return boardDao.searchArticle(params);
	}
}
