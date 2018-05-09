package com.spring.scrapper.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {
	@Inject
	private SqlSessionFactory sqlFactory;
	
	public SqlSession getSession() throws Exception{
		return sqlFactory.openSession();
	}
}
