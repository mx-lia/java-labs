package by.belstu.maximchikova.bank.controller;

import by.belstu.maximchikova.bank.entity.User;
import by.belstu.maximchikova.bank.service.LoginService;
import by.belstu.maximchikova.bank.util.SessionUtils;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Data
@Component("loginController")
@RequestScope
public class LoginController {
    private LoginService loginService;
    private String userName;
    private String password;
    private boolean loginError;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public String login() {
        Optional<User> userOptional = loginService.login(userName, password);
        loginError = !userOptional.isPresent();

        if (loginError) {
            return "login";
        } else {
            SessionUtils.setUser(userOptional.get());
            return "payment?faces-redirect=true";
        }
    }

    public String logout() {
        SessionUtils.removeUser();
        return "login?faces-redirect=true";
    }
}
