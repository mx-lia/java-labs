package by.maximchikova.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "cards", schema = "bank")
public class Card {
    @Id
    private String number;
    private Date validDate;
    private BigDecimal balance;
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Card(String number, Date validDate, BigDecimal balance, String cvv) {
        this.number = number;
        this.validDate = validDate;
        this.balance = balance;
        this.cvv = cvv;
    }

    public Card() {
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "validDate")
    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "CVV")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card that = (Card) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (validDate != null ? !validDate.equals(that.validDate) : that.validDate != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (cvv != null ? !cvv.equals(that.cvv) : that.cvv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (validDate != null ? validDate.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (cvv != null ? cvv.hashCode() : 0);
        return result;
    }
}
