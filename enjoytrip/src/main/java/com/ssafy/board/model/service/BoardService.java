package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.MemoDto;

public interface BoardService {

	List<BoardDto> listArticle() throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void writeArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> searchArticle(Map<String, String> params) throws Exception;
	void updateArticleHit(BoardDto boardDto) throws Exception;
	List<MemoDto> getMemoList(int articleNo) throws Exception;
}
