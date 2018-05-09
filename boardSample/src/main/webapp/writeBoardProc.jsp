<%-- <%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAO"%>
<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardService"%>
<%@page import="com.spring.scrapper.biz.vboard.VBoardVO"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String writer_id = request.getParameter("writer_id");
	String content = request.getParameter("content");
	String url = request.getParameter("url");
	
	VBoardVO boardVO = new VBoardVO();
	boardVO.setTitle(title);
	boardVO.setWriter(writer);
	boardVO.setContent(content);
	boardVO.setUrl(url);
	boardVO.setCnt(0);
	
	VBoardDAO dao = new VBoardDAO();
	dao.insertBoard(boardVO);
	
	response.sendRedirect("getBoardList.jsp");
%> --%>