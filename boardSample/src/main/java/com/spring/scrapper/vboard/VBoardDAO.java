package com.spring.scrapper.vboard;

import java.util.List;

import com.spring.scrapper.vboard.vo.VBoardVO;

public interface VBoardDAO {
	public VBoardVO selectVBoard(VBoardVO vo) throws Exception;

	public List<VBoardVO> selectVBoardList(VBoardVO vo) throws Exception;

	public boolean insertVBoard(VBoardVO vo) throws Exception;

	public boolean updateVBoard(VBoardVO vo) throws Exception;

	public boolean deleteVBoard(VBoardVO vo) throws Exception;
}
