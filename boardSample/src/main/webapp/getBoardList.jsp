<%@page import="com.spring.scrapper.biz.vboard.VBoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> 글 목록 </title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
//	int pageCnt = VBoardDAO
	VBoardDAO boardDAO = new VBoardDAO();
	// 추후 페이징 처리 적용
	// 참고 url : https://github.com/wonderboy39/Model2/blob/master/miracle/WebContent/listAllWithPaging.jsp
	List<VBoardVO> contents = null;
	contents = boardDAO.getBoardList(new VBoardVO());
	session.setAttribute("contents", contents);
%>
</body>
<h5>접속 사용자 : <%=session.getAttribute("idKey")%> </h5>
<div class="container">
	<form action="getBoardList.do" method="post">
		<table class="table table-striped table-bordered table-hover">
			<caption></caption>
			<tbody>
				<tr>
					<td>
						<select name="searchCondition">	
							<c:forEach items="${conditionMap}" var="item">
								<option value="${item.value}"> ${item.key}
							</c:forEach>
						</select>
						<input name="searchKeyword" type="text" /> 
						<input type="submit" value="검색" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<table class="table table-striped table-bordered table-hover">
		<caption></caption>
		<thead>
			<tr>
				<th> 글 번호 </th>
				<th> 제목 </th>
				<th> 작성자 </th>
			</tr>
		</thead>
		<tbody>
			<% if(contents.size() == 0) {%>
				<tr>
					<td> --- </td>
					<td> 현재 등록된 게시물이 없습니다. </td>
					<td> --- </td>
				</tr>
			<%}else{
				for(VBoardVO content : contents ){%>
					<tr>
						<td><%= content.getSeq() %></td>
						<td><a href="getBoard.jsp?seq=<%= content.getSeq()%>"><%=content.getTitle()%></a></td>
						<td><%= content.getWriter()%></td>
					</tr>
				<%}%>
				
			<%}%>
		</tbody>
	</table>
</div>
</html>