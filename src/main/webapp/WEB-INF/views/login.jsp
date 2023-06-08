<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/css/Login.css">

</head>
<body>
    <form action="loginResult" method="post">
        <div id="container">
            <div class="set">
                <div id="set0"><a href="/main"><img id="setimg" src="/img/logo.png"></a></div>
                <div id="set1">아이디</div>
                <div id="set2"><input type="text" id="loginId" name="id"></div>
                <div id="set3">비밀번호</div>
                <div id="set4"><input type="password" id="loginPw" name="password"></div>
                <div id="set5"><input type="submit" id="loginButton" value="로그인"></div>
                <div id="set6"><a href="/auth/service">아이디/비밀번호 찾기</a> <a href="/auth/signup">회원가입</a></div>
            </div>
        </div>
    </form>
</body>
</html>