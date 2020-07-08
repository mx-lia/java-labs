package by.maximchikova.model;

import java.math.BigDecimal;
import java.sql.Date;

public class CurrentUser {
    public static User user;
    private static Card card = new Card("4255200306247107", Date.valueOf("2029-12-22"), new BigDecimal(5422.25), "441");

    public static User getUser() {
        return user;
    }

    public static void setUser(User _user) { user = _user; }

    public static Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
