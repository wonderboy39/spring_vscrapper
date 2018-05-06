package com.spring.scrapper.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.scrapper.dao.CommonDAO;
import com.spring.scrapper.users.vo.UserVO;

@Repository
public class UserDAOImpl extends CommonDAO implements UserDAO{

	@Override
	public boolean insertUser(UserVO vo) throws Exception {
		return super.getSession().insert("users.insertUser", vo) >0 ? true : false;
	}

	@Override
	public UserVO selectUser(UserVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", vo.getId());
		parameterMap.put("password", vo.getPassword());
		return super.getSession().selectOne("users.selectUser", parameterMap);
	}

}
