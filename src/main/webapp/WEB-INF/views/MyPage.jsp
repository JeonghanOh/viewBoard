<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/MyPage.css">
</head>
<body>
   <nav>
           <div class="top-nav">
               <div class="top-nav-left">
                   <a href="Home.jsp" class="color_w"><img src="resource/img/Logo.png" style="width: 100px"></a>
               </div>
               <div class="search">
                   <select onchange="search_()" id=change_select>
                       <option disabled="disabled" selected="selected">검색 조건</option>
                       <option value="Title">제목 검색</option>
                       <option value="Story">작성자 검색</option>
                   </select>
                   <form method="get" action="/main/searchResult">
                            <input type="text" placeholder="검색어를 입력" class="serach_text" id="serach_form" name="query">
                            <input type="hidden" name="page" value="0">
                       <button class="search_btn" type="submit">검색</button>
                   </form>
               </div>
               <div class="top-nav-right">

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
        <div class="Nickname">
            <h3>${info.userNickName}님</h3> <button>수정</button>
            <form action="/user/fix" method="post"><input type="text" name="fix" /><button type="submit">수정하기</button></form>
        </div>
        <div class="sub_con">
            <div class="user_info">
                <h3>유저 정보</h3>
                ${info.userEmail}
                ${info.userName}
                ${info.userPhoneNumber}
            </div>
            <div class="liky_board">
                <h3>즐겨 찾는 게시판</h3>
                   <c:forEach var="fav" items="${favorite}">
                       <a href="/main/board/${fav.boardType}">${fav.boardType}</a>
                   </c:forEach>
            </div>
        </div>
        <div class="post_board">
            <h3>게시글 목록</h3>
            <c:forEach var="mine" items="${mine}">
                                   <a href="/main/board/${fav.boardId}">${mine.boardTitle}</a>
            </c:forEach>
        </div>
        <div class="postBoard_number">
            <div>1 2 3 4 5</div>
        </div>
    </div>
</body>
</html>