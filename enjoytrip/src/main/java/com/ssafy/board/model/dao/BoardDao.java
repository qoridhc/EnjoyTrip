package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;

@Mapper
public interface BoardDao {

	List<BoardDto> listArticle() throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void writeArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNO) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
}
