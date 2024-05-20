package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.RouteDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.SidoInfo;
import com.ssafy.trip.model.RouteDto.RouteInfo;

public interface AttractionService
{
	public List<SidoDto> getSido() throws Exception;
	public List<GugunDto> getGugun(String sido_code) throws Exception;
	public List<AttractionDto> getAttractionList(String content_id, String sido_code, String gugun_code) throws Exception;	
	public List<AttractionDto> searchByKeyword(String keyword) throws Exception;
	public SidoInfo getSidoInfo(String sidoCode) throws Exception;
	public int insertRoute(RouteDto routeDto) throws Exception;
	public void insertRouteDetail(Map<String, Object> routeInfo)throws Exception;
	public List<RouteDto> getRouteDetail(String userId)throws Exception;
}
