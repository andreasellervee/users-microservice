package users.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import users.entities.User;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static users.utils.SpecificationUtil.toLike;

@Getter
@Setter
public class UserQueryCriteria  {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String country;

    public Specification<User> getUserSpec() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (getFirstName() != null) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), toLike(getFirstName().toLowerCase())));
            }
            if (getLastName() != null) {
                predicates.add(cb.like(cb.lower(root.get("lastName")), toLike(getLastName().toLowerCase())));
            }
            if (getNickname() != null) {
                predicates.add(cb.like(cb.lower(root.get("nickname")), toLike(getNickname().toLowerCase())));
            }
            if (getEmail() != null) {
                predicates.add(cb.like(cb.lower(root.get("email")), toLike(getEmail().toLowerCase())));
            }
            if (getCountry() != null) {
                predicates.add(cb.like(cb.lower(root.get("country")), toLike(getCountry().toLowerCase())));
            }
            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
