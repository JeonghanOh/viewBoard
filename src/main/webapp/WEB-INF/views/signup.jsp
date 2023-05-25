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
     <div class="main">
        </div>
        <div class="wrap_b">
            <div class="lo">
            <h1><a href="#" class="logo"><span class="hidden">뷰 보드</span></a></h1>
            </div>
            <form name="form1" method="post" action="signup" encType="UTF-8">
                <label for="" >이메일</label><br>
                <input type="text" name="userEmail" placeholder="@포함 이메일양식 " maxlength="20"  id="id" onblur="blurIt('fir')"/>
                <p class="dan" id="fir">필수 정보입니다</p>
                <label for="">비밀번호</label><br>
                <input type="password" name="userPassword" id="pw" onblur="blurIt('sec')"/>
                <p class="dan" id="sec">필수 정보입니다</p>
                <label for="">비밀번호 재확인</label><br>
                <input type="password" name="userPasswordChk" id="pwchk" onblur="blurIt('thi')"/>
                <p class="dan" id="thi">필수 정보입니다</p>
                <label for="">이름</label><br>
                <input type="text" name="userName" id="name" onblur="blurIt('fou')"/>
                <p class="dan" id="fou">필수 정보입니다</p>
                <label for="">닉네임</label><br>
                <input type="text" name="userNickname" id="name" onblur="blurIt('five')"/>
                <p class="dan" id="five">필수 정보입니다</p>
                <label for="">휴대전화</label><br>
                <input type="text" placeholder="전화번호를 입력해주세요 " class="phone" id="pn"  onblur="blurIt('pd')" name="userPhoneNumber">
                <p class="dan" id="pd">전화번호는 숫자만 입력해주세요 (필수)</p>
                <button class="sign" type="submit" onclick="inputChk()">가입하기</button>
            </form>
        </div>

</body>
</html>