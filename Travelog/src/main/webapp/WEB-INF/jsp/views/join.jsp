<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/join.css">
</head>
<body>
<div class="join-box">
    <h2>회원가입</h2>
    <form action="${pageContext.request.contextPath}/join" method="post">
        <input type="text" name="userId" placeholder="아이디를 입력하세요">
        <input type="password" name="userPw" placeholder="비밀번호를 입력하세요">
        <input type="password" name="userPwCheck" placeholder="비밀번호를 다시 입력하세요">
        <input type="email" name="email" placeholder="이메일을 입력하세요">
        <button type="submit">가입하기</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">로그인으로 돌아가기</a>
</div>
</body>
</html>
