# Spring MVC 설정과정

스프링프레임워크 내부의 DispatcherServlet(org.springframework.web.servlet.DispatcherServlet)을 설정하는 과정을 정리했다.  

  

## MVC 설정 순서 

기본적인 순서를 목차처럼 정리해보면 아래와 같다.

1. **(필수) Spring 프레임워크의 DispatcherServlet 클래스를 web.xml에 등록**  
   web.xml에 DispatcherServlet 등록 or 수정
2. **(필수) DispatcherServelet이 스프링 컨테이너 구동시 읽어들일 스프링 설정파일추가**  
   /WEB-INF/ -> New -> Spring Bean Configuration -> action-servlet.xml 파일 생성
3. **(선택) 스프링 설정 파일 위치/이름 변경** - web.xml  
   action-servlet.xml 을 presentation-layer.xml로 변경
4. **(3을 했을 경우 필수) web.xml 파일을 열어서 변경한 파일명의 위치, xml 앨리먼트 이름 설정, DispatcherServlet.java 수정**  
   \<init-param\>\</init-param\> 내부에 param-name, param-value 설정
5. 인코딩 설정 (DispatcherServlet.java, web.xml)
6. ViewResolver 설정
7. (어노테이션을 사용하지 않을 경우) presentation-layer.xml 에 직접 만든 Controller 클래스 등록
8. (어노테이션을 사용하지 않을 경우) 6.에 등록한 Controller 클래스 코딩
9. **(어노테이션) presentation-layer.xml 설정**
10. **(어노테이션) 리소스 이동(jsp파일들을 모두 src/main/webapp 밑으로 이동)**
11. **(어노테이션) @Controller, @RequestMapping, 요청처리로직 작성**





## 1. web.xml에 스프링 Dispatcher 등록/수정

/WEB-INF/web.xml을 수정

```xml
<web-app ...>
    <servlet>
        <servlet-name>action</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>action</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
</web-app>
```

\<servlet\>\</servlet\> 내부에 \<servlet-class\> 태그 안에 스프링 컨테이너의 DispatcherServlet 클래스를 추가  

스프링 컨테이너의 DispatcherServelt클래스는 org.springframework.web.servlet.DispatcherServlet 이다.



## 참고) 스프링 컨테이너 구동 과정

별다른 추가설정없이 DispatcherServlet을 생성했다면, DispatcherServlet이 스프링 컨테이너를 구동할 때 무조건 /WEB-INF/action-servlet.xml을 찾아 로딩한다.  

DispatcherServlet은 Spring 컨테이너를 구동할 때 web.xml 파일에 등록된 서블릿 이름 뒤에 '-servlet.xml'을 붙여서 스프링 설정파일을 찾는다.  

**서블릿 컨테이너 (Apache)**  

- 서블릿 컨테이너는 DispatcherServlet 클래스를 관리한다.
- 추후 상세히 정리할 것.


**스프링의 컨테이너 (XmlWebApplicationContext)**  

- 클라이언트의 요청으로 DispatcherServlet 객체가 생성되고 나면 
- DispatcherServlet클래스의 init()메서드에서 XmlWebApplicationContext 를 구동한다. 
- init()메서드는 스프링 DispatcherServlet클래스에서 재정의된 메서드이다.
- XmlWebApplicationContext는 ApplicationContext를 구현한 클래스 중 하나이다.
- XmlWebApplicationContext는 우리가 직접 생성하는 것이 아니라 DispatcherServlet클래스가 생성한다.


**SpringMVC(스프링 소스에서의 MVC)내부의 DispatcherServlet클래스**  

- SpringMVC에서는 DispatcherServlet클래스가 유일한 서블릿이다.  
- 따라서 서블릿 컨테이너는 web.xml 파일에 등록된 DispatcherServlet 만 생성해준다.  
- 스프링 DispatcherServlet은 HandlerMapping, ViewResolver, Controller 등의 객체들을 스프링 컨테이너를 구동해서 생성한다.  
- 스프링 DispatcherServlet객체 혼자서는 클라이언트의 요청을 처리할 수 없기 때문에, HandlerMApping, ViewResolver, Controller 객체를 생성해야 하는데 이러한 객체 생성을 담당하는 것이 스프링 컨테이너이다.



## 2. (필수) DispatcherServlet이 스프링 컨테이너 구동시 읽어들일 스프링 설정파일추가

New -> Other-> Spring Bean configuration File -> Next -> File Name : action-servlet.xml  

