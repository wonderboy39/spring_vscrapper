# Spring MyBatis 설정

## 목차

- Java ORM Plugin 설치
- pom.xml 수정
- SQL Mapper XML 파일 생성, 이동, 작성
- Mybatis 환경설정 파일
- SqlSession 객체생성 클래스, CommonDAO 작성
- DAO Interface, 클래스 작성
- 테스트 클라이언트 작성 및 실행
  
## Java ORM Plugin 설치
이클립스 [Help] -> [Eclipse Marketplace] -> orm 입력 -> Enter -> Java ORM Plugin for Eclipse beta 선택, install  
## pom.xml
mybatis 바이너리를 스프링 컨테이너에서 인식할수 있어야 하므로 아래의 항목들을 pom.xml의 적당한 위치에 입력하고 저장한다.

```xml
		<!-- 1) spring-jdbc 의존성 설정 (설정하지 않을 경우 JdbcDaoSupport 사용할 수 없다...) -->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>5.0.0.RELEASE</version>
		</dependency>	

		<!-- 2) JdbcTemplate클래스 의존성 추가 (commons-dbcp 또는 commons-dbcp2 추가) -->
		<!-- jdbcTemplate 공부하면서 추가한 dependency -->
		<!-- dbcp2 버전이다. 추후 적용하기로 하고 우선은 책의 예제에 최대한 맞춰나가는 방향으로 결정 -->
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
		<dependency> 
			<groupId>org.apache.commons</groupId> 
			<artifactId>commons-dbcp2</artifactId> 
			<version>2.1.1</version> 
		</dependency>

		<!-- mariadb -->
		<!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>2.0.2</version>
		</dependency>

		<!-- mybatis -->
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.6</version>
		</dependency>
		
		<!-- ibatis -->
		<!-- https://mvnrepository.com/artifact/org.apache.ibatis/ibatis-core -->
		<dependency>
			<groupId>org.apache.ibatis</groupId>
			<artifactId>ibatis-core</artifactId>
			<version>3.0</version>
		</dependency>
```



## SQL Mapper XML 파일 생성, 이동, 작성

- 프로젝트 디렉터리 우클릭 -> New -> Other.. -> Java ORM Plugin -> Mybatis Mapper XML 선택 -> Mybatis Mpper name : vboard.xml 입력 후 Finish 버튼 클릭  
- src/main/resources 폴더에 'sql' 패키지 생성후 해당 디렉터리에  vboard.xml 이동시킨다.
- vboard.xml 파일은 아래와 같이 수정한다.
- 샘플 테이블이 너무 옛날에 멋대로 만들어서 병맛이지만 그대로 간다. 나중에 다시 새로 만들어서 업데이트하자

```xml
<!-- vboard.xml -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- <mapper namespace="#package.#mapperinterfacename"> -->
<mapper namespace="vboard">
<!--  
	<resultMap type="#modelname" id="YourResultSet">
		<id property="param1" column="columnname1" />
		<result property="param2" column="columnname2" />
		<result property="param3" column="columnname3" />
	</resultMap>
-->
	<select id="getVBoardList" resultType="com.spring.scrapper.biz.vboard.VBoardVO">
		SELECT * FROM VBoard
	</select>
	
	<select id="getVBoard" parameterType="java.util.HashMap">
		SELECT * FROM VBoard WHERE seq=#{seq}
	</select>
	
	<insert id="insertVBoard" parameterType="java.util.HashMap">
		INSERT INTO VBoard(
			title, writer, writer_id, content, url, regDate, cnt
		)
		VALUES(
			#{title}, #{writer}, #{writer_id}, #{content}, #{url}, #{regDate}, #{cnt}
		)
	</insert>
	
	<update id="updateVBoard" parameterType="java.util.HashMap">
		UPDATE VBoard 
		SET title=#{title}, writer=#{writer}, writer_id=#{writer_id},
			content=#{content}, url=#{url}, regDate=#{regDate}, cnt=#{cnt}
		WHERE seq=#{seq}
	</update>
	
	<delete id="deleteBoard" parameterType="int">
		DELETE FROM VBoard WHERE seq=#{seq}
	</delete>

</mapper>


```





