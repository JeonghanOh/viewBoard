
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
<% UserEntity user = (UserEntity)session.getAttribute("login"); %>
<nav>
                <div class="top-nav">
                    <div class="top-nav-left">
                        <a href="/main" class="color_w"><img src="/img/logo.png" style="width: 100px"></a>
                    </div>
                    <div class="search">

                        <form method="get" action="/main/searchresult">
                            <input type="text" placeholder="검색어를 입력" class="search_text" id="search_form" name="query">
                            <input type="hidden" name="page" value="0">
                            <button class="search_btn" type="submit">검색</button>
                        </form>
                    </div>
                    <div class="top-nav-right">
                       <% if(user!= null){%>
                        <%=user.getUserName()%>
                       <%}else{%>
                       <a href="/auth/login">로그인</a>
                       <%}%>
                    </div>
                </div>
                <hr>
                <div class="bot">
                    <div class="bot-nav">
                        <div class="w150">회원</div>
                        <div class="w150">회원 2</div>
                        <div class="w150">조회</div>
                        <div class="w150">좋아요</div>
                        <div class="w150">리뷰</div>
                    </div>
                </div>
                <div class="hide-nav">
                    <div class="hide_nav_width">
                        <div class="around">
                            <div class="pt20">회원 가입</div>
                            <div class="pt20">회원 가입 정규식</div>
                            <div class="pt20">로그인</div>
                            <div class="pt20">로그아웃</div>

                        </div>
                        <div class="around">
                            <div class="pt20">내 정보 보기</div>
                            <div class="pt20">내 정보 변경</div>
                            <div class="pt20">아아디 찾기</div>
                            <div class="pt20">비밀 번호 찾기</div>
                            <div class="pt20">회원 탈퇴</div>
                        </div>
                        <div class="around">
                            <div class="pt20">이름순 정렬 조회</div>
                            <div class="pt20">좋아요순 정렬 조회</div>
                            <div class="pt20">조회순 정렬 조회</div>
                            <div class="pt20">검색어 검색</div>
                        </div>
                        <div class="around">
                            <div class="pt20">좋아요</div>
                            <div class="pt20">좋아요 해제</div>
                            <div class="pt20">좋아요 작품 보기</div>
                            <div class="pt20">좋아요 수 표시</div>
                        </div>
                        <div class="around">
                            <div class="pt20">리뷰 작성</div>
                            <div class="pt20">작품 최근 리뷰 보기</div>
                            <div class="pt20">작성한 리뷰 보기</div>
                        </div>
                    </div>
                </div>
            </nav>

 <div class="main">
        <div class="wrapper">
        <% if(user!= null ){ %>
            <div class="top"><button onclick="loadSecession()">회원탈퇴</button> </div>

          <% } else {%>
            <div class="top"><button onclick="loadIdFinder()">이메일찾기</button><button onclick="loadPasswordFinder()">비밀번호찾기</button> </div>
            <% } %>
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