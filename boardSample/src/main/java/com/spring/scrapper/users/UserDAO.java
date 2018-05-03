package com.spring.scrapper.users;

import com.spring.scrapper.users.vo.UserVO;

public interface UserDAO {
	
	public boolean insertUser(UserVO vo) throws Exception;
	
	public UserVO selectUser(UserVO vo) throws Exception;
}
