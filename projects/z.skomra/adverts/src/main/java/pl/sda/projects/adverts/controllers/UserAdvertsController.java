package pl.sda.projects.adverts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.projects.adverts.model.domain.Advert;
import pl.sda.projects.adverts.model.domain.User;
import pl.sda.projects.adverts.model.repository.AdvertRepository;
import pl.sda.projects.adverts.model.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user-adverts")
public class UserAdvertsController {

    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserAdvertsController(AdvertRepository advertRepository, UserRepository userRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String prepareLoggedUserAdverts (Model model, Principal principal) {
        String username = principal.getName();
        log.info("Uzytkownik: {}", username);
        User user = userRepository.getByUsername(username);
        List<Advert> userAdverts = advertRepository.findAllByUserOrderByPostedDesc(user);
        model.addAttribute("adverts",userAdverts);
        return "/login/logged-home-page";
    }
}
