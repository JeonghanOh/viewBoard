<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                   <form method="post" action="search_input">
                       <input type="text" placeholder="검색어를 입력" class="search_text" id="search_form">
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
        <div class="Nickname">
            <h3>* * * 님</h3>
        </div>
        <div class="sub_con">
            <div class="user_info">
                <h3>유저 정보</h3>
            </div>
            <div class="liky_board">
                <h3>즐겨 찾는 게시판</h3>
            </div>
        </div>
        <div class="post_board">
            <h3>게시글 목록</h3>
        </div>
        <div class="postBoard_number">
            <div>1 2 3 4 5</div>
        </div>
    </div>
</body>
</html>