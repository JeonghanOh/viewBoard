<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="viewboard.repository.*"%>
<%@page import="viewboard.entity.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">

</head>
<body>
<% UserEntity user = (UserEntity)session.getAttribute("login");
%>

	<nav>
            <div class="top-nav">
                <div class="top-nav-left">
                    <a href="/main" class="color_w"><img src="resource/img/Logo.png" style="width: 100px"></a>
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
                   님
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

    <div id="container">
	<div id="outside1"></div>

	<div id="main">

		<div id="set1">
			<div id="banner"></div>
			<div id="login">
			   <% if(user!=null){ %>
			           <span><%=user.getUserName()%>님</span>
			           <a href="/auth/logout">로그아웃</a><a>회원탈퇴</a>
			   <% } else{%>
					<button onclick="location.href='/auth/login'">로그인</button>
				<% } %>
			</div>
		</div>

		<div id="set2">
			<div id="best">
			    <c:forEach var="board" items="${hotGesigeul}">
			        <div class="best_detail1">
			            ${board.boardContent}
			        </div>
			    </c:forEach>
			</div>
		</div>

		<div id="set3">
			<div id="gesiSet1">
				<div id="gesi1">
                    <c:forEach var="board" items="${boardList0}">
                        <div id="gesi1Contents">
                            ${board.boardContent}
                        </div>
                    </c:forEach>
				</div>
				<div id="gesi2">
                    <c:forEach var="board" items="${boardList1}">
                        <div id="gesi1Contents">
                            ${board.boardContent}
                        </div>
                    </c:forEach>
				</div>
			</div>
		</div>

		<div id="set4">
			<div id="gesiSet2">
				<div id="gesi3">
                    <c:forEach var="board" items="${boardList2}">
                        <div id="gesi1Contents">
                            ${board.boardContent}
                        </div>
                    </c:forEach>
				</div>
				<div id="gesi4">
                    <c:forEach var="board" items="${boardList3}">
                        <div id="gesi1Contents">
                            ${board.boardContent}
                        </div>
                    </c:forEach>
				</div>
			</div>
		</div>
	</div>

	<div id="outside2">
	    <%
	        for(int i=0;i<10;i++)
	        {
	    %>
	    <div id="hotGesipan">
	    </div>
	    <%
	        }
	    %>
	</div>
</div>
<script>

</script>
</body>
</html>