package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;

public interface AttractionService
{
	public List<SidoDto> getSido() throws Exception;
	public List<GugunDto> getGugun(String sido_code) throws Exception;
	public List<AttractionDto> getAttractionList(String content_id, String sido_code, String gugun_code) throws Exception;	
	public List<AttractionDto> searchByKeyword(String keyword) throws Exception;
	
}