WEB-INF 디렉터리 밑에 action-servlet.xml 파일이 생성된다.

  



## 3. , 4.(선택) 스프링 설정 파일 변경 DispatcherServlet 의 init()변경  

**파일 위치 이동, 이름변경**  

**1) /WEB-INF/ 아래에 config 폴더를 만들고 action-servlet.xml파일을 옮긴다. 파일 이름은 presentation-layer.xml로 변경**  

**2) web.xml 파일을 열어서 DispatcherServlet클래스를 등록한 곳에 \<init-param\> 을 추가하고 내부에**

- \<param-name\> : contextConfiguration (원하는 이름 아무거나 지정 가능)
- \<param-value\> : /WEB-INF/config/presentation-layer.xml ( 변경된 파일 이름, 경로의 경로를 적는다)

**3) DispatcherServlet.java**  

- spring Framework 내부에서 자체적으로 contextConfiguration이라고 명시해놓은 설정을 아래 명시해놓은 로직과 유사한 흐름으로 호출하게 된다.
- 예를 들어보자면 아래에 예로 남겨놓은 DispatcherServlet.java를 호출한다는 것이며, 실제로는 훨씬 더 복잡한 call-flow를 가진다.


**web.xml**  

(init-param태그 추가, param-value, name 설정 추가)

```xml
<servlet>
    <servelt-name>action</servelt-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfiguration</param-name>
        <param-value>/WEB-INF/config/presentation-layer.xml</param-value>
    </init-param>
    <servlet-mapping>
        <servlet-name>action</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
</servlet>
```

**DispatcherServlet.java**  

```java
public class DispatcherServlet extends HttpServlet{
    private String contextConfigLocation;
    
    public void init(ServletConfig config) throws ServletException{
        contextConfigLocation = config.getInitParameter("contextConfiguration");
        new XmlWebApplicationContext(contextConfiguration);
    }
}
```

  

  

## 5. 인코딩 설정

  

## 6. ViewResolver 활용하기

스프링 컨테이너에서 springframework에서 제공하는 ViewResolver를 호출하도록 설정을 해야 한다. ViewResolver를 사용하면 클라이언트로부터의 직접적인 JSP 호출을 차단할 수 있어서 대부분의 웹 프로젝트에서 ViewResolver 사용은 거의 필수적이다. ViewResolver는 여러가지가 있지만, JSP를 View로 사용하는 경우에는 InternalResourceViewResolver를 사용한다.  

**1) ViewResolver 적용**  

- /WEB-INF/board/ 폴더를 생성한다. (/webapp/WEB-INF/board/)    
- getBoardList.jsp, getBoard.jsp 파일을 이동시킨다.  
- presentation-layer.xml에 springFramework의 ViewResolver를 등록한다.  

지금까지는 /webapp/ 아래에 getBoardList.jsp, getBoard.jsp 와 같은 파일들이 있었는데,  

/webapp/WEB-INF/board/아래에 getBoardList.jsp, getBoard.jsp 파일을 옮겨준다.  

(로그인 기능을 실행해보면, 이전까지 잘 실행되던 프로그램이 실행되지 않는다. 만약 로그인에 실패하면 '/WEB-INF/board/login.jsp.jsp' 파일을 실행하고, 로그인에 성공하면 '/WEB-INF/board/getBoardList.do.jsp를 실행하기 때문이다.')  

```xml
<!-- Controller 등록 -->
	<!-- mappings에 등록해둔 /login.do에 대한 매핑인 login을 이곳에 설정 -->
	<bean id="login" class="com.spring.scrapper.controller.LoginController"></bean>
	<bean id="getBoardList" class="com.spring.scrapper.controller.GetBoardListController"></bean>
	<bean id="getBoard" class="com.spring.scrapper.controller.GetBoardController"></bean>
	<bean id="insertBoard" class="com.spring.scrapper.controller.InsertBoardController"></bean>
	<bean id="updateBoard" class="com.spring.scrapper.controller.UpdateBoardController"></bean>
	<bean id="deleteBoard" class="com.spring.scrapper.controller.DeleteBoardController"></bean>
	<bean id="logout" class="com.spring.scrapper.controller.LogoutController"></bean>
	
	<!-- ViewResolver 등록 -->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/board/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
```



**2) Controller 수정**  

ViewResolver를 적용했을 때 ModelAndView객체에 저장되는 View의 이름은 ViewResolver설정을 고려하여 등록한다. (redirect등을 수행할때 지정되는 url경로등이 변경되었으므로 변경해줘야 한다. ViewResolver)  

