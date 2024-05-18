package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/article")
@Slf4j
public class BoardController {
	private static final long serialVersionUID = 1L;

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//search category, keyword
	@GetMapping("/list")
	public ResponseEntity<?> list(
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "keyword", required = false) String keyword) 
	{
		System.out.println("list(BoardController.java): parameter check\ncategory: " + category + ", keyword: " + keyword);
		List<BoardDto> list;
		try {
			if(keyword == null) {
				 list = boardService.listArticle();
			}
			else {
				Map<String, String> params = new HashMap<>();
				params.put("category", category);
				params.put("keyword", keyword);
				list = boardService.searchArticle(params);
			}
			
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
	public ResponseEntity<?> view(@PathVariable(value = "articleNo") int articleNo) {
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			System.out.println("view(BoardController.java): 조회수 변경 후 데이터 확인\nboardDto: "+boardDto);
			
			if(boardDto != null) 
			{	
				//조회수 변경
				int hit = Integer.parseInt(boardDto.getHit())+1;
				boardDto.setHit(String.valueOf(hit));
				boardService.updateArticleHit(boardDto);
				
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
	public ResponseEntity<?> write(@RequestBody Map<String, String> params) {
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
	public ResponseEntity<?> delete(@PathVariable(value = "articleNo") int articleNo) {
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
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto) {
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