package users.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import users.entities.User;
import users.exceptions.UserNotFoundException;
import users.exceptions.UserValidationException;
import users.fixtures.UserFixture;
import users.models.UserCreation;
import users.models.UserQueryCriteria;
import users.services.UsersService;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UsersServiceImplTest {

    private UserCreation userCreation;

    @Autowired
    private UsersService usersService;

    @Before
    public void setup() {
        userCreation = UserFixture.getUser();
    }

    @Test
    public void createUser() {
        User createdUser = usersService.createUser(userCreation);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(userCreation.getFirstName(), createdUser.getFirstName());
        assertEquals(userCreation.getLastName(), createdUser.getLastName());
        assertEquals(userCreation.getNickname(), createdUser.getNickname());
        assertEquals(userCreation.getEmail(), createdUser.getEmail());
        assertEquals(userCreation.getCountry(), createdUser.getCountry());
        assertEquals(userCreation.getPassword().hashCode(), createdUser.getPasswordHash());
    }

    @Test
    public void createUser_validation() {
        UserCreation emptyUser = new UserCreation();
        try {
            usersService.createUser(emptyUser);
            assertFalse(true);
        } catch (UserValidationException e) {
            assertFalse(e.getErrors().isEmpty());
        }
    }

    @Test
    public void getUserById() {
        User createdUser = usersService.createUser(userCreation);
        User queriedUser = usersService.getUserById(createdUser.getId());
        assertEquals(createdUser.getId(), queriedUser.getId());
    }

    @Test
    public void getUserById_nonExistantId() {
        try {
            usersService.getUserById(1337L);
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(e instanceof UserNotFoundException);
        }
    }

    @Test
    public void updateUser() {
        User createdUser = usersService.createUser(userCreation);
        String originalFirstName = createdUser.getFirstName();
        String newFirstName = "NewFirstName";
        createdUser.setFirstName(newFirstName);
        usersService.updateUser(createdUser);
        User updatedUser = usersService.getUserById(createdUser.getId());
        assertFalse(updatedUser.getFirstName().equals(originalFirstName));
        assertEquals(newFirstName, updatedUser.getFirstName());
    }

    @Test
    public void deleteUser() {
        User createdUser = usersService.createUser(userCreation);
        usersService.deleteUser(createdUser.getId());
        try {
            usersService.getUserById(createdUser.getId());
            assertFalse(true);
        } catch (Exception e) {
            assertTrue(e instanceof UserNotFoundException);
        }
    }

    @Test
    public void queryUsers() {
        usersService.createUser(UserFixture.getUser());
        usersService.createUser(UserFixture.getUser("Priit", "D", "priitd",
                "priit.d@hotmail.com", "Estonia"));
        usersService.createUser(UserFixture.getUser("Etibar", "H", "etibarh",
                "etibar.h@gmail.com", "Azerbaijan"));
        UserQueryCriteria criteria = new UserQueryCriteria();

        criteria.setCountry("Estonia");
        List<User> users = usersService.queryUsers(criteria);
        assertEquals(2, users.size());
        assertTrue(users.stream().allMatch(u -> u.getCountry().equals("Estonia")));

        criteria.setCountry("est");
        criteria.setFirstName("Andreas");
        users = usersService.queryUsers(criteria);
        assertEquals(1, users.size());
        assertEquals("Andreas", users.get(0).getFirstName());

        criteria.setCountry(null);
        criteria.setFirstName("etibar");
        users = usersService.queryUsers(criteria);
        assertEquals(1, users.size());
        assertTrue(users.stream().allMatch(u -> u.getFirstName().equals("Etibar")));
    }

}