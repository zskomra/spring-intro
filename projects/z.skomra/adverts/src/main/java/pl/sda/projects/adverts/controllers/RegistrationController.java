package pl.sda.projects.adverts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.projects.adverts.model.domain.User;
import pl.sda.projects.adverts.model.repository.UserRepository;

@Controller @Slf4j
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired // userRepostiory będzie wstrzyknięte teraz przez Springa
    public RegistrationController (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @GetMapping
    public String prepareRegistrationPage(Model model){
        model.addAttribute("greetings", "Hello spring");
        return "/registration/registraion";
    };

    @PostMapping
    public String processRegistrationPage(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName,
                                            ModelMap model){
        String encdoedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .username(username)
                .password(encdoedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .active(true)
                .build();
        log.debug("User to save: {}", user);
        userRepository.save(user);
        log.info("New user saved: {}", user);
        log.info("password {}", encdoedPassword);
        model.addAttribute("savedUser", user);
        return "registration/registration-summary";
    };
}
