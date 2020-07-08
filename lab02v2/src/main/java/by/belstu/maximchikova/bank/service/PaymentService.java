package by.belstu.maximchikova.bank.service;

import by.belstu.maximchikova.bank.entity.Card;
import by.belstu.maximchikova.bank.entity.User;
import by.belstu.maximchikova.bank.exception.PaymentException;
import by.belstu.maximchikova.bank.repository.CardRepository;
import by.belstu.maximchikova.bank.util.SessionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PaymentService {
    private CardRepository cardRepository;

    public PaymentService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void pay(long cardNumber, String expirationDate, String cvc, BigDecimal paymentValue) {
        Optional<User> optionalUser = SessionUtils.getUser();
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            Optional<Card> cardOptional = currentUser
                    .getCards()
                    .stream()
                    .filter(c -> c.getId().equals(cardNumber)
                            && c.getCvc().equals(cvc)
                            && c.getExpirationDate().equals(expirationDate))
                    .findFirst();
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                BigDecimal newBalance = card.getBalance().subtract(paymentValue);

                if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                    throw new PaymentException("Too big payment value");
                }

                card.setBalance(newBalance);
                cardRepository.save(card);
            } else {
                throw new PaymentException("Card not found");
            }
        } else {
            throw new PaymentException("User not found");
        }
    }
}
