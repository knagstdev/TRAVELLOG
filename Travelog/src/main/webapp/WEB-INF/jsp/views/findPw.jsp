<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findPw.css">
</head>
<body>
<div class="pw-box">
    <h2>비밀번호 찾기</h2>
    <form action="${pageContext.request.contextPath}/findPw" method="post">
        <input type="text" name="userId" placeholder="아이디를 입력하세요">
        <input type="email" name="email" placeholder="이메일을 입력하세요">
        <button type="submit">비밀번호 찾기</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">로그인으로 돌아가기</a>
</div>
</body>
</html>
