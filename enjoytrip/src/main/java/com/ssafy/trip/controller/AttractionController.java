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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.AttractionDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.RouteDto;
import com.ssafy.trip.model.RouteDto.RouteInfo;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.SidoGugunCodeDto;
import com.ssafy.trip.model.SidoInfo;
import com.ssafy.trip.model.service.AttractionService;
import com.ssafy.trip.model.service.AttractionServiceImpl;

import io.swagger.v3.oas.annotations.Parameter;
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
	
	@GetMapping("/search/{contentId}")
	public ResponseEntity<AttractionDto> searchByContentId(
			@PathVariable("contentId") String contentId
			) throws Exception {
		log.info("search/{contentId} - 호출");
		
		return new ResponseEntity<AttractionDto>(attractionService.searchByContentId(contentId), HttpStatus.OK);
	}
	
	@GetMapping("/sido_info")
	public ResponseEntity<?> sidoInfo(@RequestParam Map<String, String> params){
		if(!params.containsKey("sido_code"))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		String sidoCode = params.get("sido_code");
		log.info("sidoInfo(AttractionController): 여행지 정보 요청, 파라미터 확인");
		log.info("parameter: "+sidoCode);
		
		
		try {
			SidoInfo sinfo = attractionService.getSidoInfo(sidoCode);
			
			if(sinfo != null) {
				return new ResponseEntity<SidoInfo>(sinfo, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	
//	
//	@PostMapping("/route")
//	public ResponseEntity<Void> saveRoute(@RequestBody Map<String, Object> routeInfo) throws Exception {
//		
//		log.info("saveRoute(AttractionController) - 루트 저장 성공 확인\n routeInfo : ", routeInfo);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
////		return new ResponseEntity<List<AttractionDto>>(attractionService.searchByKeyword(keyword), HttpStatus.OK);
//	}
	

    @PostMapping("/route")
    public ResponseEntity<Void> postRouteDetail(@RequestBody RouteDto routeDto) throws Exception {
    	
    	attractionService.insertRoute(routeDto);
    	
    	int routeId = routeDto.getRoute_id();
    	
        List<RouteInfo> infoList = routeDto.getInfoList();
        
        for (RouteInfo info : infoList) {
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("routeId", routeId);
        	map.put("contentId", info.getContentId());
        	map.put("sequence", info.getSequence());
        	map.put("description", info.getDescription());
        	
        	attractionService.insertRouteDetail(map);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
    
    @GetMapping("/route")
	public ResponseEntity<?> getRouteDetail(@RequestParam("userId") String userId) throws Exception {
    	
		try {
			return new ResponseEntity<List<RouteDto>>(attractionService.getRouteDetail(userId), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
	
    @GetMapping("/route/shared")
	public ResponseEntity<?> getSharedRoute(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) throws Exception {
    	log.info("getSharedRoute(AController): 함수 호출 확인");
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("start", page*pageSize);
			map.put("pageSize", pageSize);
			
			List<RouteDto> list = attractionService.getSharedRoute(map);
			log.info(String.format("length: %d", list.size()));
			return new ResponseEntity<List<RouteDto>>(list, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }    
    
    @GetMapping("/route/delete/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable(value="routeId") int routeId) throws Exception {
    	try {
    		int res = attractionService.deleteRoute(routeId);
    		if(res != 0) {
    			return new ResponseEntity<String>("정상 삭제 완료", HttpStatus.OK);
    		}
    		else {
    			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    		}
    	}
    	catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("route/share/{routeId}")
    public ResponseEntity<?> shareRoute(@PathVariable(value="routeId") int routeId) throws Exception {
    	try {
    		int res = attractionService.shareRoute(routeId);
    		if(res != 0) {
    			return new ResponseEntity<String>("정상 공유 완료", HttpStatus.OK);
    		}
    		else {
    			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    		}
    	}
    	catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
