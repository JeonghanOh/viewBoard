
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
 <div class="main">
        <div class="wrapper">
            <div class="top"><button onclick="loadIdFinder()">이메일찾기</button><button onclick="loadPasswordFinder()">비밀번호찾기</button><button onclick="loadSecession()">회원탈퇴</button></div>
            <div id="container"></div>
        </div>
    </div>
</body>
<script>
        function loadIdFinder() {
          document.getElementById("container").innerHTML = `
            <iframe src="/auth/findid" width="100%" height="500" scrolling ="no"></iframe>
          `;
        }

        function loadPasswordFinder() {
          document.getElementById("container").innerHTML = `
            <iframe src="/auth/findpw" width="100%" height="500" scrolling ="no"></iframe>
          `;
        }
        function loadSecession(){
          document.getElementById("container").innerHTML = `
            <iframe src="/auth/secession" width="100%" height="500" scrolling ="no"></iframe>
          `;
        }
      </script>
</body>
</html>