package users.models;

import lombok.Getter;
import lombok.Setter;
import users.entities.User;

import javax.persistence.Transient;

@Getter
@Setter
public class UserCreation extends User {

    private String password;
    private String passwordConfirmation;

}
