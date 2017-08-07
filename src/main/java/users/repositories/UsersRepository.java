package users.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import users.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

}
