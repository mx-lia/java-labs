package by.belstu.maximchikova.bank.util;

import by.belstu.maximchikova.bank.entity.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public final class SessionUtils {
    private static final String USER_ATTR = "user";

    private SessionUtils() {
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static void setUser(User user) {
        HttpSession session = getSession();
        session.setAttribute(USER_ATTR, user);
    }

    public static Optional<User> getUser() {
        HttpSession session = getSession();
        User user = (User) session.getAttribute(USER_ATTR);
        return Optional.ofNullable(user);
    }

    public static void removeUser() {
        HttpSession session = getSession();
        session.removeAttribute(USER_ATTR);
    }
}