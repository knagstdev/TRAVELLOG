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
        <!-- 소셜 로그인 -->
        <div class="social-divider">
            <div class="divider-line"></div>
            <span class="divider-text">또는</span>
            <div class="divider-line"></div>
        </div>
        <div class="social-buttons">
            <!-- 구글 -->
            <button class="social-btn google-btn" onclick="location.href='/oauth2/authorization/google'">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48">
                    <path fill="#EA4335" d="M24 9.5c3.9 0 6.6 1.7 8.1 3.1l6-5.9C34.6 3.6 29.7 1.5 24 1.5 14.9 1.5 7.1 7.3 4.1 15.2l7.1 5.5C12.9 15.1 18 9.5 24 9.5z"/>
                    <path fill="#4285F4" d="M46.1 24.5c0-1.6-.1-3.2-.4-4.7H24v9.1h12.6c-.5 2.7-2 5-4.2 6.5l6.5 5.1c3.8-3.5 6.2-8.7 6.2-15z"/>
                    <path fill="#FBBC05" d="M11.2 28.9c-1-2.7-1-5.6 0-8.3L4.1 15c-2.5 5-2.5 11 0 16l7.1-5.1z"/>
                    <path fill="#34A853" d="M24 46.5c5.7 0 10.6-1.9 14.1-5.2l-6.5-5.1c-2 1.4-4.6 2.3-7.6 2.3-6 0-11.1-5.6-12.8-11.1l-7.1 5.5C7.1 40.7 14.9 46.5 24 46.5z"/>
                </svg>
            </button>
            <!-- 카카오 -->
            <button class="social-btn kakao-btn" onclick="location.href='/login/oauth2/code/kakao'">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                    <path fill="#3C1E1E" d="M16 3C8.8 3 3 7.9 3 14c0 3.5 2.1 6.6 5.5 8.6-.2.7-.9 3.3-1 3.8 0 0-.1.5.3.3.4-.2 3.8-2.5 4.4-2.9.9.1 1.7.2 2.8.2 7.2 0 13-4.9 13-11S23.2 3 16 3z"/>
                </svg>
            </button>
            <!-- 네이버 -->
            <button class="social-btn naver-btn" onclick="location.href='/oauth2/authorization/naver'">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                    <path fill="#03C75A" d="M7 5h6.3l5.7 8.8V5H25v22h-6.3l-5.7-8.8V27H7z"/>
                </svg>
            </button>
        </div>
    </div>
</div>
</body>
</html>
