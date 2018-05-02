<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> 글 등록 </title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>    
    
    <script type="text/javascript">
    	function sendData(){
    		for(var i=0; i<document.forms[0].elements.length; i++){
    			if(document.forms[0].elements[i].value==""){
    				alert(document.forms[0].elements[i].name + " 를 입력하지 않았습니다.");
    				document.forms[0].elements[i].focus();
    				return;
    			}
    		}
    		document.forms[0].submit();
    	}
    </script>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	System.out.println(">>>>> writeBoard.jsp <<<<< ");
	/* String seq = request.getParameter("seq"); */
%>
<div class="container">
	<table class="table table-bordered">
		<thead> 글 입력 </thead>
		<tbody>
			<form action="writeBoardProc.jsp" method="post">
				<tr>
					<th>제목 : </th>
					<td><input type="text" name="title" placeholder="제목을 입력하세요." class="form-control"/></td>
				</tr>
				<tr>
					<th>작성자 : </th>
					<td><input type="text" name="writer" placeholder="이름을 입력하세요." class="form-control"/></td>
				</tr>
				<tr>
					<th>id : </th>
					<td><input type="text" name="writer_id" placeholder="작성자 id" class="form-control"/></td>
				</tr>
				<tr>
					<th>내용 : </th>
					<td><textarea rows="8" id="summernote" name="content" placeholder="내용을 입력하세요." class="form-control"></textarea></td>
		            <script>
		            	$(document).ready(function(){
		            		$('#summernote').summernote();
		            	});
		            </script>
				</tr>
				<tr>
					<th>url : </th>
					<td><input type="text" name="url" placeholder="url을 입력하세요" class="form-control"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="등록" onclick="sendData()" class="pull-right"/>
						<input type="button" value="reset" class="pull-left"/>
						<input type="button" value="글 목록으로" class="pull-right" onclick="javascript:location.href='getBoardList.jsp'"/>
					</td>
				</tr>
			</form>
		</tbody>
	</table>
</div>
</body>
</html>