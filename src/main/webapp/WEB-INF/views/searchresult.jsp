<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="viewboard.repository.*"%>
<%@page import="viewboard.entity.*"%>
<%@page import="viewboard.dto.*"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.data.domain.Page"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/searchResult.css">

</head>
<body>
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
              <hr>
              <div class="bot">
                  <div class="bot-nav">
                      <div class="w150">게시판</div>
                      <div class="w150">회원 2</div>
                      <div class="w150">조회</div>
                      <div class="w150">좋아요</div>
                      <div class="w150">리뷰</div>
                  </div>
              </div>
              <div class="hide-nav">
                  <div class="hide_nav_width">
                      <div class="around">
                          <c:forEach var="board" items="${allBoard}">
                              <div class="pt20"><a href="/main/board/${board.boardType}">${board.boardName}</a></div>
                          </c:forEach>
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

   <div id="main">
      '<%=request.getAttribute("query")%>' 검색 결과

      <%
            List <BoardEntity> res = (List <BoardEntity>)request.getAttribute("result");
            ArrayList <BoardTypeEntity> bteList = (ArrayList <BoardTypeEntity>)request.getAttribute("bteList");
        %>
        <div id="mainhead"></div>

      <div id="result">
          <%
              for(int i=0;i<res.size();i++){

          %>
              <div class="resBox" onclick="location.href='/main/detailboard/<%=res.get(i).getBoardId()%>'">
                <div class="box">
                    <div id="box1"><%=bteList.get(i).getBoardName()%></div>
                    <div id="box2">
                        <div id="box2_1"><%=res.get(i).getBoardTitle()%></div>
                        <div id="box2_2"><%=res.get(i).getBoardContent()%></div>
                    </div>
                    <%
                        String a = res.get(i).getBoardImage() == null?null:res.get(i).getBoardImage().substring(70);
                        if(a == null){
                    %>
                    <div id="box3"><img src="/img/none.png" class="imgbox3" width=150 height=150></div>
                    <%
                        }
                        else{
                            System.out.println(a);
                    %>
                    <div id="box3"><img src="/img/<%=a%>" class="imgbox3" width=150 height=150></div>
                    <%
                        }
                    %>
                </div>
              </div>
            <%
                }
            %>
            <div class="pagination">

                    <ul class="page">
                <c:forEach begin="${startpage}" end="${endpage}" var="pageNum">
                                    <c:choose>
                                    <c:when test="${pageNum != nowpage}">
                                        <li><a href="/main/searchresult?query=${query}&page=${pageNum-1}">${pageNum}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="/main/searchresult?query=${query}&page=${pageNum-1}"><strong style="color:red">${pageNum}</strong></a></li>
                                    </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                               </ul>
            </div>
      </div>

      <footer id="footer">
            <div class="buttons"></div>
      </footer>
   </div>
</body>
</html>