## Mybatis 환경설정 파일

- 프로젝트명 우클릭 -> [New] -> [Other] -> Java ORM Plugin -> MyBatis Configuration XML 선택 -> Next -> MyBatis Configuration name : mybatis-config.xml 입력후 Finish  
- src 폴더에 db.properties 파일, mybatis-config.xml 파일이 생성된다.
- 이 파일들을 src/main/resource 소스 폴더로 이동한다.
- src/main/resources/config 패키지를 만든후 config 디렉터리에 db.properties, mybatis-config.xml 파일을 옮겨준다.  

**db.properties 파일 내용**

```properties
jdbc.driver=org.mariadb.jdbc.Driver
jdbc.url=jdbc:mariadb://localhost:3306/springscrapper
jdbc.username=scrapper
jdbc.password=1111
```

  

**mybatis-config.xml 파일 내용**  

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<properties resource="db.properties" />
	<typeAliases>
		<!-- <typeAlias type="#package.#modelname" alias="#modelname"></typeAlias> -->
	</typeAliases>
	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="${jdbc.driver}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.username}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>
	<mappers>
		<mapper resource="sql/vboard.xml" />
	</mappers>
</configuration>

```



## SqlSession 객체생성 클래스, CommonDAO 작성

com.spring.scrapper에 dao라는 패키지를 생성하고 SqlSessionFactory Bean 클래스를 생성한다.  

**SqlSessionFactoryBean.java**  

```java
package com.spring.scrapper.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
	private static SqlSessionFactory sessionFactory = null;
	private SqlSessionFactoryBean() {}
	
	static {
		try {
			if(sessionFactory == null) {
				System.out.println("current directory :: " + System.getProperty("user.dir") );
				Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession();
	}
}

```

  

**CommonDAO 클래스 작성**

각 기능의 패키지 내의 DAO 역할을 하는 클래스마다 CommonDAO 클래스를 extends해서 Session을 불러오는 로직을 하나로 통일 시킨다. 이렇게 하는 이유는 여러곳에서 getInstance로 접근할 경우 SqlSessionFactoryBean클래스의 getInstance() 메서드의 이름이 바뀌는 등이 있을 경우 고쳐야 할 일이 태산이 되기 때문이다...

```java
package com.spring.scrapper.dao;

import org.apache.ibatis.session.SqlSession;

public class CommonDAO {
	public SqlSession getSession(){
		return SqlSessionFactoryBean.getSqlSessionInstance();
	}
}
```

  

## DAO Interface, 클래스 작성

  

**VBoardDAO.java (인터페이스)**

```java
package com.spring.scrapper.vboard;

import java.util.List;

import com.spring.scrapper.vboard.vo.VBoardVO;

public interface VBoardDAO {
	public VBoardVO selectVBoard(VBoardVO vo) throws Exception;

	public List<VBoardVO> selectVBoardList(VBoardVO vo) throws Exception;

	public boolean insertVBoard(VBoardVO vo) throws Exception;

	public boolean updateVBoard(VBoardVO vo) throws Exception;

	public boolean deleteVBoard(VBoardVO vo) throws Exception;
}
```

  

**VBoardDAOImpl.java**  

```java
package com.spring.scrapper.vboard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.scrapper.dao.CommonDAO;
import com.spring.scrapper.vboard.vo.VBoardVO;

public class VBoardDAOImpl extends CommonDAO implements VBoardDAO{

	@Override
	public VBoardVO selectVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().selectOne("vboard.getVBoard", parameterMap);
	}

	@Override
	public List<VBoardVO> selectVBoardList(VBoardVO vo) throws Exception {
		return super.getSession().selectList("vboard.getVBoardList");
	}

	@Override
	public boolean insertVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("title", vo.getTitle());
		parameterMap.put("writer", vo.getWriter());
		parameterMap.put("content", vo.getContent());
		parameterMap.put("url", "http://naver.com");
		parameterMap.put("cnt", 12121);
		parameterMap.put("regdate", new Date());
		parameterMap.put("writer_id", "TESTER!!! ");
		return super.getSession().insert("vboard.insertVBoard", parameterMap) >0 ? true : false;
	}

	@Override
	public boolean updateVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().update("vboard.updateVBoard", parameterMap) > 0 ? true : false;
	}

	@Override
	public boolean deleteVBoard(VBoardVO vo) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		return super.getSession().delete("vboard.deleteVBoard", parameterMap) > 0 ? true : false;
	}

}
```



## 테스트 클라이언트 작성 및 실행

- src/test/java 내부에 com.spring.scrapper 패키지를 만든다.
- 서브 패키지로 vboard 패키지를 만든다.
- vboard 패키지 내부에 VBoardDAOTestClient 라는 이름의 클래스를 만단다.


**VBoardDAOTestClient.java 소스**  

```java
package com.spring.scrapper.vboard;

