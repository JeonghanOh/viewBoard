
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
         <h2>이메일 찾기</h2>
         <h5>이메일은 가입때 기입한 이름과 전화번호로 찾을수 있습니다</h5>
         <form action="findid" method="post">
         <input type="text" placeholder="이름" class="name" name="userName">
         <input type="text" placeholder="'-'없이 전화번호 11자리" class="pnum" name="userPhonenumber">
         <button type="submit">찾기</button>
         </form>
         ${find}
         </div>
</body>
</body>
</html>