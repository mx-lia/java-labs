package by.maximchikova.beans;

import by.maximchikova.dao.classes.CardModel;
import by.maximchikova.dao.classes.UserModel;
import by.maximchikova.model.CurrentUser;
import by.maximchikova.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;

@ManagedBean(name = "loginBean")
@RequestScoped
@Getter
@Setter
public class LoginBean {
    private CardModel cardModel;
    private UserModel userModel;
    private User user;

    public LoginBean() throws ClassNotFoundException {
        cardModel = new CardModel();
        userModel = new UserModel();
        user = new User();
    }

    public String validate() throws SQLException, ClassNotFoundException {
        if((CurrentUser.user = userModel.find(user.getEmail(), user.getPassword())) != null){
            return "home";
        } else {
            return "login";
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

