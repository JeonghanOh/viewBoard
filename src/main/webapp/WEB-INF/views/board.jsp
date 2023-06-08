
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="viewboard.entity.BoardEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="viewboard.entity.UserEntity"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/board.css"/>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
<% UserEntity user = (UserEntity)session.getAttribute("login");%>

    <nav>
                <div class="top-nav">
                    <div class="top-nav-left">
                        <a href="/main" class="color_w"><img src="/img/logo.png" style="width: 100px"></a>
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

    <div class="main">
        <div class="wrapper">
            <div class="banner">
                <div class="left">
                <form  action ="/main/favorite" method="post">
                    <input type="hidden" name= "userEmail" value="dkdfl1235@naver.com">
                    <input type="hidden" name= "boardType" value="${board.boardType}">
                    <div class="top">
                        <div class="boardtitle"><h1>${board.boardName}게시판</h1></div>
                        <div class="about"><p>${board.boardIntro}</p><button type="submit">⭐</button></div>
                    </div>
                    </form>
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
                       <p><strong><%=user.getUserName()%></strong>님</p>
                       <p>작성한 게시물 : 100개</p>
                       <p><a href="#">마이페이지</a></p>
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
                                   <div class="board" onclick=location.href="/main/DetailBoard/${board.boardId}">
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
                                       <c:choose>
                                           <c:when test="${board.boardImage.length() > 70}">
                                        <img src="/img/${board.boardImage.substring(70)}" style="width: 100%; height: 100%;">
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
                                 <li><a href="/main/board/${boardType}?page=${pageNum-1}"><strong style="color:red">${pageNum}</strong></a></li>
                             </c:otherwise>
                         </c:choose>
                     </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>