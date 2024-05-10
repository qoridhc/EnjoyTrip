package com.ssafy.user.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
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
	
	@PostMapping("/login")
	public String login(UserDto userDto, Model model, HttpSession session) {

		try {

			UserDto targetUser = userService.login(userDto.getId());
			
			System.out.println("targetUser : " + targetUser);
			
			if (targetUser != null && targetUser.getId().equals(userDto.getId())
					&& targetUser.getPw().equals(userDto.getPw())) {
				session.setAttribute("user", targetUser);
				return "redirect:/";

			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				System.out.println("login: fail(null)");
				return "index";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "/error";
		}
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
