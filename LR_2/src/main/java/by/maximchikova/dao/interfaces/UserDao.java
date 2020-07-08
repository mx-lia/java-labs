package by.maximchikova.dao.interfaces;

import by.maximchikova.model.User;

public interface UserDao {
    public boolean addUser(User user);
    public boolean isExists(User user);
    public User find (String email, String password);
}