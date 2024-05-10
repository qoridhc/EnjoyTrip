package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.BoardDto;

public interface BoardService {

	List<BoardDto> listArticle() throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void writeArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
}
