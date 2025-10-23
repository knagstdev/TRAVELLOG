<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findId.css">
</head>
<body>
<div class="find-box">
    <h2>아이디 찾기</h2>
    <form action="${pageContext.request.contextPath}/findId" method="post">
        <input type="text" name="name" placeholder="이름을 입력하세요">
        <input type="email" name="email" placeholder="이메일을 입력하세요">
        <button type="submit">아이디 찾기</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">로그인으로 돌아가기</a>
</div>
</body>
</html>
