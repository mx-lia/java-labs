package by.maximchikova.dao.classes;

import by.maximchikova.DB.HibernateUtil;
import by.maximchikova.dao.interfaces.CardDao;
import by.maximchikova.model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CardModel implements CardDao {
    private SessionFactory sessionFactory;

    public CardModel() throws ClassNotFoundException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Card> findAll(){
        List cards = null;
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("CALL GetCards()")
                    .addEntity(Card.class);
            cards = query.list();

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
        } finally {
            session.close();
        }
        return cards;
    }

    @Override
    public void pay(Card card) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("CALL pay(:number, :balance)")
                    .setParameter("number", card.getNumber())
                    .setParameter("balance", card.getBalance());

            query.executeUpdate();
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public int addCard(Card card) {
        Session session = null;
        Transaction transaction = null;

        int count = 0;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CALL addCard(:number, :validDate, :balance, :CVV, :user_id)")
                    .setParameter("number", card.getNumber())
                    .setParameter("validDate", card.getValidDate())
                    .setParameter("balance", card.getBalance())
                    .setParameter("CVV", card.getCvv())
                    .setParameter("user_id", card.getUser().getId());

            count = query.executeUpdate();
            return count;
        } catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            return -10;
        } finally {
            System.out.println("finally");
            session.close();
        }
    }

    @Override
    public Card find(String number){
        Card card = null;
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CALL GetCardByNumber(:num)")
                    .addEntity(Card.class)
                    .setParameter("num", number);

            card = (Card) query.list().get(0);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
        } finally {
            session.close();
        }
        return card;
    }
}
