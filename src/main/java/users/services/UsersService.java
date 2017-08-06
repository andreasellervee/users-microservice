package users.services;

import users.entities.User;
import users.models.UserCreation;
import users.models.UserQueryCriteria;

import java.util.List;

public interface UsersService {

    User createUser(UserCreation userCreation);

    User updateUser(User user);

    void deleteUser(Long userId);

    List<User> queryUsers(UserQueryCriteria userQueryCriteria);

    User getUserById(Long id);
}
