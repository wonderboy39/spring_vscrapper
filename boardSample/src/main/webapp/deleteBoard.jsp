<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAO"%>
<%@page import="com.spring.scrapper.biz.vboard.VBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String seq = request.getParameter("seq");
	VBoardVO vo = new VBoardVO();
	vo.setSeq(Integer.parseInt(seq));

	VBoardDAO dao = new VBoardDAO();
	dao.deleteBoard(vo);
	response.sendRedirect("getBoardList.jsp");
%>