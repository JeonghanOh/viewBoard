
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
<% UserEntity user=(UserEntity)session.getAttribute("login"); %>
    <nav>
        <div class="top-nav">
            <div class="top-nav-left">
                <a href="/main" class="color_w"><img src="/img/logo.png" style="width: 150px"></a>
            </div>
            <div class="search">

                <form method="get" action="/main/searchresult">
                    <input type="text" placeholder="검색어를 입력" class="search_text" id="search_form" name="query">
                    <input type="hidden" name="page" value="0">
                    <button class="search_btn" type="submit">검색</button>
                </form>
            </div>
            <div class="top-nav-right">
                <% if(user!=null){%>
                    <%=user.getUserName()%>
                        <%}else{%>
                            <a href="/auth/login">로그인</a>
                            <%}%>
            </div>
        </div>
    </nav>

        <div class="main">
            <div class="wrapper">
                <% if(user!=null ){ %>
                    <div class="top"><button onclick="loadSecession()">회원탈퇴</button> </div>

                    <% } else {%>
                        <div class="top"><button onclick="loadIdFinder()">이메일찾기</button><button
                                onclick="loadPasswordFinder()">비밀번호찾기</button> </div>
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
            function loadSecession() {
                document.getElementById("container").innerHTML = `
                <iframe src="/auth/secession" width="100%" height="500" scrolling ="no"></iframe>
              `;
            }
        </script>
    </body>
    </html>