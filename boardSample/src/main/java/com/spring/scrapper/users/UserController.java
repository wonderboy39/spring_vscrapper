package com.spring.scrapper.users;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.scrapper.users.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(){
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session){
		UserVO user = null;
		try {
			user = userService.selectUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user!=null){
			session.setAttribute("idKey", user.getId());
			System.out.println("Complete---------");
			return "redirect:getBoardList.do";
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
