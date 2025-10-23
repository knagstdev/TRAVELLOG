<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>TRAVELOG 로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="login-wrapper">
    <div class="login-box">
        <div class="logo-area">
            <h1>🐶 TRAVELOG</h1>
            <a href="${pageContext.request.contextPath}/" class="home-icon" title="홈으로">🏠</a>
        </div>
        <p class="subtitle">여행을 기록하고, 추억을 남기세요 ✈️</p>

        <form action="/loginProc" method="post">
            <input type="text" name="username" placeholder="아이디"><br>
            <input type="password" name="password" placeholder="비밀번호"><br>
            <button type="submit">로그인</button>
        </form>



        <div class="links">
            <a href="${pageContext.request.contextPath}/join">회원가입</a>|
            <a href="${pageContext.request.contextPath}/findId">아이디 찾기</a>|
            <a href="${pageContext.request.contextPath}/findPw">비밀번호 찾기</a>

        </div>

        <div class="divider">또는</div>

        <!-- 소셜 로그인 -->
        <div class="social-divider">
        </div>
        <div class="social-buttons">

            <!-- 카카오 -->
            <button class="social-btn kakao-btn">
                <%--                            onclick="location.href='/oauth2/authorization/kakao'">--%>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                    <path fill="#3C1E1E" d="M16 3C8.8 3 3 7.9 3 14c0 3.5 2.1 6.6 5.5 8.6-.2.7-.9 3.3-1 3.8 0 0-.1.5.3.3.4-.2 3.8-2.5 4.4-2.9.9.1 1.7.2 2.8.2 7.2 0 13-4.9 13-11S23.2 3 16 3z"/>
                </svg>
            </button>
            <!-- 네이버 -->
            <button class="social-btn naver-btn">
                <%--                            onclick="location.href='/oauth2/authorization/naver'">--%>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                    <path fill="#03C75A" d="M7 5h6.3l5.7 8.8V5H25v22h-6.3l-5.7-8.8V27H7z"/>
                </svg>
            </button>
        </div>
    </div>
</div>
    </div>
</div>
</body>
</html>
