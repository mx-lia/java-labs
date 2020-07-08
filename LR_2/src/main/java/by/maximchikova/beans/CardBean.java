package by.maximchikova.beans;

import by.maximchikova.dao.classes.CardModel;
import by.maximchikova.model.Card;
import by.maximchikova.model.CurrentUser;
import by.maximchikova.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@ManagedBean(name = "cardBean")
@RequestScoped
public class CardBean {
    private CardModel cardModel;

    private double payment;
    private User user;
    private Card card;
    private Card newCard;
    private Card selectedCard;
    private String dateString;

    private String newnumber;
    private Date newvalidDate;
    private BigDecimal newbalance;
    private String newcvv;

    private List<Card> cards;

    public CardBean() throws ClassNotFoundException {
        cardModel = new CardModel();

        user = CurrentUser.getUser();
        card = CurrentUser.getCard();
        card.setUser(user);
        newCard = new Card();
        cards = cardModel.findAll();
    }

    public void pay(){
        if(payment <= selectedCard.getBalance().doubleValue() && selectedCard.getBalance().doubleValue() > 0){
            selectedCard.setBalance(BigDecimal.valueOf(selectedCard.getBalance().doubleValue() - payment));

            cardModel.pay(selectedCard);

            FacesContext.getCurrentInstance().addMessage("infoCard", new FacesMessage(FacesMessage.SEVERITY_INFO, selectedCard.getNumber(), "Pay has been successfully"));
        } else{
            FacesContext.getCurrentInstance().addMessage("infoCard", new FacesMessage(FacesMessage.SEVERITY_FATAL, selectedCard.getNumber(), "Pay was terminated"));
        }
    }

    public void submit() {
        newCard.setUser(CurrentUser.user);
        cardModel.addCard(newCard);
        if (!cards.contains(newCard)) {
            cards.add(newCard);
        } else {
            return;
        }
    }

    public double getPayment() {
        return payment;
    }

    public String getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }

    public Date getNewvalidDate() {
        return newvalidDate;
    }

    public void setNewvalidDate(Date newvalidDate) {
        this.newvalidDate = newvalidDate;
    }

    public BigDecimal getNewbalance() {
        return newbalance;
    }

    public void setNewbalance(BigDecimal newbalance) {
        this.newbalance = newbalance;
    }

    public String getNewcvv() {
        return newcvv;
    }

    public void setNewcvv(String newcvv) {
        this.newcvv = newcvv;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getNewCard() {
        return newCard;
    }

    public void setNewCard(Card newCard) {
        this.newCard = newCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        newCard.setValidDate(Date.valueOf(dateString));
    }
}
