package by.belstu.maximchikova.bank.controller;

import by.belstu.maximchikova.bank.service.PaymentService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;

@Data
@Component("cardController")
@RequestScope
public class CardController {
    private PaymentService paymentService;

    private Long cardNumber;
    private String expirationDate;
    private String cvc;
    private BigDecimal paymentValue;
    private BigDecimal balance;
    private String errorMessage;

    public CardController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String pay() {
        try {
            paymentService.pay(cardNumber, expirationDate, cvc, paymentValue);
            return "success?faces-redirect=true";
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
            return "failure";
        }
    }
}
