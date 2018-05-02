package com.spring.scrapper.users;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.scrapper.users.UserDAO;
import com.spring.scrapper.users.UserVO;

public class LoginController {
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(){
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO dao, HttpSession session){
		UserVO user = null;
		try {
			user = dao.getUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user!=null){
			session.setAttribute("idKey", user.getId());
			return "getBoardList.do";
		}
		else{
			return "login.jsp";
		}
	}
	
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "login.jsp";
	}
}
