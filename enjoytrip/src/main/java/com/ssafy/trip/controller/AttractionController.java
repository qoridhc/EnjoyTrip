package com.ssafy.trip.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.service.AttractionServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/attraction")
public class AttractionController 
{
	private static AttractionController acon;
	private AttractionServiceImpl service;
	
	public static AttractionController getCont() {
		if(acon == null) {
			acon = new AttractionController();
		}
		return acon;
	}

	public void getSido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			List<SidoDto> list = service.getSido();
			request.setAttribute("sidoList", list);
			request.getRequestDispatcher("/attractionFinder.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"error.jsp");
		}
        System.out.println("doGet: getSido clear");
	}
	
	public void getGugun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			List<GugunDto> list = service.getGugun(request.getParameter("sido-code"));
			//list를 json 문자열로 변환
			String json = "{\"code\":\"200\", \"msg\":\"success\", \"body\": [";
			for(int i = 0; i < list.size()-1; i++)
			{
				json += String.format("{\"%d\":\"%s\"}, ", list.get(i).getGugun_code(), list.get(i).getGugun_name());
			}
			json += String.format("{\"%d\":\"%s\"}]}", list.get(list.size()-1).getGugun_code(), list.get(list.size()-1).getGugun_name());
			
			request.setAttribute("str", json);
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
        System.out.println("doGet: getGugun clear");
	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			System.out.println("start: search");
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
//			String keyword = request.getParameter("keyword");
			List<AttractionDto> list = service.getAttractionList(sido, gugun, "12");
			
			String json = "{\"code\":\"200\", \"msg\":\"success\", \"body\": [";
			for(int i = 0; i < list.size()-1; i++)
			{
				AttractionDto dto = list.get(i);
				json += String.format("{\"title\":\"%s\", \"firstimage\":\"%s\", \"addr1\":\"%s\", \"mapx\":\"%s\", \"mapy\":\"%s\"}, ",
						dto.getTitle(), dto.getFirst_image(), dto.getAddr1(),dto.getLongitude(),  dto.getLatitude());
			}
			AttractionDto dto = list.get(list.size()-1);
			json += String.format("{\"title\":\"%s\", \"firstimage\":\"%s\", \"addr1\":\"%s\", \"mapx\":\"%s\", \"mapy\":\"%s\"} ]}",
					dto.getTitle(), dto.getFirst_image(), dto.getAddr1(), dto.getLongitude(), dto.getLatitude());
			
			request.setAttribute("str", json);
			System.out.println("end: search");
			request.getRequestDispatcher("/result.jsp").forward(request, response);
	        
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
	}
	
	
	
	//나만의 여행 경로를 먼저 받아오기
}
