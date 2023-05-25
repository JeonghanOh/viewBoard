
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
<div class="main">
              <div class="wrapper">
                  <div class="row">
                          <h3>글작성</h3>
                          <form action="/write" method="post">
                          <input type="hidden" value="wnsqja@naver.com" name="userEmail"/>
                              <div id="title">
                                   <select name="boardType">
                                        <option value="1">개발 게시판</option>
                                        <option value="2">자격증 게시판</option>
                                        <option value="3">JOB 게시판</option>
                                        <option value="4">유머 게시판</option>
                                        <option value="5">부산 게시판</option>
                                   </select>
                                  <input type="text" name="boardTitle" style="width:100%">
                              </div>
                              <div id="smarteditor">
                              <textarea
                                      name="boardContent"
                                      id="editorTxt"
                                      rows="20"
                                      cols="10"
                                      placeholder="내용을 입력해주세요"
                                      style="width: 100%"></textarea>
                              </div>
                              <button type="submit" onclick="submitPost()">작성하기</button>
                          </form>
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