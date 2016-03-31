package usermanager.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import usermanager.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User getByLogin(String login);

    @Query("FROM User ORDER BY login ASC")
    public List<User> getUserList();

    public List<User> findAll(Specification<User> spec);

}
