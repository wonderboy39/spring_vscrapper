<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAO"%>
<%@page import="com.spring.scrapper.biz.vboard.VBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	VBoardVO vo = new VBoardVO();
	vo.setTitle(request.getParameter("title"));
	vo.setWriter(request.getParameter("writer"));
	vo.setWriter_id(request.getParameter("writer_id"));
	vo.setContent(request.getParameter("content"));
	vo.setUrl(request.getParameter("url"));
	vo.setSeq(Integer.parseInt(request.getParameter("seq")));
	
	VBoardDAO dao = new VBoardDAO();
	dao.updateBoard(vo);
	
	response.sendRedirect("getBoardList.jsp");
%>