package org.example.jpa.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "login"; // -> /WEB-INF/views/login.jsp
    }

    @GetMapping("/join")
    public String join() {
        return "join"; // -> /WEB-INF/views/join.jsp
    }

    @GetMapping("/findId")
    public String findId() {
        return "findId"; // -> /WEB-INF/views/findId.jsp
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "findPw"; // -> /WEB-INF/views/findPw.jsp
    }
}
