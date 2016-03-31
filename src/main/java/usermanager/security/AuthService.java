package usermanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usermanager.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            return null;
        }
        usermanager.model.User user = userRepository.getByLogin(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> granted = new ArrayList<>();
        granted.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getLogin(), user.getPassword(), granted);
    }
}
