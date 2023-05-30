
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/service.css"/>
</head>
<body>
 <div class="container">
         <h2>회원 탈퇴</h2>
         <h5>정말 회원탈퇴를 원하시면 비밀번호를 입력해주세요.</h5>
         <form action="cancel" method="post">
         <input type="hidden" name="userEmail" value="qwer1234@naver.com">
         <input type="password" placeholder="비밀번호 입력" class="password" name="userPassword">
         <button type="submit">탈퇴</button>
         </form>
     </div>
</body>
</body>
</html>