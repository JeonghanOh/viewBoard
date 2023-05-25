
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뷰보드 - 게시판</title>
    <link rel="stylesheet" href="/css/board.css"/>
</head>
<body>

    <div class="main">
        <div class="wrapper">
            <div class="banner">
                <div class="left">
                    <div class="top">
                        <div class="boardtitle"><h1>**게시판</h1></div>
                        <div class="about"><p>***좋아하는 사람들을 위한 게시판</p></div>
                    </div>
                    <div class="bottom">
                        <h3>실시간 🔥게시글</h3>
                        <ul>
                            <li><a href="#">음 for문 </a></li>
                            <li><a href="#">음 2등</a></li>
                            <li><a href="#">음 3등</a></li>
                            <li><a href="#">음 4등 </a></li>
                            <li><a href="#">음 asdfasdfdsafsdafsfdasdfasdfasdf </a></li>
                            <li><a href="#">음 2등</a></li>
                            <li><a href="#">음 3등</a></li>
                            <li><a href="#">음 4등 </a></li>
                            <li><a href="#">음 for문 </a></li>
                            <li><a href="#">음 2등</a></li>
                        </ul>
                    </div>
                </div>
                <div class="right">
                    <div class="login">
                        <h2>로그인</h2>
                        <form action="/signin" method="post">
                            <label >이메일</label>
                            <input type="text" name="email">
                            <label>비밀번호</label>
                            <input type="text" name="password">
                            <button type="submit" id="signin">로그인</button>
                            <ul>
                                <li><a href="#">회원가입</a></li>
                                <li><a href="#">이메일/비밀번호 찾기</a></li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
            <div class="section">
                            <% for(int i=0; i<10;i++){ %>
                            <div class="board">
                                <div class="letter">
                                    <div class="title">아이뻐</div>
                                    <div class="writer">김누구</div>
                                    <div class="content">이번이번이번 이번 이번 이이이이이번asddddddddddddddasdfasdfsadfsdafsdafsadsafdsafs</div>
                                      <ul>
                                            <li>조회수</li>
                                            <li>좋아요</li>
                                            <li>댓글수</li>
                                      </ul>
                                </div>
                                <div class="image">
                                    <img src="./toeic.png" style="width: 100%; height: 100%;">
                                </div>
                                </div>
                                <% } %>
                <div class="pagination">
                    <ul class="page">
                        <% for(int j=1;j<=10;j++){ %>
                        <li><a href="#"><%=j%></a></li>
                           <% }%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>