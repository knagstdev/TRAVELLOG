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
    <form id="joinForm" action="${pageContext.request.contextPath}/join" method="post">

        <!-- 아이디 입력 + 중복검사 -->
        <div class="input-group">
            <input type="text" id="loginId" name="loginId" placeholder="아이디 입력" required>
        </div>
        <span id="idCheckMsg" class="msg"></span>

        <!-- 비밀번호 -->
        <div class="form-field">
            <input type="password" id="password" name="password" placeholder="비밀번호 입력" required>
            <span id="pwCheckMsg" class="msg"></span>
        </div>

        <!-- 비밀번호 확인 -->
        <div class="form-field">
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" required>
            <span id="pwMatchMsg" class="msg"></span>
        </div>



        <!-- 이메일 입력 + 인증 -->
        <div class="input-group">
            <input type="text" id="email" name="email" placeholder="이메일 입력" required>
            <button type="button" class="check-btn" id="sendMailBtn">인증번호 전송</button>
        </div>
        <span id="emailCheckMsg" class="msg"></span>

        <!-- 닉네임 입력 + 중복검사 -->
        <div class="input-group">
            <input type="text" id="nickname" name="nickname" placeholder="닉네임 입력" required>
        </div>
        <span id="nickCheckMsg" class="msg"></span>

        <button type="submit">회원가입</button>
    </form>

    <a href="${pageContext.request.contextPath}/login">로그인으로 돌아가기</a>
</div>

<script src="${pageContext.request.contextPath}/js/join.js"></script>
</body>
</html>
