<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="viewboard.entity.*"%>
<%@page import="viewboard.repository.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/MyPage.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    function ChangeNick() {
        $('.NickName_Fix').css('display','block');
        $('#Change').css('display','none');
    }
    </script>
</head>
<body>
     <%
         CommentRepository commentRepository;
         UserEntity user = (UserEntity) session.getAttribute("login");
     %>
     <% if (user == null) { %>
     <script>
           alert("로그인 이후에 사용 가능합니다.");
            location.href="/main"
     </script>
     <% } else { %>
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
    <div class="bodyContainer">
        <div class="Nickname">
         <% String FixName = (String) request.getAttribute("FixName"); %>
         <% if (FixName == null) { %>
             <h3>${NickName}</h3>
         <% } else { %>
             <h3><%= FixName %></h3>
         <% } %>
                <span>
                <button onclick="ChangeNick()" id="Change"> 닉네임 변경 </button>
                <form method="post" action="/auth/mypage" id="form_con">
                    <input type="hidden" value="<%=user.getUserEmail()%>" name="UserEmail">
                    <input type="text" class="NickName_Fix" style="display:none;" name="UserNickName">
                    <button type="submit" style="display:none;" class="NickName_Fix">수정</button>
                </form>
                </span>
        </div>
        <div class="sub_con">
            <div class="user_info">
                <h3>유저 정보</h3>
                <div class="user_information"> 이름 : <%=user.getUserName()%></div>
                <div class="user_information"> 이메일 : <%=user.getUserEmail()%></div>
                <div class="user_information"> 전화 번호 :<%=user.getUserPhoneNumber()%></div>
            </div>
            <div class="liky_board">
                <h3>즐겨 찾는 게시판</h3>
                 <c:forEach var="fav" items="${favorite}">
                     <div id="like_board_list"><a href="/main/board/${fav.boardType}">${fav.boardName}</a></div>
                 </c:forEach>
            </div>
        </div>
        <div class="post_board">
            <h3>게시글 목록</h3>
            <div>
                <c:forEach var="MyBoard" items="${Title}">
                    <a href="/main/detailboard/${MyBoard.boardId}"><div class="write_boardList"><div id="board_title">글 제목 : ${MyBoard.boardTitle}</div><div id="like_count">좋아요 수 : ${MyBoard.boardLike}</div><div id="view_count">조회 수 : ${MyBoard.boardClick}</div></div></a>
                </c:forEach>
            </div>
        </div>
        <div class="postBoard_number">
        <ul>
          <c:forEach begin="${startpage}" end="${endpage}" var="pageNum">
            <c:choose>
              <c:when test="${pageNum != nowpage}">
                <li><a href="/auth/mypage?UserEmail=${param.UserEmail}&page=${pageNum-1}">${pageNum}</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="/auth/mypage?UserEmail=${param.UserEmail}&page=${pageNum-1}"><strong style="color:red">${pageNum}</strong></a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          </ul>
        </div>
    </div>
    <% } %>
</body>
</html>