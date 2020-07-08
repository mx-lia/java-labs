package by.belstu.maximchikova.vehicleconsole.service;

import by.belstu.maximchikova.vehicleconsole.entity.User;
import by.belstu.maximchikova.vehicleconsole.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<User> userOptional = repository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return buildUserDetailsForAuthentication(user);
        } else {
            throw new UsernameNotFoundException("Cannot find username " + userName);
        }
    }

    private UserDetails buildUserDetailsForAuthentication(User user) {
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(user.getUserName());

        builder.password(user.getPasswordHash());
        builder.roles(user.getRole().name());

        return builder.build();
    }
}
