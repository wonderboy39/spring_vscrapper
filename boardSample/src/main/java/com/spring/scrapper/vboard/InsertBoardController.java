package com.spring.scrapper.vboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.scrapper.vboard.vo.VBoardVO;

@Controller
public class InsertBoardController {
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(VBoardVO vo, VBoardDAO boardDAO){
		boolean result = false;
		try {
			result = boardDAO.insertVBoard(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result){
			return "getBoardList.do";						
		}else{
			return "insertBoard.do";
		}
	}
}
