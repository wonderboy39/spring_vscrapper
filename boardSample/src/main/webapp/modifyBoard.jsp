<%@page import="com.spring.scrapper.vboard.vo.VBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> 글 수정 페이지 </title>
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
</head>
<body>

</body>
<div class="header">
</div>
<div class="container">
    <form method="post" action="updateBoard.do">
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <td colspan="2"> 글 수정 페이지</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td> 제목 </td>
                    <td><input type="text" name="title" value="${board.title}" class="form-control"/></td>
                </tr>
                
                <tr>
                    <td>작성자</td>
                    <td><input type="text" name="writer" value="${board.writer}" class="form-control"/></td>
                </tr>
                <tr>
                    <td>id</td>
                    <td><input type="text" name="writer_id" value="${board.writer_id}" class="form-control"/></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td><textarea id="summernote" name="content" class="form-control">${board.content}</textarea></td>
                    <script>
                    	$(document).ready(function(){
                    		$('#summernote').summernote();
                    	});
                    </script>
                </tr>
                <tr>
                    <td>URL</td>
                    <td><input type="text" name="url" value="${board.url}" class="form-control"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="seq" value="${board.seq}"/>
                        <input type="reset" name="reset" value="초기화" class="btn pull-left"/>
                        <input type="submit" name="control" value="글 등록" class="btn btn-primary pull-right"/>
                    </td>
                </tr>
                
            </tbody>
        </table>
    </form>
</div>
</html>