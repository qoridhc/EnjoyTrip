package com.ssafy.trip.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.SidoGugunCodeDto;
import com.ssafy.trip.model.service.AttractionService;
import com.ssafy.trip.model.service.AttractionServiceImpl;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attraction")
@Slf4j
public class AttractionController 
{
	private static AttractionController acon;
	private AttractionService attractionService;
	
	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
//	public static AttractionController getCont() {
//		if(acon == null) {
//			acon = new AttractionController();
//		}
//		return acon;
//	}


	@GetMapping("/sido")
	public ResponseEntity<List<SidoDto>> sido() throws Exception {
		log.info("sido - 호출");
		
		return new ResponseEntity<List<SidoDto>>(attractionService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<GugunDto>> gugun(
			@RequestParam("sido") @Parameter(description = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<GugunDto>>(attractionService.getGugun(sido), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<AttractionDto>> getList(
			@RequestParam("contentId") String contentId,
			@RequestParam("sido") String sido,
			@RequestParam("gugun") String gugun
			) throws Exception {
		log.info("list - 호출");
		return new ResponseEntity<List<AttractionDto>>(attractionService.getAttractionList(contentId, sido, gugun), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<AttractionDto>> searchByKeyword(
			@RequestParam("keyword") String keyword
			) throws Exception {
		log.info("search - 호출");
		
		return new ResponseEntity<List<AttractionDto>>(attractionService.searchByKeyword(keyword), HttpStatus.OK);
	}
	
	
	@PostMapping("/route")
	public ResponseEntity<Void> saveRoute(@RequestParam("routeInfo") String routeInfo) throws Exception {
		
		log.info("saveRoute(AttractionController) - 루트 저장 성공 확인\n routeInfo : ", routeInfo);
		
		return new ResponseEntity<>(HttpStatus.OK);
//		return new ResponseEntity<List<AttractionDto>>(attractionService.searchByKeyword(keyword), HttpStatus.OK);
	}
	
	@PostMapping("/api/example")
    public ResponseEntity<Void>  handlePostRequest(@RequestBody Map<String, Object> payload) {
        
		// 요청 파라미터 출력
        System.out.println("Received payload: " + payload);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
//	
//	public void getGugun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//			List<GugunDto> list = service.getGugun(request.getParameter("sido-code"));
//			//list를 json 문자열로 변환
//			String json = "{\"code\":\"200\", \"msg\":\"success\", \"body\": [";
//			for(int i = 0; i < list.size()-1; i++)
//			{
//				json += String.format("{\"%d\":\"%s\"}, ", list.get(i).getGugun_code(), list.get(i).getGugun_name());
//			}
//			json += String.format("{\"%d\":\"%s\"}]}", list.get(list.size()-1).getGugun_code(), list.get(list.size()-1).getGugun_name());
//			
//			request.setAttribute("str", json);
//			request.getRequestDispatcher("/result.jsp").forward(request, response);
//		} catch (Exception e) {
//			response.sendRedirect(request.getContextPath()+"/error.jsp");
//		}
//        System.out.println("doGet: getGugun clear");
//	}
//
//	public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		try {
//			System.out.println("start: search");
//			String sido = request.getParameter("sido");
//			String gugun = request.getParameter("gugun");
////			String keyword = request.getParameter("keyword");
//			List<AttractionDto> list = service.getAttractionList(sido, gugun, "12");
//			
//			String json = "{\"code\":\"200\", \"msg\":\"success\", \"body\": [";
//			for(int i = 0; i < list.size()-1; i++)
//			{
//				AttractionDto dto = list.get(i);
//				json += String.format("{\"title\":\"%s\", \"firstimage\":\"%s\", \"addr1\":\"%s\", \"mapx\":\"%s\", \"mapy\":\"%s\"}, ",
//						dto.getTitle(), dto.getFirst_image(), dto.getAddr1(),dto.getLongitude(),  dto.getLatitude());
//			}
//			AttractionDto dto = list.get(list.size()-1);
//			json += String.format("{\"title\":\"%s\", \"firstimage\":\"%s\", \"addr1\":\"%s\", \"mapx\":\"%s\", \"mapy\":\"%s\"} ]}",
//					dto.getTitle(), dto.getFirst_image(), dto.getAddr1(), dto.getLongitude(), dto.getLatitude());
//			
//			request.setAttribute("str", json);
//			System.out.println("end: search");
//			request.getRequestDispatcher("/result.jsp").forward(request, response);
//	        
//		} catch (Exception e) {
//			response.sendRedirect(request.getContextPath()+"/error.jsp");
//		}
//	}
//	
	
	
	//나만의 여행 경로를 먼저 받아오기
}
