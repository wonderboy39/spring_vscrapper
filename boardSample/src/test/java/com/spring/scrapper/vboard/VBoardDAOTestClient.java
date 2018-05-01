package com.spring.scrapper.vboard;

import java.util.List;

import com.spring.scrapper.vboard.vo.VBoardVO;

public class VBoardDAOTestClient {
	public static void main(String [] args){
		VBoardDAOImpl boardDAO = new VBoardDAOImpl();
		VBoardVO vo = new VBoardVO();
		
		vo.setTitle("마이바티스 테스트 코드 ");
		vo.setWriter("TESTER");
		vo.setContent("마이바티스 커넥터 테스트 ");
		try {
			boardDAO.insertVBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<VBoardVO> boardList = null;

		try {
			boardList = boardDAO.selectVBoardList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(VBoardVO board : boardList){
			System.out.println("====>> " + board.toString());
		}
	}
}
