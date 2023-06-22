
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="viewboard.entity.BoardEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="viewboard.entity.UserEntity"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/board.css"/>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Orbit&display=swap');
</style>
</head>
<body>
<% UserEntity user=(UserEntity)session.getAttribute("login");%>
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

            </div>
        </div>

    </nav>
<div class="container">
        <ul class="hot">
         <c:forEach var="i" begin="0" end="${fn:length(hot) - 1}" varStatus="status">
             <c:if test="${status.index < 7}">
                 <a href="/main/board/${hot[i].boardType}">
                     <li><span>${hot[i].boardName.substring(0,1)}</span></li>
                     <span>${hot[i].boardName}</span>
                 </a>
             </c:if>
         </c:forEach>

        <a onclick="navArea()" class="plus">+</a>
        </ul>
         <div class="all">
           <h4>전체 게시판</h4>
           <ul>
            <c:forEach var="board" items="${allBoard}">
                         <a href="/main/board/${board.boardType}">
                         <li><span>${board.boardName}</span></li>
                         </a>
                     </c:forEach>
           </ul>
         </div>
         </div>
    <div class="main">
        <div class="wrapper">
            <div class="banner">
                <div class="left">
                    <% if(user==null){%>
                        <div class="top">
                            <div class="boardtitle">
                                <h1>${board.boardName}게시판</h1>
                            </div>
                            <div class="about">
                                <p>${board.boardIntro}</p><button onclick="notSignIn()">⭐</button>
                            </div>
                        </div>
                        <%}else{%>
                            <form action="/main/favorite" method="post">
                                <input type="hidden" name="userEmail" value="<%=user.getUserEmail()%>">
                                <input type="hidden" name="boardType" value="${board.boardType}">
                                <div class="top">
                                    <div class="boardtitle">
                                        <h1>${board.boardName}게시판</h1>
                                    </div>
                                    <div class="about">
                                        <p>${board.boardIntro}</p>
                                        <button type="submit">
                                            <c:choose>
                                                <c:when test="${isfavorite}">
                                                    🌟
                                                </c:when>
                                                <c:otherwise>
                                                    ⭐
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </div>
                                </div>
                                <%}%>
                            </form>
                            <div class="bottom">
                                <h3 class="color">실시간 🔥게시글</h3>
                                <ul>
                                    <c:forEach var="hot" items="${hotBoard}">
                                        <li><a href="/main/detailboard/${hot.boardId}">${hot.boardTitle}</a></li>
                                    </c:forEach>

                                </ul>
                            </div>
                </div>
                <div class="right">
                    <% if(user==null){ %>
                        <div class="login">

                            <button type="button" id="signin" onclick="location.href='/auth/login'">로그인</button>
                            <ul>
                                <li><a href="/auth/signup">회원가입</a></li>
                                <li><a href="/auth/service">이메일/비밀번호 찾기</a></li>
                            </ul>

                        </div>
                        <% } else{%>
                            <div class="login">
                                <p><strong>
                                        <%=user.getUserNickName()%>
                                    </strong>님</p>
                                <p>작성한 게시물 :
                                            ${boardcount}개

                                </p>
                                <form action="/auth/mypage" method="get">
                                    <input type="hidden" value="<%=user.getUserEmail()%>" name="UserEmail">
                                    <button>마이 페이지</button><a href="/main/write" class="write">글쓰기</a>
                                </form>

                                <ul>
                                    <li><a href="/auth/logout">로그아웃</a></li>
                                    <li><a href="/auth/service">회원탈퇴</a></li>
                                </ul>
                            </div>
                            <% } %>
                                <div class="ad">
                                    <h3> 🔥게시판</h3>
                                    <ul>
                                        <c:forEach var="hot" items="${hotType}">
                                            <li><a href="/main/board/${hot.boardType}">${hot.boardName} 게시판</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                </div>
            </div>
            <div class="section">
                <c:forEach var="board" items="${boardDetail}">
                    <div class="board" onclick=location.href="/main/detailboard/${board.boardId}">
                        <div class="letter">
                            <div class="title">${board.boardTitle}</div>
                            <div class="writer">${board.userEmail}<span>${fn:substring(board.boardDate, 0, 10)}</span></div>
                            <div class="content">${board.boardContent}</div>
                            <ul>
                                <li>조회수${board.boardClick}</li>
                                <li>좋아요${board.boardLike}</li>
                                <li>댓글수${board.commentCount}</li>
                            </ul>

                        </div>
                        <div class="image">
                            <c:choose>
                                <c:when test="${board.boardImage.length() > 70}">
                                    <img src="/img/${board.boardImage.substring(70)}"
                                        style="width: 100%; height: 100%;">
                                </c:when>
                                <c:otherwise>
                                    <img src="/img/none.png" style="width: 100%; height: 100%;">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
                <div class="pagination">
                    <ul class="page">
                        <c:forEach begin="${startpage}" end="${endpage}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum != nowpage}">
                                    <li><a href="/main/board/${board.boardType}/?page=${pageNum-1}">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/main/board/${boardType}?page=${pageNum-1}"><strong
                                                style="color:red">${pageNum}</strong></a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>
        function notSignIn() {
            alert("로그인 후 이용가능합니다");
        }

                    function navArea(){
                        var plus = document.querySelector('.plus');
                        var sel = document.querySelector('.all');
                        var sel2 = document.querySelector('.container');

                        if(sel.style.display=='block'){
                            sel.style.display='none';
                            sel2.style.boxShadow="none";
                            sel2.style.border='none';
                            plus.innerText = '+';
                        }
                        else{
                            plus.innerText = '×';
                            sel.style.display='block';
                            sel2.style.boxShadow="0 0 0 1px #e3e5e8, 0 4px 8px 0 rgba(0,0,0,.3)";
                        }
                    }
    </script>

</body>
</html>