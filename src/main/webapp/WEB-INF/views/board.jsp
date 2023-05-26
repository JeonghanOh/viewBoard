
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="viewboard.entity.BoardEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/board.css"/>
</head>
<body>
    <div class="main">
        <div class="wrapper">
            <div class="banner">
                <div class="left">
                    <div class="top">
                        <div class="boardtitle"><h1>${boardType.boardName}게시판</h1></div>
                        <div class="about"><p>${boardType.boardIntro}</
                        p></div>
                    </div>
                    <div class="bottom">
                        <h3>실시간 🔥게시글</h3>
                        <ul>
                        <c:forEach var="hot" items="${hotBoard}">
                            <li><a href="#">${hot.boardTitle}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
                <div class="right">
                     <div class="login">
                         <form action="">
                            <button type="button" id="signin">로그인</button>
                            <ul>
                                <li><a href="#">회원가입</a></li>
                                <li><a href="#">이메일/비밀번호 찾기</a></li>
                            </ul>
                         </form>
                      </div>
                      <div class="ad">
                                    d
                      </div>
                </div>
            </div>
            <div class="section">
                           <c:forEach var="board" items="${boardDetail}">
                               <div class="board">
                                    <div class="letter">
                                   <div class="title">${board.boardTitle}</div>
                                   <div class="writer">${board.userEmail}<span>${board.boardDate}</span></div>
                                    <div class="content">${board.boardContent}</div>
                                      <ul>
                                            <li>조회수${board.boardClick}</li>
                                            <li>좋아요${board.boardLike}</li>
                                            <li>댓글수${board.commentCount}</li>
                                      </ul>
                                </div>
                                <div class="image">
                                    <img src="./toeic.png" style="width: 100%; height: 100%;">
                                </div>
                                </div>
                               </c:forEach>
                <div class="pagination">
                    <ul class="page">
                        <% for(int j=1;j<=10;j++){ %>
                        <li><a href="#"><%=j%></a></li>
                           <% }%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>