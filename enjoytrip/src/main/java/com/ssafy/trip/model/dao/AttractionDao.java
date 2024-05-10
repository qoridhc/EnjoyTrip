package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;

public interface AttractionDao
{
	public List<SidoDto> getSido() throws SQLException;
	public List<GugunDto> getGugun(String sido_code) throws SQLException;
	public List<AttractionDto> getAttractionList(String sido, String gugun, String keyword) throws SQLException;
}