import java.util.List;

import com.spring.scrapper.vboard.vo.VBoardVO;

public class VBoardDAOTestClient {
	public static void main(String [] args){
		VBoardDAOImpl boardDAO = new VBoardDAOImpl();
		VBoardVO vo = new VBoardVO();
		
		vo.setTitle("마이바티스 테스트 코드 ");
		vo.setWriter("TESTER");
		vo.setContent("마이바티스 커넥터 테스트 ");
		try {
			boardDAO.insertVBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<VBoardVO> boardList = null;

		try {
			boardList = boardDAO.selectVBoardList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(VBoardVO board : boardList){
			System.out.println("====>> " + board.toString());
		}
	}
}
```

  

**출력결과**  

```java
current directory :: /Users/soon/workspace/spring/spring_annotation_mybatis/boardSample
====>> VBoardVO [seq=1, title=TEST - VBoardService Component - 1 , writer=테스트유저1, writer_id=test1, content=TEST TEST TEST, JUST TEST..., url=https://www.youtube.com/embed/VNxUy2ua9AM, regDate=Sat Aug 12 21:49:01 KST 2017, cnt=0]
====>> VBoardVO [seq=3, title=TEST2 - VBoardService 2, writer=테스트유저2, writer_id=test2, content=테스트유저 2 입니다~, url=https://www.youtube.com/embed/VNxUy2ua9AM, regDate=null, cnt=0]
====>> VBoardVO [seq=4, title=TEST2 - VBoardService 2, writer=테스트 유저2, writer_id=test2, content=LogAdvice - xml기반 AOP기능 구현 테스트 , url=https://www.youtube.com/embed/VNxUy2ua9AM, regDate=Wed Aug 23 01:05:55 KST 2017, cnt=0]
====>> VBoardVO [seq=6, title=TEST3 - VBoardService 3, writer=테스트유저3, writer_id=test3, content=안뇽하세요!! 테 유저 3입니다~!!, url=https://www.youtube.com/embed/VNxUy2ua9AM, regDate=null, cnt=0]
====>> VBoardVO [seq=7, title=TEST3 - VBoardService 3, writer=테스트유저3, writer_id=test3, content=LogAdvice - Spring JDBC 구현 테스트 (JDBCDaoSupport클래스 상속) , url=https://www.youtube.com/embed/VNxUy2ua9AM, regDate=Mon Oct 02 16:59:38 KST 2017, cnt=0]
====>> VBoardVO [seq=9, title=TEST3 - jsp 기능테스트(글 등록), writer=테스트유저3, writer_id=null, content=안뇽하세요. 테유저3 입니다., url=http://naver.com, regDate=Fri Oct 06 22:04:09 KST 2017, cnt=0]
```








