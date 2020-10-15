package pl.sda.projects.adverts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @Slf4j
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String prepareLoginPage () {
        return "/login/log";
    }

    @PostMapping
    public String processLoginPage () {
        return "";
    }
}
