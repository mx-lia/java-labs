package by.maximchikova.dao.classes;

import by.maximchikova.DB.HibernateUtil;
import by.maximchikova.dao.interfaces.UserDao;
import by.maximchikova.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserModel implements UserDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserModel() throws ClassNotFoundException {
    }

    public User find(int id){
        User user = null;
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = (User) session.createQuery("from User where id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
        } finally {
            session.close();
        }
        return user;
    }


    @Override
    public boolean addUser(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("CALL AddUser(:firstName, :secondName, :email, :password)")
                    .addEntity(User.class)
                    .setParameter("firstName", user.getFirstName())
                    .setParameter("secondName", user.getSecondName())
                    .setParameter("email", user.getEmail())
                    .setParameter("password", user.getPassword().hashCode());

            query.executeUpdate();
            return true;
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean isExists(User user) {
        return false;
    }

    @Override
    public User find(String email, String password) {
        User user = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CALL GetUser(:email, :password)")
                    .addEntity(User.class)
                    .setParameter("email", email)
                    .setParameter("password", password.hashCode());

            user = (User) query.list().get(0);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null)
                transaction.commit();
        }
        finally {
            session.close();
        }
        return user;
    }
}
