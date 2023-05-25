<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css">

</head>
<body>
	<nav id="nav1">
		<div id="logo">logo</div>
		<div id="search">search</div>
	</nav>
	<nav id="nav2">
		<div id="menu">메뉴</div>
	</nav>

    <div id="container">
	<div id="outside1"></div>

	<div id="main">

		<div id="set1">
			<div id="banner"></div>
			<div id="login">
				<form>
					<input type="text" id="loginId"> <input type="password"
						id="loginPw"> <input type="submit" value="로그인">
				</form>
			</div>
		</div>

		<div id="set2">
			<div id="best">
			    <%
			        for(int i=0;i<5;i++){
			    %>
			        <div id="best_detail1">
			        </div>

			        <div id="best_detail2">
                    </div>
			    <%
			        }
			    %>
			</div>
		</div>

		<div id="set3">
			<div id="gesiSet1">
				<div id="gesi1">

				    <%
                        for(int i=0;i<5;i++){
                    %>
                        <div id="gesi1Contents">
                    	</div>
                    <%
                    	}
                    %>
				</div>
				<div id="gesi2">

				    <%
                        for(int i=0;i<5;i++){
                    %>
                        <div id="gesi1Contents">
                    	</div>
                    <%
                    	}
                    %>
				</div>
			</div>
		</div>

		<div id="set4">
			<div id="gesiSet2">
				<div id="gesi3">

				    <%
                        for(int i=0;i<5;i++){
                    %>
                        <div id="gesi1Contents">
                    	</div>
                    <%
                    	}
                    %>
				</div>
				<div id="gesi4">

				    <%
                        for(int i=0;i<5;i++){
                    %>
                        <div id="gesi1Contents">
                    	</div>
                    <%
                    	}
                    %>
				</div>
			</div>
		</div>
	</div>

	<div id="outside2">
	    <%
	        for(int i=0;i<10;i++)
	        {
	    %>
	    <div id="hotGesipan">
	    </div>
	    <%
	        }
	    %>
	</div>
</div>
</body>
</html>