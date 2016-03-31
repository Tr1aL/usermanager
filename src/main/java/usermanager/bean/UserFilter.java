package usermanager.bean;

import org.springframework.data.jpa.domain.Specification;
import usermanager.model.User;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

public class UserFilter {

    private String login;
    private String name;
    private String surname;
    private Integer age;
    private String skype;
    private String city;

    public UserFilter() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Specification<User> getSpecification() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if (login != null && !login.isEmpty()) {
                predicates.add(builder.equal(root.get("login"), login));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.equal(root.get("name"), name));
            }
            if (surname != null && !surname.isEmpty()) {
                predicates.add(builder.equal(root.get("surname"), surname));
            }
            if (age != null) {
                predicates.add(builder.equal(root.get("age"), age));
            }
            if (skype != null && !skype.isEmpty()) {
                predicates.add(builder.equal(root.get("skype"), skype));
            }
            if (city != null && !city.isEmpty()) {
                predicates.add(builder.equal(root.get("city"), city));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
