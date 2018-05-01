package com.spring.scrapper.vboard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.scrapper.dao.CommonDAO;
import com.spring.scrapper.vboard.vo.VBoardVO;

public class VBoardDAOImpl extends CommonDAO implements VBoardDAO{

	@Override
	public VBoardVO selectVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().selectOne("vboard.getVBoard", parameterMap);
	}

	@Override
	public List<VBoardVO> selectVBoardList(VBoardVO vo) throws Exception {
		return super.getSession().selectList("vboard.getVBoardList");
	}

	@Override
	public boolean insertVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("title", vo.getTitle());
		parameterMap.put("writer", vo.getWriter());
		parameterMap.put("content", vo.getContent());
		parameterMap.put("url", "http://naver.com");
		parameterMap.put("cnt", 12121);
		parameterMap.put("regdate", new Date());
		parameterMap.put("writer_id", "test1");
		return super.getSession().insert("vboard.insertVBoard", parameterMap) >0 ? true : false;
	}

	@Override
	public boolean updateVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().update("vboard.updateVBoard", parameterMap) > 0 ? true : false;
	}

	@Override
	public boolean deleteVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().delete("vboard.deleteVBoard", parameterMap) > 0 ? true : false;
	}

}
