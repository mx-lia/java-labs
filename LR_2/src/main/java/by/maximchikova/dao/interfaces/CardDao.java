package by.maximchikova.dao.interfaces;

import by.maximchikova.model.Card;

import java.util.List;

public interface CardDao {
    public int addCard(Card card);
    public Card find(String number);
    public List<Card> findAll();
    public void pay(Card card);
}
