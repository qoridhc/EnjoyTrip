package com.ssafy.trip.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.RouteDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.SidoInfo;
import com.ssafy.trip.model.RouteDto.RouteInfo;

@Mapper
public interface AttractionDao
{
	public List<SidoDto> getSido() throws SQLException;
	public List<GugunDto> getGugun(String sido_code) throws SQLException;
	public List<AttractionDto> getAttractionList(String content_id, String sido_code, String gugun_code) throws SQLException;
	public List<AttractionDto> searchByKeyword(Map<String, String> params) throws SQLException;
	public SidoInfo getSidoInfo(String sidoCode) throws SQLException;
	public int insertRoute(RouteDto routeDto) throws SQLException;
	public void insertRouteDetail(Map<String, Object> routeInfo)throws SQLException;
	public List<RouteDto> getRouteDetail(String userId)throws SQLException;
	public AttractionDto searchByContentId(String contentId) throws SQLException;
	public List<RouteDto> getSharedRoute(Map<String, Integer> map) throws SQLException;
	public int deleteRoute(int routeId) throws SQLException;
	public int shareRoute(int routeId) throws SQLException;
	public List<AttractionDto> searchBySidoCode(String sido_code) throws SQLException;
	public int stopShareRoute(int routeId) throws SQLException;
}
