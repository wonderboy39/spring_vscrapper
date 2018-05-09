package com.spring.scrapper.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBeanOld {
//	private static SqlSessionFactory sessionFactory = null;
//	private SqlSessionFactoryBean() {}
//	
//	static {
//		try {
//			if(sessionFactory == null) {
//				System.out.println("current directory :: " + System.getProperty("user.dir") );
//				Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
//				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static SqlSession getSqlSessionInstance() {
//		return sessionFactory.openSession();
//	}
}
