
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
                          <form action="insertStudentInfoForm" method="post">
                              <div id="title">

                                  <input type="text" name="title" style="width:100%">
                              </div>
                              <div id="smarteditor">
                                  <textarea
                                      name="editorTxt"
                                      id="editorTxt"
                                      rows="20"
                                      cols="10"
                                      placeholder="내용을 입력해주세요"
                                      style="width: 100%"></textarea>
                              </div>
                              <input type="button" value="작성하기" onclick="submitPost()"/>
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
        </script>
</body>
</html>