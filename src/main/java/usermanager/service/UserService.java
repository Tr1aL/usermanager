package usermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import usermanager.bean.UserFilter;
import usermanager.dao.UserRepository;
import usermanager.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(User user) {
        if (userRepository.getByLogin(user.getLogin()) != null) {
            return false;
        }
        user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), ""));
        userRepository.save(user);
        return true;
    }

    public List<User> getUserList() {
        return userRepository.getUserList();
    }

    public List<User> getUserList(UserFilter filter) {
        return userRepository.findAll(filter.getSpecification());
    }
}
