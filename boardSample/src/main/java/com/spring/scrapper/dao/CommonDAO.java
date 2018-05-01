package com.spring.scrapper.dao;

import org.apache.ibatis.session.SqlSession;

public class CommonDAO {
	public SqlSession getSession(){
		return SqlSessionFactoryBean.getSqlSessionInstance();
	}
}
