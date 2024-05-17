package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.user.model.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/article")
public class BoardController {
	private static final long serialVersionUID = 1L;

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	private ResponseEntity<String> checkUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		System.out.println(userDto);
		if (userDto == null) {
			return new ResponseEntity<String>("no user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(HttpServletRequest request, HttpServletResponse response) {
//		ResponseEntity<String> result = checkUser(request);
//		if(result != null) return result;
		
		try {
			List<BoardDto> list = boardService.listArticle();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/view/{articleNo}")
	public ResponseEntity<?> view(@PathVariable(value = "articleNo") int articleNo, Model model, HttpSession session) {
//		ResponseEntity<String> result = checkUser(request);
//		if(result != null) return result;
		
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			if(boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK); 
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	boardDto, userId	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody Map<String, String> params, HttpSession session) {
		BoardDto boardDto = new BoardDto();
		try 
		{
			boardDto.setArticle(params);
			boardService.writeArticle(boardDto);
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/delete/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable(value = "articleNo") int articleNo, Model model) {
//		ResponseEntity<String> result = checkUser(request);
//		if(result != null) return result;
		
		try 
		{
			System.out.println(articleNo);
			boardService.deleteArticle(articleNo);
			return new ResponseEntity<String>("complete", HttpStatus.OK);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto, Model model) {
//		ResponseEntity<String> result = checkUser(request);
//		if(result != null) return result;
		
		try 
		{
			boardService.modifyArticle(boardDto);
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} 
		catch(Exception e) 
		{
			System.out.println(e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}