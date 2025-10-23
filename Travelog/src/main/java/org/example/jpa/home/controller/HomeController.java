package org.example.jpa.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /** 홈 페이지 */
    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // → /WEB-INF/jsp/home.jsp
    }

}
