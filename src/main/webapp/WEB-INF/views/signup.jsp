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
                       <input type="text" placeholder="전화번호를 입력해주세요 " class="phone" id="pn"  name="userPhoneNumber" value="${dto.userPhoneNumber}"/>
                       <p class="error" id="pd">${errors.getFieldError("userPhoneNumber").defaultMessage}</p>
                       <button class="sign" type="submit" onclick="inputChk()">가입하기</button>
                   </form>
        </div>

</body>
</html>