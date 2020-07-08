package by.belstu.maximchikova.bank.service;

import by.belstu.maximchikova.bank.entity.User;
import by.belstu.maximchikova.bank.exception.LoginException;
import by.belstu.maximchikova.bank.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class LoginService {
    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String userName, String password) {

        try {
            Optional<User> userOptional = userRepository.findByUserName(userName);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (isSameHash(password, user.getPasswordHash())) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        } catch (NoSuchAlgorithmException e) {
            throw new LoginException(e.getMessage(), e);
        }
    }

    private boolean isSameHash(String value, String hash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] digest = md.digest();
        String passwordHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return passwordHash.equals(hash);
    }
}
