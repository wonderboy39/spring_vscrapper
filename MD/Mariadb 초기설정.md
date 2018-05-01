# Mariadb 초기설정

- mariadb 다운로드 (구글 검색후 다운로드 & 설치)
- database 조회, 신규 생성
- 권한부여



## mariadb 다운로드

- 공식다운로드 페이지에서 다운로드 받는다.  
- 설치시 root 비밀번호를 묻는데(id는 당연히 'root'다), 1111로 설정했다.  
- 윈도우'시작>MariaDB 10.0(x64)>MySQL Client (MariaDB 10.0 (x64))' 선택  




## database 조회, 신규생성

### database 조회, mysql database 사용

```sql
> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| test               |
+--------------------+
> use mysql;
```

  

## 계정 조회, 생성, database 생성



  

```sql
-- 계정 조회
SELECT host, user, password FROM user;
-- 계정 생성 
INSERT INTO user(
    host, user, password, ssl_cipher, x509_issuer, x509_subject, authentication_string
)
VALUES(
    'localhost', 'scrapper', password('1111'), '','','', ''
);
-- database 생성
CREATE DATABASE springscrapper DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

참고자료 :  

 http://linuxism.tistory.com/263

  

## 권한 (privileges) 부여 

  

```sql
-- springscrapper라는 이름의 database의 모든 부분에 대해 scrapper유저가 localhost에서 접근한느 것을 허용
grant all privileges on springscrapper.* to scrapper@localhost;
-- 권한 새로고침
flush privileges;
```



## 테이블 생성

오라클의 시퀀스를 흉내내서 만드는 방법이 있다.  

시퀀스를 사용하기 전에 ALTER를 통해 증가시켜서 사용하는 방식인데, 해당 내용은 추후에 **'테이블 튜닝' 에서** 사용하고자 한다.

  

게시글 테이블 생성

```sql
CREATE TABLE vboard
(
  seq       BIGINT AUTO_INCREMENT PRIMARY KEY,
  title     VARCHAR(100)                 NOT NULL,
  writer    VARCHAR(40) default 'NULL'   NULL,
  writer_id VARCHAR(40) default 'NULL'   NULL,
  content   VARCHAR(3000) default 'NULL' NULL,
  url       VARCHAR(1000) default 'NULL' NULL,
  regdate   DATETIME default 'NULL'      NULL,
  cnt       INT DEFAULT 'NULL'           NULL
);

```



사용자 테이블 생성

```sql
create table users
(
	seq int auto_increment
		primary key,
	id varchar(40) not null,
	name varchar(100) not null,
	password varchar(40) not null,
	role varchar(300) default 'NULL' null,
	constraint Users_id_uindex
		unique (id)
)
;

```

  

## 윈도우 에러

mariadb를 설치하고나서 컴퓨터를 껏다가 다시 키고나서 mysql -u root -p와 같이 접속 명령을 내리면 아래와 같은 에러를 낸다. (mysqlworkbench와 같은 관리 프로그램을 아직 설치해놓지 않은 상태이기 때문인듯 하다)  

```bash
C:\Windows\system32>mysql -u root -p
Enter password: ****
...
...

ERROR 2002 (HY000): Can't connect to MySQL server on 'localhost' (10061)
```

**ERROR 2002 (HY000): Can't connect to MySQL server on 'localhost' (10061)**  

와 같은 에러는 아래와 같이 mysqld --skip-grant 명령어로 해결 가능하다.  

```bash
C:\Windows\system32>mysqld --skip-grant
...
...

2018-04-29 15:35:39 10040 [Note] Using unique option prefix 'skip-grant' is erro
r-prone and can break in the future. Please use the full name 'skip-grant-tables
' instead.
2018-04-29 15:35:39 10040 [Note] mysqld (mysqld 10.2.14-MariaDB) starting as pro
cess 8620 ...
```

  

## pom.xml에 mariadb 관련 의존성 라이브러리 명시

```xml
		<!-- Spring JDBC -->
		<!-- 1) spring-jdbc 의존성 설정 (설정하지 않을 경우 JdbcDaoSupport 사용할 수 없다...)-->
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
		<!-- <dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-dbcp2</artifactId>
			<version>2.1.1</version>
		</dependency> -->
		
		<!-- jdbcTemplate 공부하면서 추가한 dependency -->
		<!-- 위에서는 2.1.1버전을 설치하는 것으로 했었지만 책의 예제에서는 1.4버전을 사용한다. -->
		<!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency> 
		
		<!-- mariadb -->
		<!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>2.0.2</version>
		</dependency>
```



  





## 테이블 튜닝

  

  

