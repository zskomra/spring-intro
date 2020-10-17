package pl.sda.projects.adverts.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.projects.adverts.model.domain.Advert;
import pl.sda.projects.adverts.model.domain.User;
import pl.sda.projects.adverts.model.repository.AdvertRepository;
import pl.sda.projects.adverts.model.repository.UserRepository;

import java.time.LocalDateTime;

//Klasa tworzy uzytkowanika do logowania bez rejestracji
@Component @Slf4j @RequiredArgsConstructor
public class StartupDataLoader {

// Mamy wygenerowany konstruktor dla wszystkich
    // pól typu final przez lomboka (@RequiredArgsConstructor)
    // więc nie tworzymy własnego, a jak mamy
    // tylko jeden, to Spring automatycznie (magicznie)
    // używa go do wstrzykiwania zależności
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final AdvertRepository advertRepository;

    @EventListener
    public void onStartupPrepareData (ContextRefreshedEvent event) {
        log.info("Loading startup data...");
        userRepository.save(User.builder()
                .firstName("Nos")
                .lastName("Nosek")
                .username("endriu")
                .password(passwordEncoder.encode("endriu"))
                .active(true)
                .build());
        User endriu = userRepository.getByUsername("endriu");
        advertRepository.save(Advert.builder()
                .title("Kupię psa")
                .description("Kupię ładnego, spokojnego psa")
                .user(endriu)
                .posted(LocalDateTime.now())
                .build());
        advertRepository.save(Advert.builder()
                .title("Oddam mieszkanie")
                .description("Oddam mieszkanie w centrum")
                .user(endriu)
                .posted(LocalDateTime.now().minusDays(1))
                .build());
        advertRepository.save(Advert.builder()
                .title("Zjem drzewo")
                .description("Zjem dowolne drzewo do 2m średnicy!")
                .user(endriu)
                .posted(LocalDateTime.now().minusDays(1).minusHours(4))
                .build());
        log.info("Startup data loaded");
    }


}
