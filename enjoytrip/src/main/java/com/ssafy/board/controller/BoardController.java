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
import com.ssafy.board.model.MemoDto;
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
			int hit = Integer.parseInt(boardDto.getHit())-2;
			boardDto.setHit(String.valueOf(hit));
			boardService.modifyArticle(boardDto);
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} 
		catch(Exception e) 
		{
			System.out.println(e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/comment/{articleNo}")
	public ResponseEntity<?> comment(@PathVariable(value="articleNo") int articleNo){
		try {
			List<MemoDto> list = boardService.getMemoList(articleNo);
			
			if(!list.isEmpty()) {
				return new ResponseEntity<List<MemoDto>>(list, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/comment/write")
	public ResponseEntity<?> writeComment(@RequestBody Map<String, String> params){
		if(!params.containsKey("articleNo") || !params.containsKey("userId") || !params.containsKey("comment"))
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		try {
			log.info("writeComment(BoardController): 댓글 작성");
			for(String key: params.keySet()) log.info(key + ": " + params.get(key));
			
			int res = boardService.writeMemo(params);
			if(res != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/comment/delete/{memoNo}")
	public ResponseEntity<?> deleteComment(@PathVariable(value="memoNo")int memoNo){
		try {
			int res = boardService.deleteMemo(memoNo);
			if(res != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(String.valueOf(memoNo)+"에 해당하는 댓글이 없습니다.", HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/comment/modify")
	public ResponseEntity<?> modifyComment(@RequestBody Map<String, String> params){
		if(!params.containsKey("memoNo")) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			int res = boardService.modifyMemo(params);
			if(res != 0) {
				return new ResponseEntity<Void>(HttpStatus.OK);
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