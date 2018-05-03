package com.spring.scrapper.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.scrapper.users.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO selectUser(UserVO vo) {
		UserVO result = null;
		try {
			result = userDAO.selectUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean insertUser(UserVO vo) {
		
		boolean result = false;
		try {
			result = userDAO.insertUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}