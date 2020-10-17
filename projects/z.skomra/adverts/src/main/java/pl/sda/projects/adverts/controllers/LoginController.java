//package pl.sda.projects.adverts.controllers;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import pl.sda.projects.adverts.model.domain.User;
//
//@Controller
//@Slf4j
//@RequestMapping
//public class LoginController {
//
//
//    @GetMapping("/login")
//    public String prepareLoginPage () {
//        return "/login/login";
//    }
//
////    @PostMapping
////    public String loginError (Model model) {
////        model.addAttribute("loginError",true);
////        return "registration/registration-summary";
////    }
//
//    @PostMapping
//    public String processLoginPage (@ModelAttribute(name="user") User user, Model model) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        if (username.equals("user")& password.equals("user"))
//        return "registration/registration-summary";
//        model.addAttribute("invalidCredentials", true);
//        return "registration/registration-summary";
//    }
//}
