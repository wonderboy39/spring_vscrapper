package com.spring.scrapper.users;

import com.spring.scrapper.dao.CommonDAO;

public class UserDAOImpl extends CommonDAO implements UserDAO{

	@Override
	public boolean insertUser(UserVO vo) throws Exception {
		return super.getSession().insert("users.insertUser", vo) >0 ? true : false;
	}

	@Override
	public UserVO getUser(UserVO vo) throws Exception {
		return super.getSession().selectOne("users.getUser", vo);
	}

}
