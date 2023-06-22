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

     function update_change() {
        var form = document.getElementById("form_Change");
        form.action = "/admin/update"
        form.submit();
     }

     function delete_change() {
         var form = document.getElementById("form_Change");
         form.action = "/admin/delete"
         form.submit();
     }

     function rollback_change() {
        var form = document.getElementById("form_Change");
        form.action = "/admin/down"
        form.submit();
     }


</script>
<body>
<% UserEntity user = (UserEntity)session.getAttribute("login");%>
 <nav>
    <div id="nav_logo">
        <a href="/main" class="color_w"><img src="/img/adlogo.png" style="width: 150px"></a>
    </div>
    <div id="admin_feature">
        <div class="manage">
            <input type="hidden" value="${user.getUserEmail()}" name="UserEmail">
            <button class="adminButton">유저 관리</button>
        </div>
        <div class="manage">
            <form action="/admin/board" method="get">
                <input type="hidden" value="${user.getUserEmail()}" name="UserEmail">
                <button class="adminButton">게시판 관리</button>
            </form>
        </div>
    </div>
 </nav>
 <div class="container">
    <div class="userManage">
        <h2>회원 관리</h2>
    </div>
    <div class="search">
        <div class="searchDiv">
           <select name="조건" onchange="inputFunction()" name="condition" class="selectBox">
                           <option disabled selected>상세 조건</option>
                           <option value="user_email">email</option>
                           <option value="user_name">이름</option>
                           <option value="user_nickname">닉네임</option>
                           <option value="user_grant">권한</option>
           </select>
            <form action="/admin/search_user" method="get">
                <input class="inputSearch" type="text" placeholder="검색할 단어를 입력하세요">
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
                <form action="" method="post" id="form_Change">
                     <c:forEach var="user" items="${result}">
                     <tr>
                         <td><input type="checkbox" name="user_email" value="${user.getUserEmail()}"></td>
                            <td>${user.getUserEmail()}</td>
                             <td>${user.getUserName()}</td>
                             <td>${user.getUserNickName()}</td>
                              <td>${user.getUserGrant()}</td>
                     </tr>
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
            <button onclick="update_change()">권한 주기</button>
            <button onclick="rollback_change()">권한 뺏기</button>
            <button onclick="delete_change()">삭제하기</button>
    </form>
    </div>
 </div>
</body>
</html>