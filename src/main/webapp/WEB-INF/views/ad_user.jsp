<%@ page import="java.util.Calendar" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@page import="viewboard.entity.*"%>
 <%@page import="viewboard.repository.*"%>
 <%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/ad_user.css"/>
</head>
<body>
 <nav>

 </nav>
 <div class="container">
    <div class="userManage">
        <h2>회원 관리</h2>
    </div>
    <div class="search">
        <div class="searchDiv">
            <select name="조건" onchange="inputFunction()" name="condition" class="selectBox">
                            <option value="email">email</option>
                            <option value="name">이름</option>
                            <option value="nickname">닉네임</option>
                            <option value="grant">권한</option>
                        </select>
            <form action="/admin/search_user" method="get">
            <input class="inputSearch" type="text" placeholder="검색할 단어를 입력하세요" name="user_name">
            <button type="submit" class ="btnSearch">검색</button>
            </form>
        </div>
    </div>
    <div class="userInfo">
        <table class="userList">
            <thead class="thead">
                <th class="th_check">체크여부</th>
                <th class="th_email">email</th>
                <th>이름</th>
                <th>닉네임</th>
                <th class="th_grant">권한</th>
            </thead>
            <tbody>
                 <c:forEach var="user" items="${result}">
                    이메일 : ${user.getUserEmail()}
                     이름 : ${user.getUserName()}
                 </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
    <c:forEach begin="${startpage}" end="${endpage}" var="pageNum">
                                  <c:choose>
                                    <c:when test="${pageNum != nowpage}">
                                      <li><a href="/admin/search_user?page=${pageNum-1}&amp;user_email=${searchuserDto.user_email}&amp;user_name=${searchuserDto.user_name}&amp;user_nickname=${searchuserDto.user_nickname}">${pageNum}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                      <li><a href="/admin/search_user?page=${pageNum-1}" style="color:red"><strong>${pageNum}</strong></a></li>
                                    </c:otherwise>
                                  </c:choose>
    </c:forEach>
    </div>
    <div class="button">
        <button type="button">권한 주기/뺏기</button>
        <button type="button">삭제하기</button>
    </div>
 </div>
<script>
     function inputFunction(){
         var sel = document.querySelector(".inputSearch");
         var opt = document.querySelector(".selectBox");
         var len = opt.options.length;
         for(let i=0;i<len;i++){
             if(opt.options[i].selected == true){
                 sel.name = opt.options[i].value;
             }
         }
     }
  </script>
</body>
</html>