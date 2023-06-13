<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="viewboard.repository.*"%>
<%@page import="viewboard.entity.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Orbit&display=swap');
</style>
</head>
<body>
<% UserEntity user = (UserEntity)session.getAttribute("login");
%>
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
         <c:forEach var="i" begin="0" end="${fn:length(hotBoard) - 1}" varStatus="status">
             <c:if test="${status.index < 7}">
                 <a href="/main/board/${hotBoard[i].boardType}">
                     <li><span>${hotBoard[i].boardName.substring(0,1)}</span></li>
                     <span>${hotBoard[i].boardName}</span>
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
    <div id="container">
       <div id="outside1"></div>

       <div id="main">

          <div id="set1">
             <div id="banner"></div>
             <div id="login">
                <c:choose>
                        <c:when test = "${user == null}">
                           <button id="loginButton" onclick="location.href='/auth/login'">로그인</button>
                        </c:when>
                        <c:otherwise>
                           <div class="myInfo"><h4>${user.getUserNickName()}님</h4></div>
                           <div class="myInfo"><a href="#">작성한 게시물 : ${user.getBoardCount()}개</a></div>
                           <div class="myInfo2"><a href="/main/write">글쓰기</a> <a href="/auth/logout">로그아웃</a> <a href="/auth/service">회원탈퇴</a></div>
                               <form action="/auth/mypage" method="get">
                                    <input type="hidden" value="${user.getUserEmail()}" name="UserEmail">
                                    <button id="mypageButton">마이 페이지</button>
                               </form>
                        </c:otherwise>
                   </c:choose>
             </div>
          </div>
    <h2 class="bugu">핫   게시물</h2>
          <div id="set2">
             <div id="best">
                 <c:forEach var="board" items="${hotGesigeul}">
                     <div class="best_detail1" onclick="location.href='/main/detailboard/${board.boardId}'">
                         <h4>${board.boardTitle}</h4>
                         ${board.boardContent}
                     </div>
                 </c:forEach>
             </div>
          </div>
              <h2 class="bugu">오늘의 게시판</h2>
          <div id="set3">
             <div id="gesiSet1">
                <div id="gesi1" onclick="location.href='/main/board/${board0.boardType}'">
                <h3>${board0.boardName}</h3>
                        <c:forEach var="board" items="${boardList0}">
                            <div id="gesi1Contents">
                                <h4>${board.boardTitle}</h4>
                                ${board.boardContent}
                            </div>
                        </c:forEach>
                </div>
                <div id="gesi2" onclick="location.href='/main/board/${board1.boardType}'">
                <h3>${board1.boardName}</h3>
                        <c:forEach var="board" items="${boardList1}">
                            <div id="gesi1Contents">
                                <h4>${board.boardTitle}</h4>
                                ${board.boardContent}
                            </div>
                        </c:forEach>
                </div>
             </div>
          </div>

          <div id="set4">
             <div id="gesiSet2">
                <div id="gesi3" onclick="location.href='/main/board/${board2.boardType}'">
                <h3>${board2.boardName}</h3>
                        <c:forEach var="board" items="${boardList2}">
                            <div id="gesi1Contents">
                                 <h4>${board.boardTitle}</h4>
                                ${board.boardContent}
                            </div>
                        </c:forEach>
                </div>
                <div id="gesi4" onclick="location.href='/main/board/${board3.boardType}'">
                <h3>${board3.boardName}</h3>
                        <c:forEach var="board" items="${boardList3}">
                            <div id="gesi1Contents">
                                 <h4>${board.boardTitle}</h4>
                                ${board.boardContent}
                            </div>
                        </c:forEach>
                </div>
             </div>
          </div>
       </div>


    </div>
    <script>
        function hidenavArea(){
            var sel = document.querySelector('.hide-nav');
            var len = ${fn:length(allBoard)};
            sel.style.height=len*40+'px';
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