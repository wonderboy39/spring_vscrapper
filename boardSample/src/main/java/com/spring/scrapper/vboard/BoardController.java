package com.spring.scrapper.vboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.scrapper.vboard.VBoardDAO;
import com.spring.scrapper.vboard.vo.VBoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private VBoardDAO boardDAO;
	
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
			VBoardVO vo, ModelAndView mav
			){
		try {
			mav.addObject("boardList", boardDAO.selectVBoardList(vo));	// Model 정보 저장 
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("getBoardList.jsp"); 							// View 정보 저장
		return mav;
	}
	
	@RequestMapping(value="/getBoard.do", method=RequestMethod.GET)
	public ModelAndView getBoard(VBoardVO vo, Model model, ModelAndView mav, @RequestParam(required=false) int seq){
		
		try {
			if(seq>0){
				VBoardVO board = boardDAO.selectVBoard(vo);
				model.addAttribute("board", board);
				mav.addObject("board", board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("getBoard.jsp");
		return mav;
	}
	
	@RequestMapping(value="/updateBoard.do", method=RequestMethod.GET)
	public String updateVBoardGet(@ModelAttribute("board") VBoardVO boardVO){
		return "modifyBoard.jsp";
	}
	
	@RequestMapping(value="/updateBoard.do", method=RequestMethod.POST)
	public String updateVBoardPost(@ModelAttribute("board") VBoardVO boardVO, Model model){
		boolean result = false;
		try {
			result = boardDAO.updateVBoard(boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result){
			return "redirect:getBoardList.do";
		}
		else{
			return "redirect:getBoard.do?seq=" + boardVO.getSeq();
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
