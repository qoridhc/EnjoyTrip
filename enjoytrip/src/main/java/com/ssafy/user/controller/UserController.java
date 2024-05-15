package com.ssafy.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	private UserService userService;

	private UserController(UserService userService) {
		this.userService = userService;
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
				
				if (targetUser != null && targetUser.getId().equals(userDto.getId())
						&& targetUser.getPw().equals(userDto.getPw())) {
					System.out.println("로그인 성공");
					resultMap.put("user", userDto);
					status = HttpStatus.CREATED;
				}
				else {
					status = HttpStatus.UNAUTHORIZED;
				}
				
//				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
//				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
//				log.debug("access token : {}", accessToken);
//				log.debug("refresh token : {}", refreshToken);
//				
////				발급받은 refresh token 을 DB에 저장.
//				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);
//				
////				JSON 으로 token 전달.
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);

			}

		} catch (Exception e) {
			System.out.println("로그인 에러 발생 : {}" + e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}

//	===== 2. 로그아웃 =====
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//	===== 3. 회원가입(C) =====
	@GetMapping("/signup")
	public String signup() {
		return "/user/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@RequestParam Map<String,String> map, Model model) throws IOException {
		
		System.out.println(map);
		
		try {
			int existIdCnt = userService.idCheck(map.get("id"));
			System.out.println(existIdCnt);
			
			if(existIdCnt != 0) {
				model.addAttribute("msg", "이미 존재하는 아이디 입니다.");
				return "/user/signup";
			}else {
				userService.signUp(map);
				
				//회원가입 성공
				System.out.println("회원가입 성공");
				return "/user/login";
				
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원가입 중 문제 발생!!!");
			return "/error";
		}
		
	}

//	===== 4. 회원정보 수정 (profile.jsp) (U) =====
	@GetMapping("/profile")
	public String showProfile(HttpSession session) {

		System.out.println("loginUser : " + session.getAttribute("loginUser"));
		
		return "/user/profile";
	}
	
	@PostMapping("/modify")
	public String modify(@RequestParam Map<String, String> map , Model model) throws IOException {
		System.out.println(map);
		try {
			userService.modify(map);
			
			//회원정보 수정 성공
			System.out.println("회원정보 수정 성공");
			return "redirect:/";
		} 	catch (Exception e) {
			model.addAttribute("msg", "회원정보 수정 중 문제 발생!!!");
			return "/error";
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
