<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="viewboard.entity.*"%>
<%@page import="viewboard.repository.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="/css/DetailBoard.css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    var currentPage = 2;
    function moreList(boardId) {
        $.ajax({
            url: "/main/detail",
            type: "Post",
            cache: false,
            dataType: 'json',
            data: { boardId: boardId , page: currentPage },
            success: function(data) {
                var content = "";
                for (var i = 0; i < data.length; i++) {
                    var comment = data[i];
                    content += '<tr class="tr_width">';
                    content += '<td class="first_width">' + comment.userEmail + '</td>';
                    content += '<td class="second_width">' + comment.commentContent + '</td>';
                    content += '</tr>';
                }
                $('.table_body').append(content);
                currentPage++;
                if (data.length < 10) {
                    $('#More_view_btn').hide(); // 더 이상 댓글이 없는 경우 버튼 숨기기
                }
            },
            error: function(request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    };

    $(document).ready(function() {
        var commentCount = ${comment.size()};
            if (commentCount < 10) {
                $('#More_view_btn').hide();
            }
    });


    function liked(boardId, userEmail) {
        $.ajax({
            url: "/main/like",
            type: "POST",
            data: { boardId: boardId, userEmail: userEmail },
            success: function(isLiked) {
                var likeCountElement = $('.like-count');
                var like_view = $('#liked_btn');
                var likeCount = parseInt(likeCountElement.text());
                if (isLiked) {
                    likeCount++;
                    like_view.text("❤");
                } else {
                    likeCount--;
                    like_view.text("🤍");
                }
                likeCountElement.text(likeCount);
            },
            error: function() {
              console.error("요청이 실패했습니다.");
            }
        });
    }


    document.addEventListener('DOMContentLoaded', function() {
        var div = document.getElementById('main_text');
        var contentHeight = div.scrollHeight;

        if (contentHeight > 300) {
            div.style.overflow = 'hidden';
            div.style.height = 'auto';
            div.style.overflowX = 'auto';
        } else {
            div.style.overflow = 'visible';
            div.style.height = '300px';
            div.style.overflowX = 'auto';
        }
    });
     </script>
 </head>
 <body>
 <%
 CommentRepository commentRepository;
 UserEntity user = (UserEntity) session.getAttribute("login");
 BoardEntity board = (BoardEntity) request.getAttribute("board");
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
 <div class="bodyContainer">
     <div class="content">
         <div class="content_title">
             <div class="board_name">
                 <h3>${type.boardName} 게시판</h3>
             </div>
             <div class="main_title">
                 <div id="title_font">
                    <div>글 제목 : ${board.boardTitle}</div>
                    <div id="form_btn">
                            <% if(user!=null && user.getUserEmail().equals(board.getUserEmail())) {%>
                                         <form action="/main/delete" method="post">
                                            <input type="hidden" name="boardId" value="${board.boardId}">
                                            <input type="hidden" name="userEmail" value="${board.userEmail}">
                                            <input type="hidden" name="boardType" value="${board.boardType}">
                                            <button type="submit" class="btn_board_update">삭제하기</button>
                                         </form>
                                         <form action="/main/write/update" method="get">
                                          <input type="hidden" name="boardId" value="${board.boardId}">
                                          <button type="submit" class="btn_board_update">수정하기</button>
                                         </form>
                            <%}%>
                    </div>
                 </div>
                 <div class="title_detail">
                     <div class="Write_info">
                         <div>작성자 명 : ${board.userEmail}</div>
                         <div class="ml20">작성일 : ${fn:substring(board.boardDate, 0, 10)}</div>
                     </div>
                     <div class="Board_info">
                         <div>조회수 : ${board.boardClick}</div>
                         <div class="ml20">좋아요 수 : ${board.boardLike}</div>
                         <div class="ml20">댓글 : ${count}</div>
                     </div>
                 </div>
             </div>
         </div>
         <div class="content_body">
             <h3>본문</h3>
             <div class="main_text" id="main_text">
                 ${board.boardContent}
                     <c:choose>
                         <c:when test="${board.boardImage.length() > 70}">
                             <img src="/img/${board.boardImage.substring(70)}" style="width: 100%; height: 100%;">
                         </c:when>
                     </c:choose>
             </div>
                 <% if(user!=null){ %>
                 <div class="Liky_btn">
                 <button type="button" onclick="liked('${board.boardId}', '<%=user.getUserEmail()%>')" class="BTN_LIKE">
                     <div id="liked_btn">
                         <c:choose>
                             <c:when test="${Likestatus}">
                                 🤍
                             </c:when>
                             <c:otherwise>
                                 ❤
                             </c:otherwise>
                         </c:choose>
                     </div>
                 </button>
                 </div>
                  <div class="liky-container">
                       <div class="like-count"> ${board.boardLike} </div>
                  </div>
                 <% } else{%>
                 <div class="Liky_btn">
                 <a href="/auth/login">로그인</a>
                 </div>
                 <% } %>
         </div>
         <div class="Arrow_btn">
             <div class="btn_another_width">
                 <div class="btn_another">
                     <c:if test="${Prev != null}">
                         <a href="/main/detailboard/${Prev.boardId}">
                             <div id="Prev_btn">
                                 <div class="first_td">이전글</div>
                                 <div class="second_td">${Prev.boardTitle}</div>
                             </div>
                         </a>
                     </c:if>
                 </div>
             </div>
             <div class="btn_another_width">
                 <div class="btn_another">
                     <c:if test="${Next != null}">
                         <a href="/main/detailboard/${Next.boardId}">
                             <div id="Next_btn">
                                 <div class="first_td">다음글</div>
                                 <div class="second_td">${Next.boardTitle}</div>
                             </div>
                         </a>
                     </c:if>
                 </div>
             </div>
         </div>
         <div class="input_comment">
             <form method="post" action="/main/detailboard">
                 <div class="comment_btn">
                     <h3>댓글 작성</h3>
                 </div>
                 <% if(user!=null){ %>
                 <div class="content_container">
                     <input value="${board.boardId}" type="hidden" name="boardId">
                     <input type="hidden" value="<%=user.getUserEmail()%>" name="userEmail">
                     <textarea rows="4" class="comment_text" name="commentContent"></textarea>
                     <button type="submit" id="write_btn">작성하기</button>
                 </div>
                 <% } else { %>
                  <div id="comment_login"><a href="/auth/login">로그인</a></div>
                 <% } %>
             </form>
         </div>
         <div class="comment_list">
             <div class="list_container">
                 <h3>댓글</h3>
                 <table>
                     <tbody class="table_body">
                     <c:forEach items="${comment}" var="comment">
                         <tr class="tr_width">
                             <td class="first_width">${comment.userEmail}</td>
                             <td class="second_width">${comment.commentContent}</td>
                         </tr>
                     </c:forEach>
                     </tbody>
                 </table>
                 <div class="input_recomment">
                     <input type="text"/>
                     <button type="submit">답글 달기</button>
                 </div>
             </div>
         </div>
         <div class="more_btn">
             <button onclick="moreList(${board.boardId})" id="More_view_btn">더보기</button>
         </div>
     </div>
     <div class="side_bar">
         <div class="login">
             <% if(user==null){ %>
             <div class="Before_login">
                 <button type="button" id="signin" onclick="location.href='/auth/login'">로그인</button>
                 <ul>
                     <li><a href="/auth/signup">회원가입</a></li>
                     <li><a href="/auth/service">이메일/비밀번호 찾기</a></li>
                 </ul>
             </div>
             <% } else {%>
             <div class="After_login">
                 <div id="output_name">
                 <div><strong><%=user.getUserName()%></strong>님</div>
                 <form action="/auth/mypage" method="get">
                    <input type="hidden" value="<%=user.getUserEmail()%>" name="UserEmail">
                    <button id ="mypage_btn">마이 페이지</button>
                 </form>
                 </div>
                 <p>작성한 게시물 : ${board_count} </p>
                 <p><a href="/main/write" class="write">글쓰기</a></p>
                 <ul>
                     <li><a href="/auth/logout">로그아웃</a></li>
                     <li><a href="/auth/service">회원탈퇴</a></li>
                 </ul>
             </div>
             <% } %>
         </div>
         <div class="hot_board">
             <h4>개발 게시판 베스트 글</h4>
             <div class="text_width">
                 <ul>
                     <c:forEach var="hot" items="${allBoard}">
                         <li><a href="/main/detailboard/${hot.boardId}">${hot.boardTitle}</a></li>
                     </c:forEach>
                 </ul>
             </div>
         </div>
     </div>
 </div>
 </body>
 </html>