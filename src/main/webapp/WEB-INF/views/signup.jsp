<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HelloWorld!</title>
    <link rel="stylesheet" href="/css/signup.css"/>
</head>
<body>
<nav>
                <div class="top-nav">
                    <div class="top-nav-left">
                        <a href="/main" class="color_w"><img src="/img/logo.png" style="width: 100px"></a>
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
        </div>
        <div class="wrap_b">
            <div class="lo">
            <h1><a href="#" class="logo"><span class="hidden">뷰 보드</span></a></h1>
            </div>
                        <form name="form1" method="post" action="signup" enctype="application/x-www-form-urlencoded">
                       <label for="">이메일</label><br>
                       <input type="text" name="userEmail" placeholder="@포함 이메일양식 " maxlength="20" id="id" value="${dto.userEmail}" />
                       <p class="error" id="fir">${errors.getFieldError("userEmail").defaultMessage}</p>
                       <label for="">비밀번호</label><br>
                       <input type="password" name="userPassword" id="pw"  value="${dto.userPassword}"/>
                       <p class="error" id="sec">${errors.getFieldError("userPassword").defaultMessage}</p>
                       <label for="">비밀번호 재확인</label><br>
                       <input type="password" name="userPasswordChk" id="pwchk" value="${dto.userPasswordChk}" />
                       <label for="">이름</label><br>
                       <input type="text" name="userName" id="name"value="${dto.userName}" />
                       <p class="error" id="fou">${errors.getFieldError("userName").defaultMessage}</p>
                       <label for="">닉네임</label><br>
                       <input type="text" name="userNickname" id="nickname" value="${dto.userNickname}"/>
                        <p class="error" id="fou">${errors.getFieldError("userNickname").defaultMessage}</p>
                       <label for="">휴대전화</label><br>
                       <input type="text" placeholder="'-'를제외한 숫자11자리를 입력해주세요. " class="phone" id="pn"  name="userPhoneNumber" value="${dto.userPhoneNumber}"/>
                       <p class="error" id="pd">${errors.getFieldError("userPhoneNumber").defaultMessage}</p>
                       <button class="sign" type="submit" onclick="inputChk()">가입하기</button>
                   </form>
        </div>

</body>
</html>