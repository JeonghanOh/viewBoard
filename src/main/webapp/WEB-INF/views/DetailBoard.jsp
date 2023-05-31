<%@ page import="java.util.Calendar" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   function moreList() {
       $.ajax({
           url: "/Detail",
           type: "Post",
           cache: false,
           dataType: 'json',
           data: { boardId: ${board.boardId} , page: currentPage },
           success: function(data) {
               var content = "";
               for (var i = 0; i < data.length; i++) {
                   var comment = data[i];
                   content += '<tr>';
                   content += '<td>' + comment.userEmail + '</td>';
                   content += '<td>' + comment.commentContent + '</td>';
                   content += '</tr>';
               }
   	              content += '<div class="input_recomment">';
                  content += '<input type="text"/>';
                  content += '<button type="submit">답글 달기</button>';
                  content += '</div>';
               $('.comment_list').append(content);
               currentPage++;
           },
           error: function(request, status, error) {
               alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
           }
       });
   };
     </script>
 </head>
 <body>
 <%
     CommentRepository commentRepository;
 %>
     <nav>
         <div class="top-nav">
             <div class="top-nav-left">
                 <a href="Home.jsp" class="color_w"><img src="resource/img/Logo.png" style="width: 100px"></a>
             </div>
             <div class="serach">
                 <select onchange="search_()" id=change_select>
                     <option disabled="disabled" selected="selected">검색 조건</option>
                     <option value="Title">제목 검색</option>
                     <option value="Story">작성자 검색</option>
                 </select>
                 <form method="post" action="search_input">
                     <input type="text" placeholder="검색어를 입력" class="serach_text" id="serach_form">
                     <button class="search_btn" type="submit">검색</button>
                 </form>
             </div>
             <div class="top-nav-right">
                 * * * 님
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
     <div class="bodyContainer">
         <div class="content">
             <div class="content_title">
                 <div class="board_name">
                     <h3>게시판 : 개발 게시판</h3>
                 </div>
                 <div class="main_title">
                     제목 : 5월 23일 작성
                     <div class="title_detail">
                         <div class="Write_info">
                             <div>작성자 명 : ${board.userEmail}</div>
                             <div class="ml20">작성일 : 2023-05-23</div>
                         </div>
                         <div class="Board_info">
                             <div>조회수 : 345</div>
                             <div class="ml20">좋아요 수 : 12</div>
                             <div class="ml20">댓글 : 3</div>
                         </div>
                     </div>
                 </div>
             </div>
             <div class="content_body">
                 <h3>본문</h3>
                 <div class="main_text">
                     ${board.boardContent}
                 </div>
                 <div class="Liky_btn">
                     <button>개추
                         (12)
                     </button>
                 </div>
             </div>
             <div class="Arrow_btn">
                             <table>
                                 <tr>
                                     <td class="first_td">이전글</th>
                                     <td>서동현 - 트럼팻 장인</th>
                                 </tr>
                                 <tr>
                                     <td class="first_td">다음글</td>
                                     <td>박재범 - 모몸모몸매</td>
                                 </tr>
                             </table>
                         </div>
             <div class="input_comment">
             <form method="post" action="/main/DetailBoard">
                 <div class="comment_btn">
                     <h3>댓글 작성</h3>
                 </div>
                 <div class="content_container">
                     <input value="${board.boardId}" type="text" name="boardId">
                     <input type="hidden" value="${board.userEmail}" name="userEmail">
                     <textarea rows="4" class="comment_text" name="commentContent"></textarea>
                     <button type="submit">작성하기</button>
                 </div>
                  </form>
             </div>
             <div class="comment_list">
                 <div class="list_container">
                     <h3>댓글</h3>
                             <table>
                                 <tbody>
                                     <c:forEach items="${comment}" var="comment">
                                     <tr>
                                         <td>${comment.userEmail}</td>
                                         <td>${comment.commentContent}</td>
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
                <button onclick="moreList()">더보기</button>
            </div>

         </div>
         <div class="side_bar">
             <div class="login">
                 *** 님
             </div>
             <div class="hot_board">
                 <h4>개발 게시판 베스트 글</h4>
                 <div class="text_width">
                     <div class="text">
                         <a href="#">Spring 존나 어렵다</a>
                     </div>
                     <div class="text">
                         <a href="#">Spring 어렵다는 새기들 특</a>
                     </div>
                     <div class="text">
                         <a href="#">어노테이션 종류가 너무 많음</a>
                     </div>
                     <div class="text">
                         <a href="#">Springboot Lombok</a>
                     </div>
                 </div>
             </div>
         </div>
     </div>
     <div>
     ${board.boardId}
     </div>
 </body>
 </html>