<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="viewboard.entity.*"%>
<%@page import="viewboard.repository.*"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 글쓰기</title>
    <link rel="stylesheet" href="/css/board.css"/>
    <script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
  <%
         CommentRepository commentRepository;
         UserEntity user = (UserEntity) session.getAttribute("login");
         List<BoardEntity> board = (List<BoardEntity>) request.getAttribute("BoardContent");
  %>
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
<div class="main" >
              <div class="wrapper">
                  <div class="row">
                          <h3>글작성</h3>
                          <% if(board==null) { %>
                              <form action="/main/write" method="post" enctype="multipart/form-data">
                                  <input type="hidden" value="<%=user.getUserEmail()%>" name="userEmail" />
                                  <div id="title">
                                      <select name="boardType">
                                          <c:forEach var="board" items="${allBoard}">
                                              <option value=${board.boardType}>${board.boardName}</option>
                                          </c:forEach>
                                      </select>
                                      <input type="text" name="boardTitle" style="width:100%">
                                  </div>
                                  <div id="smarteditor">
                                      <textarea name="boardContent" id="editorTxt" rows="20" cols="10" placeholder="내용을 입력해주세요" style="width: 100%"></textarea>
                                  </div>
                                  <input type="file" class="upload" name="image" accept="image/$">
                                  <button type="submit" onclick="submitPost()">작성하기</button>
                              </form>
                          <% } else { %>
                              <form action="/main/write/update" method="post" enctype="multipart/form-data">
                                  <input type="hidden" value="<%=user.getUserEmail()%>" name="userEmail" />
                                  <div id="title">
                                      <select name="boardType">
                                          <c:forEach var="boards" items="${allBoard}">
                                            <option value=${boards.boardType}>${boards.boardName}</option>
                                          </c:forEach>
                                      </select>
                                      <input type="hidden" value="<%= board.get(0).getBoardId()%>" name="boardId">
                                      <input type="text" name="boardTitle" style="width:100%" value="<%=board.get(0).getBoardTitle()%>">
                                  </div>
                                  <div id="smarteditor">
                                      <textarea name="boardContent" id="editorTxt" rows="20" cols="10" placeholder="내용을 입력해주세요" style="width: 100%">
                                          <%=board.get(0).getBoardContent() %>
                                      </textarea>
                                  </div>
                                  <input type="file" class="upload" name="image" accept="image/$">
                                  <input type="checkbox" name="deleteImage" value="true"> 기존 이미지 삭제
                                  <%=board.get(0).getBoardImage()%>
                                  <button type="submit" onclick="submitPost()">수정하기</button>
                              </form>
                          <% } %>
                          <!-- 포스트 추가하는 곳-->
                      </div>
              </div>
          </div>
          <script>
                 let oEditors = [];
                 function smartEditor() {
               console.log("Naver SmartEditor");
               nhn.husky.EZCreator.createInIFrame({
                   oAppRef: oEditors,
                   elPlaceHolder: "editorTxt",
                   sSkinURI: "/smarteditor/SmartEditor2Skin.html",
                   fCreator: "createSEditor2"
               });
           }

           document.addEventListener("DOMContentLoaded", function() {
               smartEditor();
           });

           /* 버튼 클릭 이벤트 */
           submitPost = function() {
               oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", [])
               let content = document.getElementById("editorTxt").value

               //if(content == '') {
               if(content == '<p>&nbsp;</p>') { //비어있어도 기본 P태그가 붙더라.
                   alert("내용을 입력해주세요.")
                   oEditors.getById["editorTxt"].exec("FOCUS")
                   return
               } else {
                   console.log(content)
               }
           }
        </script>
</body>
</html>