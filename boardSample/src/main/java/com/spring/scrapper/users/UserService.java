package com.spring.scrapper.users;

import com.spring.scrapper.users.vo.UserVO;

public interface UserService {
	
	public UserVO selectUser(UserVO vo);
	
	public boolean insertUser(UserVO vo);

}