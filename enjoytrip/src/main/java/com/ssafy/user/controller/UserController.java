package com.ssafy.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.service.BoardService;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.util.JWTUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	

	private UserService userService;
	private final JWTUtil jwtUtil;

	private UserController(UserService userService, JWTUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

//	===== 1. 로그인 (R) =====
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(UserDto userDto) {

		System.out.println("login user : {}" + userDto.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			System.out.println(userDto.getId());
			
			UserDto targetUser = userService.login(userDto.getId());
			
			System.out.println("targetUser : " + targetUser);
			System.out.println("userDto : " + userDto);
			if(targetUser != null) {
				
				if (!targetUser.getPw().equals(userDto.getPw())) {
					status = HttpStatus.UNAUTHORIZED;
					return new ResponseEntity<Map<String, Object>>(resultMap, status);
				}
				
				String accessToken = jwtUtil.createAccessToken(targetUser.getId());
				String refreshToken = jwtUtil.createRefreshToken(targetUser.getId());
				
//				발급받은 refresh token 을 DB에 저장.
				userService.saveRefreshToken(targetUser.getId(), refreshToken);
				
//				JSON 으로 token, userInfo 전달.
				resultMap.put("user", userDto);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			}else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
		} catch (Exception e) {
			System.out.println("로그인 에러 발생 : {}" + e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}

	// == 토큰 인증 ==
	@CrossOrigin
	@Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @Parameter(description = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				log.info("server: userDto == " + userDto.toString());
				
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 토큰 재발급
	@CrossOrigin
	@Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
//	===== 2. 로그아웃 =====
	@CrossOrigin
	@Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
	@GetMapping("/logout/{userId}")
	@Hidden
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	

	
//	===== 3. 회원가입(C) =====
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Map<String,String> map) throws IOException {
		log.info("signup(UserController.java): 회원가입 파라미터 확인");
		log.info("map");
		for(String key: map.keySet()) log.info(key + ": " + map.get(key));
		
		try {
			int existIdCnt = userService.idCheck(map.get("id"));
			log.info("signup(UserController.java): 회원가입 id 존재 유무 확인\nexistIdCnt: " + existIdCnt);
			
			if(existIdCnt != 0){
				return new ResponseEntity<String>("이미 존재하는 id입니다.", HttpStatus.OK);
			}
			else{
				userService.signUp(map);
				return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

//	===== 4. 회원정보 수정(U) =====
	@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody Map<String, String> params) throws IOException {
		if(!params.containsKey("id")) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		try {
			UserDto user = userService.userInfo(params.get("id"));
			if(params.containsKey("name")) user.setName(params.get("name"));
			if(params.containsKey("pw")) user.setPw(params.get("pw"));
			if(params.containsKey("email")) {
				user.setEmail_1(params.get("email").split("@")[0]);
				user.setEmail_2(params.get("email").split("@")[1]);
			}
			
			int res = userService.modifyUser(user);
			if(res != 0) {
				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	//5. 회원정보 삭제 (profile.jsp) (D)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id, HttpSession session, Model model) throws IOException {
		try {
			userService.delete(id); //DB에서 삭제
			
			// 회원탈퇴 성공
			session.invalidate(); //세션없앰
			
			return "redirect:/";
		} catch (Exception e) {
			model.addAttribute("msg", "회원정보 수정 중 문제 발생!!!");
			return "/error";
		} 	
	}

}
