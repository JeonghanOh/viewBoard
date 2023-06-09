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
   	              content += '<div class="input_recomment">';
                  content += '<input type="text"/>';
                  content += '<button type="submit">답글 달기</button>';
                  content += '</div>';
               $('.table_body').append(content);
               currentPage++;
           },
           error: function(request, status, error) {
               alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
           }
       });
   };

    $(document).ready(function() {
          var isLiked = $('#liked_btn').data('isliked'); // data-* 속성을 사용하여 isLiked 값을 읽어옵니다.
          if (isLiked) {
              $('#liked_btn').text("좋아요 - 1");
          } else {
              $('#liked_btn').text("좋아요 + 1");
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
             likeCount--;
             like_view.text("좋아요 + 1");
          } else {
             likeCount++;
             like_view.text("좋아요 - 1");
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

        if (contentHeight > 400) {
            div.style.overflow = 'hidden';
            div.style.height = '400px';
            div.style.overflowX = 'hidden';
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
                 <% if(user!=null){ %>
                			           <span><%=user.getUserName()%>님</span>
                			           <a href="/auth/logout">로그아웃</a><a>회원탈퇴</a>
                			   <% } else{%>
                					<button onclick="location.href='/auth/login'">로그인</button>
                				<% } %>
             </div>
         </div>
         <hr> <div class="bot">
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
     <div class="bodyContainer">
         <div class="content">
             <div class="content_title">
                 <div class="board_name">
                     <h3>${type.boardName} 게시판</h3>
                 </div>
                 <div class="main_title">
                     <div id="title_font">글 제목 : ${board.boardTitle} </div>
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
                 </div>
                 <div class="Liky_btn">
                 <% if(user!=null){ %>
                    <button type="button" onclick="liked('${board.boardId}', '<%=user.getUserEmail()%>')">
                        <div id="liked_btn">

                        </div>
                    </button>
                <div class="liky-container">
                 <div class="like-count">${board.boardLike}</div>
                 </div>
                    <% } else{%>
                    <a href="/auth/login">로그인</a>
                    <% } %>
                 </div>
             </div>
             <div class="Arrow_btn">
                             <div class="btn_another_width">
                                 <div class="btn_another">
                                <c:if test="${Prev != null}">
                                 <a href = "/main/detailboard/${Prev.boardId}">
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
                                 <a href = "/main/detailboard/${Next.boardId}">
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
                 <div class="content_container">
                     <input value="${board.boardId}" type="hidden" name="boardId">
                     <input type="hidden" value="${board.userEmail}" name="userEmail">
                     <textarea rows="4" class="comment_text" name="commentContent"></textarea>
                     <button type="submit" id="write_btn">작성하기</button>
                 </div>
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
                <button onclick="moreList(${board.boardId})">더보기</button>
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
                         <p><strong><%=user.getUserName()%></strong>님</p>
                         <p>작성한 게시물 : 100개</p>
                         <p><a href="#">마이페이지</a></p>
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
                           <c:forEach var="hot" items="${hotBoard}">
                                <li><a href="/main/detailboard/${hot.boardId}">${hot.boardTitle}</a></li>
                           </c:forEach>
                      </ul>
                 </div>
             </div>
         </div>
     </div>
     <div>
     ${board.boardId}
     </div>
 </body>
 </html>