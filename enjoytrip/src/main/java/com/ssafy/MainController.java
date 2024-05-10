package com.ssafy;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.trip.controller.AttractionController;
import com.ssafy.user.controller.UserController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController extends HttpServlet {
	
	@GetMapping({ "/", "/index" })
	public String showIndex() {
		return "index";
	}
}
