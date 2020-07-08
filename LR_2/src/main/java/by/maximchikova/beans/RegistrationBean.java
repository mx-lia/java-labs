package by.maximchikova.beans;

import by.maximchikova.dao.classes.UserModel;
import by.maximchikova.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="regBean")
@SessionScoped
public class RegistrationBean {
    private final UserModel userModel;
    private User user;

    public RegistrationBean() throws ClassNotFoundException {
        userModel = new UserModel();
        user = new User();
    }

    public void registration(){
        if(!user.getFirstName().isEmpty() && !user.getSecondName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
            if(userModel.addUser(user)){
                FacesContext.getCurrentInstance().addMessage("info", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration", "Registration completed successfully"));
            }
            else{
                FacesContext.getCurrentInstance().addMessage("info", new FacesMessage(FacesMessage.SEVERITY_WARN, "Registration", "User already exist!"));
            }
        } else{
            FacesContext.getCurrentInstance().addMessage("info", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration", "Registration error"));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
