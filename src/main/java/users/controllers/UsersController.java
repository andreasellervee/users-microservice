package users.controllers;

import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.entities.User;
import users.models.UserCreation;
import users.models.UserQueryCriteria;
import users.services.UsersService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Get User by Id", httpMethod = "GET", response = User.class)
    public User getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    @ApiOperation(value = "Query Users by criteria")
    public List<User> queryUsers(UserQueryCriteria userQueryCriteria) {
        return usersService.queryUsers(userQueryCriteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new User", httpMethod = "POST", response = User.class)
    public User createUser(@RequestBody UserCreation user) {
        return usersService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update the user", httpMethod = "PUT", response = User.class)
    public User updateUser(@RequestBody User user) {
        return usersService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ApiOperation(value = "Delete an user", httpMethod = "DELETE")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
