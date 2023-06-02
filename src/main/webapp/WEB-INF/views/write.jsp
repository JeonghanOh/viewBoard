
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
<nav>
            <div class="top-nav">
                <div class="top-nav-left">
                    <a href="Home.jsp" class="color_w"><img src="resource/img/Logo.png" style="width: 100px"></a>
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
                  <div class="row">
                          <h3>글작성</h3>
                          <form action="/main/write" method="post" enctype="multipart/form-data">
                          <input type="hidden" value="wnsqja@naver.com" name="userEmail"/>
                              <div id="title">
                                   <select name="boardType">
                                        <option value="1">개발 게시판</option>
                                        <option value="2">자격증 게시판</option>
                                        <option value="3">JOB 게시판</option>
                                        <option value="4">유머 게시판</option>
                                        <option value="5">부산 게시판</option>
                                        <option value="6">노래 게시판</option>
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
                              <input type="file" class="upload" name="image" accept="image/$"/>
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