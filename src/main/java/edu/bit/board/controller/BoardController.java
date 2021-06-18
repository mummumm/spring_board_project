package edu.bit.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.board.page.Criteria;
import edu.bit.board.page.PageVO;
import edu.bit.board.service.BoardService;
import edu.bit.board.vo.BoardVO;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(Model model) {
		log.info("list()..");
		log.info(boardService.getList());
		
		model.addAttribute("list", boardService.getList());
		
		return "board_list";
	}
	
	@GetMapping("/board_list2") // board_list2를 치게되면 Criteria cri 객체(this.)가 생성이 되서 페이지(1~10)를 가져오게 된다.
	public String board_list2(Criteria cri, Model model) {
		log.info("board_list2()..");
		log.info(cri);

		model.addAttribute("list", boardService.getList(cri)); // 10개 가지고 온 다음에
		int total = boardService.getTotal(cri);

		log.info("total" + total);
		model.addAttribute("pageMaker", new PageVO(cri, total)); // 보여준다.

		return "board_list2";
	}
	
	@GetMapping("/content_view")
	public String content_view(BoardVO boardVO, Model model) {
		log.info("content_view()..");
		
		// 비즈니스 로직은 반드시 서비스 단에서 처리해야함.
		// 왜?
		// 1. 컨트롤러 단의 주 목적은 ModelAndView 리턴이다.(View 정도면 결정해서 넘김)
		// boardService.upHit(boardVO.getBid());
		// boardVo.getBid();
		// 이렇게 해주는 것보다 서비스 단에서 upHit(bid); 로 처리하는게 더 낫다.
		// 2. 비즈니스로직은 반드시 서비스단에 들어가야함.
		
		model.addAttribute("content_view", boardService.get(boardVO.getBid()));
	
		return "content_view";
	}
	
	@PostMapping("/modify()..")
	public String modify(BoardVO boardVO, Model model) {
		log.info("modify()..");
		boardService.modify(boardVO);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		log.info("delete()..");
		boardService.remove(boardVO.getBid());
		
		return "redirect:list";
	}
	
	@GetMapping("/write_view")
	public String write_view() {
		log.info("write_view()..");
		
		return "write_view";
	}
	
	@PostMapping("/write") 
	public String write(BoardVO boardVO) {
		log.info("write()..");
		boardService.writeBoard(boardVO);
		
		return "redirect:list";
	}
	
	@GetMapping("/reply_view")
	public String reply_view(BoardVO boardVO, Model model) {
		log.info("reply_view()..");
		model.addAttribute("reply_view", boardService.get(boardVO.getBid()));
		
		return "reply_view";
		
	}
	
	// 트랜잭션을 위한 비즈니스로직이 여기에 들어가면 안된다.
	@PostMapping("/reply")
	public String reply(BoardVO boardVO) {
		log.info("reply()..");
		boardService.writeReply(boardVO);
		// boardService.updateshape(boardVO) -> x
		
		return "redirect:list";
	}		 
	

}
