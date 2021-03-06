package com.spring.scrapper.vboard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.scrapper.dao.CommonDAO;
import com.spring.scrapper.vboard.vo.VBoardVO;

@Repository
public class VBoardDAOImpl extends CommonDAO implements VBoardDAO{

	@Override
	public VBoardVO selectVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("seq", vo.getSeq());
		return super.getSession().selectOne("vboard.getVBoard", parameterMap);
	}

	@Override
	public List<VBoardVO> selectVBoardList(VBoardVO vo) throws Exception {
		return super.getSession().selectList("vboard.selectVBoardList");
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
	public boolean updateVBoard(VBoardVO vo) throws Exception{
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("title", vo.getTitle());
		parameterMap.put("writer", vo.getWriter());
		parameterMap.put("content", vo.getContent());
		parameterMap.put("url", vo.getUrl());
		parameterMap.put("cnt", vo.getCnt());
		parameterMap.put("regdate", new Date());
		parameterMap.put("writer_id", vo.getWriter_id());
		int result = -1;
		try{
			SqlSession session = super.getSession();
			result = session.insert("vboard.updateVBoard", parameterMap);
			session.commit();
			session.toString();
//			result = super.getSession().insert("vboard.updateVBoard", parameterMap);
		}
		catch (Exception e) {
			System.out.println("MYBATIS ERROR ... ");
		}
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().delete("vboard.deleteVBoard", parameterMap) > 0 ? true : false;
	}

}
