
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="viewboard.entity.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/service.css"/>
</head>
<body>

<script>
        var a = '${state}';
        if(a){
            alert("회원탈퇴 성공");
            window.onload = function() {
                parent.location.reload(); // 부모 페이지 새로고침
            };
         }
</script>
</body>
</html>