​    

## 7. (어노테이션을 사용하지 않을 경우) presentation-layer.xml 에 직접 만든 Controller 클래스 등록/코딩

- presentation-layer.xml에 HandlerMapping을 등록하고, bean을 등록하는 과정이다.  
- login.do라는 페이지를 등록하는 과정을 예로 아래와 같다.

**LoginController.java**  

LoginController.java가 implements하는 Controller는 org.springframework.xxx 의 소유이다. 따라서  스프링 컨테이너에서 관리하게 될 Controller 라는 것을 알수 있다.  

```java
package com.spring.scrapper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.scrapper.biz.users.UserDAO;
import com.spring.scrapper.biz.users.UserVO;

public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리 ");
		
		//1. 사용자 입력정보 
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		ModelAndView mav = new ModelAndView();
		if(user!=null) {
			mav.setViewName("getBoardList.do");
		}
		else {
			mav.setViewName("login.jsp");
		}
		
		return null;
	}
	
}
```



**presentation-layer.xml**  

작성한 코드를 스프링 컨테이너가 알아먹기 위해서는 presentation-layer.xml을 아래와 같이 수정한다.  

HandlerMapping으로는 springframework의 SimpleUrlHandlerMapping클래스를 사용했다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- Handler Mapping 등록 -->
	<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
		<property name="mappings">
			<props>
				<prop key="/login.do">login</prop>
			</props>
		</property>
	</bean>
	
	<!-- Controller 등록 -->
	<!-- mappings에 등록해둔 /login.do에 대한 매핑인 login을 이곳에 설정 -->
	<bean id="login" class="com.spring.scrapper.controller.LoginController"></bean>
</beans>
```



## 9.(어노테이션) presentation-layer.xml 설정

- xmlns:context=xmlns:context="http://www.springframework.org/schema/context"
- xsi:schemaLocation에는 아래 두개의 URL을 추가해준다.
- http://www.springframework.org/schema/context
- http://www.springframework.org/schema/context/spring-context-4.2.xsd
- \<context:component-scan base-package="com.spring.scrapper"\>\</context:component-scan\>을 추가해준다.

아래와 같이 presentation-layer.xml 의 내용을 입력한다.  

  

**presentation-layer.xml**  

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-4.2.xsd">
	<context:component-scan base-package="com.spring.scrapper"></context:component-scan>
</beans>
```



## 10. (어노테이션) 리소스 이동(jsp파일들을 모두 src/main/webapp 밑으로 이동)

  

## 11. (어노테이션) @Controller, @RequestMapping, 요청처리로직 작성

여기서는 간단한 샘플코드만을 정리해보고자 한다.  

**src/main/java/com/spring/scrapper/vboard/insertBoardController.java**  

```java
package com.spring.scrapper.vboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.scrapper.vboard.vo.VBoardVO;

@Controller
public class InsertBoardController {
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(VBoardVO vo, VBoardDAO boardDAO){
		boolean result = false;
		try {
			result = boardDAO.insertVBoard(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result){
			return "getBoardList.do";						
		}else{
			return "insertBoard.do";
		}
	}
}
```

사실 VBoardDAO와 같은 DAO 타입의 매개변수를 취하지 않고 @Autowired 어노테이션을 사용해도 된다.하지만, 여기서는 DAO객체를 Command객체와 마찬가지로 매개변수로 선언하면 스프링 컨테이너가 해당객체를 생성해 전달해 준다는 것을 정리하기 위해 예제로 남겨봤다.  

  

Controller메소드가 실행되고 View 경로를 리턴하면 기본이 포워딩 방식이므로 글 등록 후에 목록화면에 출력되도 브라우저의 URL은 변경되지 않는다.  

http://localhost:8080/boardSample/insertBoard.do  

따라서 포워딩이 아니라 리다이렉트를 원할 경우 "redirect:"라는 접두사를 붙여야한다.  

ex)  

```java
@RequestMapping(value="/insertBoard.do")
public String insertBoard(VBoardVO vo, VBoardDAO boardDAO){
    boardDAO.insertVBoard(vo);
    return "redirect:getBoardList.do";
}
```

위와 같이 작성하면 글 등록 처리 후 getBoardList.do로 리다이렉트 되고, 최종 URL은 아래와 같이  

http://localhost:80080/boardSample/getBoardList.do  

가 된다.  

어노테이션 기반의 게시판 개발방식은 SpringMVC_annotation.md 에서 따로 정리할예정이다.  

