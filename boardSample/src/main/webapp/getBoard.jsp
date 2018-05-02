<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAO"%>
<%@page import="com.spring.scrapper.biz.vboard.impl.VBoardDAOSpring"%>
<%@page import="com.spring.scrapper.biz.vboard.VBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글 상세 보기</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	function go_modify(f){
//    		f.action="modify.jsp";
			f.action="modifyBoard.jsp";
    		f.submit();
    	}
    	
    	function go_delete(f){
//    		f.action="delete.jsp";
			f.action="deleteBoard.jsp";
    		f.submit();
    	}
    	
    	function go_write(f){
    		//alert("go_write() begin");
    		f.action="writeBoard.jsp";
    		f.submit();
    	}
    </script>
    
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	System.out.println("---");
//	VBoardDAOSpring boardDao = new VBoardDAOSpring();
	VBoardDAO dao = new VBoardDAO();
	VBoardVO vo = new VBoardVO();
	
	String seq = request.getParameter("seq");
	System.out.println("seq :: " + seq); 

	vo.setSeq(Integer.parseInt(seq));
	//VBoardVO board = boardDao.getBoard(vo);
	VBoardVO board = dao.getBoard(vo);
%>

<div class="container">
	<form method="post">
		<input type="hidden" name="seq" value="<%=seq%>"/>
		<table class="table table-striped">
			<thead>
				<th colspan="2">
					<p align="center"><%=board.getTitle()%></p>
				</th>
			</thead>
			<tbody>
				<tr>
					<td> 작성자 : <%=board.getWriter()%></td>
					<td> 글 번호 : <%=seq%></td>
				</tr>
				<tr>
					<td colspan="2">
						<p><%=board.getContent()%></p>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<button class="btn btn-primary pull-left"> 글 목록으로 </button>
						<button class="btn btn-primary pull-right" onclick="go_delete(this.form)" > 글 삭제 </butoon>
						<button class="btn btn-primary pull-right" onclick="go_modify(this.form)" > 글 수정 </button>
						<button class="btn btn-primary pull-right" onclick="go_write(this.form)" > 글 등록 </button>
					</td>
				</tr>
			</tbody>
		</table>
	</form> 
</div>
</body>
</html>