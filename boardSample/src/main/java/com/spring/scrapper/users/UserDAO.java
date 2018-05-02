package com.spring.scrapper.users;

public interface UserDAO {
	
	public boolean insertUser(UserVO vo) throws Exception;
	
	public UserVO getUser(UserVO vo) throws Exception;
}
