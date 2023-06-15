<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/ad_board.css"/>
</head>
<body>
 <nav>
 d
 </nav>
 <div class="container">
    <div class="left">
        <div class="container2">
        <form method="post" action="/admin/board/delete">
          <div class="head"><h2>게시판</h2><button type="submit">삭제</button></div>
          <table>
          <tr>
          <th>체크</th>
          <th>게시판</th>
          <th>게시판 설명</th>
          </tr>
                <c:forEach var="all" items="${board}">
                   <tr>
                   <td class="fir"><input type="checkbox" name="boardType" value="${all.boardType}"/></td>
                   <td class="sec">${all.boardName}</td>
                   <td class="third">${all.boardIntro}</td>
                   </tr>
                   </c:forEach>
          </table>

                <div class="pagination">
                    <ul class="page">
                        <c:forEach begin="${startpage}" end="${endpage}" var="pageNum">
                            <c:choose>
                                <c:when test="${pageNum != nowpage}">
                                    <li><a href="/admin/board?page=${pageNum-1}">${pageNum}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/admin/board?page=${pageNum-1}"><strong
                                                style="color:red">${pageNum}</strong></a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                </form>
                </div>
    </div>
    <div class="right">
        <div class="top">
         <h2>게시판 수정</h2>
                    <form action="/admin/board/update" method="post">
                            <input type="text" name="boardTitle" placeholder="기존 게시판 이름">
                            <input type="text" name="boardTitle2" placeholder="변경할 게시판 이름">
                            <button type="submit">수정</button>
                            </form>
        </div>
        <div class="bottom">
                      <h2>게시판 추가</h2>
                            <form action="/admin/board/add" method="post">
                            <input type="text" name="boardTitle" placeholder="추가할 게시판 이름"><br>
                            <textarea  name="boardInfo" placeholder="간단한 게시판 설명" maxlength="26"></textarea><br>
                            <button type="submit" class="add">추가</button>
                            </form>
        </div>


    </div>
 </div>
</body>
</body>
</html>