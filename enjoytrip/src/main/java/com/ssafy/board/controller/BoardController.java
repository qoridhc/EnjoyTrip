package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.user.model.UserDto;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller()
@RequestMapping("/article")
public class BoardController {
	private static final long serialVersionUID = 1L;

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		System.out.println(userDto);
		
		if (userDto != null) 
		{
			try 
			{
				List<BoardDto> list = boardService.listArticle();
				request.setAttribute("articles", list);
				return "board/list";
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return "redirect:/error";
			}
		} 
		else
		{
			return "redirect:/user/login";
		}
	}
	
	@GetMapping("/view/{articleNo}")
	public String view(@PathVariable(value = "articleNo") int articleNo, Model model, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("user");
		
		if (userDto != null) {
			try {
				BoardDto boardDto = boardService.getArticle(articleNo);
				model.addAttribute("article", boardDto);
				return "board/view";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/error";
			}
		} 
		else {
			return "redirect:/user/login";
		}
	}
	
	@GetMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, Model model, HttpSession session) {
		
		UserDto userDto = (UserDto) session.getAttribute("user");
		if (userDto != null) {
			try 
			{
				boardDto.setUserId(userDto.getId());
				boardService.writeArticle(boardDto);
				return "redirect:list";
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return "index";
			}
		} 
		else {
			return "login";
		}
	}
	
	@GetMapping("/delete/{articleNo}")
	public String delete(@PathVariable(value = "articleNo") int articleNo, Model model) {
		try 
		{
			boardService.deleteArticle(articleNo);
			return "redirect:/article/list";
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			model.addAttribute("msg", "글 삭제 중 문제 발생!!!");
		}
		return "index";
	}
	
	@GetMapping("/modify/{articleNo}")
	public String mvModify(@PathVariable(value = "articleNo") int articleNo, Model model ) {
		
		BoardDto boardDto = new BoardDto();
		try 
		{
			boardDto = boardService.getArticle(articleNo);
			model.addAttribute("article", boardDto);
			return "board/modify";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			model.addAttribute("msg", "글 수정 중 문제 발생!!!");
			return "redirect:/article/list";
		}
	}
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, Model model) {
		try 
		{
			boardService.modifyArticle(boardDto);
			return "redirect:/article/view/"+ boardDto.getArticleNo();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			model.addAttribute("msg", "글 수정 중 문제 발생!!!");
		}
		return "index";
	}
}