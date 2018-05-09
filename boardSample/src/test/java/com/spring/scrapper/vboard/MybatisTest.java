package com.spring.scrapper.vboard;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.scrapper.users.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/web.xml"})
@ContextConfiguration(locations={"file:src/main/resources/config/context-database.xml"})
public class MybatisTest {
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testSession() throws Exception{
		try(SqlSession session = sqlFactory.openSession()){
			System.out.println(">>>>>>> session 출력 " + session + "<<<<<<<<< \n");
			
			UserVO vo = new UserVO();
			vo.setId("test3");
			vo.setPassword("1111");
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("id", vo.getId());
			parameterMap.put("password", vo.getPassword());
			
			UserVO result = session.selectOne("users.selectUser", parameterMap);
			System.out.println("resultVO.writer :: " + result.getName());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
