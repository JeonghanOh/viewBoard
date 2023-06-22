<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<style>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Orbit&display=swap');
</style>
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

   <script>
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