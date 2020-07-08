package by.belstu.maximchikova.bank.config;

import by.belstu.maximchikova.bank.entity.Card;
import by.belstu.maximchikova.bank.entity.User;
import by.belstu.maximchikova.bank.repository.CardRepository;
import by.belstu.maximchikova.bank.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Log
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {
    private UserRepository userRepository;
    private CardRepository cardRepository;

    public DbInitializer(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... strings) {
        this.userRepository.deleteAll();
        User labSuperUser = userRepository.save(new User(null, "Olnexia", "098F6BCD4621D373CADE4E832627B4F6", Collections.emptyList()));
        Card card = new Card(null, "322", "07/2023", BigDecimal.valueOf(1241.42), labSuperUser);
        cardRepository.save(card);

        log.info("Database has been initialized");
    }
}