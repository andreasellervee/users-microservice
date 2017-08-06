package users.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import users.UsersMicroserviceApp;
import users.configuration.CachingConfig;
import users.entities.User;
import users.enums.ErrorMessages;
import users.enums.UserEventType;
import users.exceptions.UserNotFoundException;
import users.exceptions.UserValidationException;
import users.models.UserCreation;
import users.models.UserEvent;
import users.models.UserQueryCriteria;
import users.repositories.UsersRepository;
import users.services.EventService;
import users.services.UsersService;
import users.validation.UserValidation;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersMicroserviceApp.class);

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EventService eventService;

    @Override
    @Cacheable(value = CachingConfig.USERS, key = "#id")
    public User getUserById(@NotNull Long id) {
        User user = usersRepository.findOne(id);
        if (user == null) {
            log.debug("User with id {} not found", id);
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User createUser(UserCreation userCreation) {
        List<ErrorMessages> errors = UserValidation.validateNewUser(userCreation);
        if (!errors.isEmpty()) {
            throw new UserValidationException(ErrorMessages.NEW_USER_VALIDATION_FAILED.getErrorMessage(), errors);
        }
        User newUser = usersRepository.save(new User(userCreation));
        eventService.pushEvent(new UserEvent(newUser.getId(), UserEventType.USER_ADDED));
        return newUser;
    }

    @Override
    @CacheEvict(value = CachingConfig.USERS, key = "#user.id")
    public User updateUser(User user) {
        Assert.isTrue(user.getId() != null, ErrorMessages.UPDATE_USER_ID_NULL.getErrorMessage());
        User updatedUser = usersRepository.save(user);
        eventService.pushEvent(new UserEvent(updatedUser.getId(), UserEventType.USER_MODIFIED));
        return updatedUser;
    }

    @Override
    @CacheEvict(value = CachingConfig.USERS, key = "#id")
    public void deleteUser(Long id) {
        log.debug("Deleting user with id {}", id);
        usersRepository.delete(id);
        eventService.pushEvent(new UserEvent(id, UserEventType.USER_DELETED));
    }

    @Override
    public List<User> queryUsers(UserQueryCriteria userQueryCriteria) {
        return usersRepository.findAll(userQueryCriteria.getUserSpec());
    }

}
