package com.spring.scrapper.vboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.scrapper.vboard.VBoardDAO;
import com.spring.scrapper.vboard.vo.VBoardVO;

@Controller
public class BoardController {
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(VBoardVO vo, VBoardDAO dao) {
		boolean result = false;
		try {
			result = dao.insertVBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result) {
			return "redirect:getBoardList.do";
		}
		else{
			return "/insertBoard.do";
		}
	}	
	
	@RequestMapping(value="/getBoardList.do")
	public ModelAndView getBoardList(
			@RequestParam(value="searchCondition" ,defaultValue="TITLE", required=false) String condition,
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
			VBoardVO vo, VBoardDAO dao, ModelAndView mav
			){
		System.out.println("검색조건 : " + condition);
		System.out.println("검색단어 : " + keyword);
		try {
			mav.addObject("boardList", dao.selectVBoardList(vo));	// Model 정보 저장 
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("getBoardList.jsp"); 						// View 정보 저장
		return mav;
	}
	
	@RequestMapping(value="/getBoard.do")
	public ModelAndView getBoard(VBoardVO vo, VBoardDAO dao, Model model, ModelAndView mav){
		try {
			model.addAttribute("board", dao.selectVBoard(vo));
			mav.addObject("board", dao.selectVBoard(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("getBoard.jsp");
		return mav;
	}
	
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") VBoardVO vo, VBoardDAO dao){
		System.out.println("번호 		: " 	+ vo.getSeq());
		System.out.println("제목 		: " 	+ vo.getTitle());
		System.out.println("작성자 	: " 	+ vo.getWriter());
		System.out.println("작성자 ID : " 	+ vo.getWriter_id());
		System.out.println("등록일 	: " 	+ vo.getRegDate());
		System.out.println("조회수 	: " 	+ vo.getCnt());
		
		boolean result = false;
		try {
			result = dao.updateVBoard(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result){
			return "getBoardList.do";
		}
		else{
			return "updateBoard.do";
		}
	}
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(VBoardVO vo, VBoardDAO dao){
		boolean result = false;
		try {
			result = dao.deleteVBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result){
			return "getBoardList.do";
		}
		else{
			return "deleteBoard.do";			
		}
	}
}
