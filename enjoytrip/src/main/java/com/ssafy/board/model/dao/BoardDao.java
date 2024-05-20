package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.MemoDto;

@Mapper
public interface BoardDao {

	List<BoardDto> listArticle() throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void writeArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNO) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> searchArticle(Map<String, String> params) throws SQLException;
	void updateArticleHit(BoardDto boardDto) throws SQLException;
	List<MemoDto> getMemoList(int articleNo) throws SQLException;
	int writeMemo(Map<String, String> params) throws SQLException;
	int deleteMemo(int memoNo) throws SQLException;
	int modifyMemo(Map<String, String> params) throws SQLException;
}
