package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.RouteDto;
import com.ssafy.trip.model.RouteDto.RouteInfo;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.SidoInfo;
import com.ssafy.trip.model.dao.AttractionDao;

@Service
public class AttractionServiceImpl implements AttractionService
{
	private static AttractionServiceImpl service;
	private AttractionDao dao;
	
	@Autowired
	private AttractionServiceImpl(AttractionDao dao) {
		this.dao = dao;
	}

	@Override
	public List<SidoDto> getSido() throws Exception {
		return dao.getSido();
	}

	@Override
	public List<GugunDto> getGugun(String sido_code) throws Exception {
		return dao.getGugun(sido_code);
	}

	@Override
	public List<AttractionDto> getAttractionList(String content_id, String sido_code, String gugun_code) throws Exception {
		return dao.getAttractionList(content_id, sido_code, gugun_code);
	}

	@Override
	public List<AttractionDto> searchByKeyword(String keyword) throws Exception {
		return dao.searchByKeyword(keyword);
	}

	@Override
	public SidoInfo getSidoInfo(String sidoCode) throws Exception {
		return dao.getSidoInfo(sidoCode);
	}
	public int insertRoute(RouteDto routeDto) throws Exception {
		return dao.insertRoute(routeDto);
	}
	@Override
	public void insertRouteDetail(Map<String, Object> routeInfo) throws Exception {
		dao.insertRouteDetail(routeInfo);
	}

	@Override
	public List<RouteDto> getRouteDetail(String userId) throws Exception {
		return dao.getRouteDetail(userId);
	}

	@Override
	public AttractionDto searchByContentId(String contentId) throws Exception {
		return dao.searchByContentId(contentId);
	}

	@Override
	public List<RouteDto> getSharedRoute(Map<String, Integer> map) throws Exception {
		return dao.getSharedRoute(map);
	}

	@Override
	public int deleteRoute(int routeId) throws Exception {
		return dao.deleteRoute(routeId);
	}
}
