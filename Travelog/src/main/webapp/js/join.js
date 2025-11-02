document.addEventListener("DOMContentLoaded", () => {
    const loginIdInput = document.getElementById("loginId");
    const nicknameInput = document.getElementById("nickname");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const confirmPwInput = document.getElementById("confirmPassword"); // ✅ 추가
    const form = document.getElementById("joinForm");
    const idMsg = document.getElementById("idCheckMsg");
    const nickMsg = document.getElementById("nickCheckMsg");
    const emailMsg = document.getElementById("emailCheckMsg");
    const pwMsg = document.getElementById("pwCheckMsg");      // ✅ 추가
    const pwMatchMsg = document.getElementById("pwMatchMsg");  // ✅ 추가

    // 🔹 아이디 중복 검사
    loginIdInput.addEventListener("input", async () => {
        const loginId = loginIdInput.value.trim();
        if (loginId.length < 5) {
            idMsg.textContent = "아이디는 5자 이상 입력해주세요.";
            idMsg.style.color = "gray";
            return;
        }

        const res = await fetch(`/checkId?loginId=${encodeURIComponent(loginId)}`);
        const exists = await res.json();
        idMsg.textContent = exists ? "이미 사용 중인 아이디입니다." : "사용 가능한 아이디입니다.";
        idMsg.style.color = exists ? "red" : "green";
    });

    // 🔹 닉네임 중복 검사
    nicknameInput.addEventListener("input", async () => {
        const nickname = nicknameInput.value.trim();
        if (nickname.length < 2) {
            nickMsg.textContent = "닉네임은 2자 이상 입력해주세요.";
            nickMsg.style.color = "gray";
            return;
        }

        const res = await fetch(`/checkNickname?nickname=${encodeURIComponent(nickname)}`);
        const exists = await res.json();
        nickMsg.textContent = exists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다.";
        nickMsg.style.color = exists ? "red" : "green";
    });

    // 🔹 이메일 형식 검사
    emailInput.addEventListener("input", () => {
        const email = emailInput.value.trim();
        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!pattern.test(email)) {
            emailMsg.textContent = "올바른 이메일 형식이 아닙니다.";
            emailMsg.style.color = "red";
        } else {
            emailMsg.textContent = "";
        }
    });

    // ✅ 비밀번호 실시간 유효성 검사 추가
    const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*]).{8,16}$/;

    passwordInput.addEventListener("input", () => {
        const pw = passwordInput.value.trim();

        if (pw.length === 0) {
            pwMsg.textContent = "";
            return;
        }

        if (!pwPattern.test(pw)) {
            pwMsg.textContent = "❌ 비밀번호는 영문+숫자+특수문자 포함 8~16자여야 합니다.";
            pwMsg.style.color = "red";
        } else {
            pwMsg.textContent = "✅ 사용 가능한 비밀번호입니다.";
            pwMsg.style.color = "green";
        }
    });

    // ✅ 비밀번호 확인 실시간 일치 검사
    confirmPwInput.addEventListener("input", () => {
        const pw = passwordInput.value.trim();
        const confirm = confirmPwInput.value.trim();

        if (confirm.length === 0) {
            pwMatchMsg.textContent = "";
            return;
        }

        if (pw !== confirm) {
            pwMatchMsg.textContent = "❌ 비밀번호가 일치하지 않습니다.";
            pwMatchMsg.style.color = "red";
        } else {
            pwMatchMsg.textContent = "✅ 비밀번호가 일치합니다.";
            pwMatchMsg.style.color = "green";
        }
    });

    // 🔹 폼 제출 시 마지막 검증
    form.addEventListener("submit", (e) => {
        const pw = passwordInput.value.trim();
        const confirm = confirmPwInput.value.trim();

        if (!pwPattern.test(pw)) {
            alert("비밀번호 형식이 올바르지 않습니다.");
            e.preventDefault();
            return;
        }

        if (pw !== confirm) {
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
        }
    });
});
