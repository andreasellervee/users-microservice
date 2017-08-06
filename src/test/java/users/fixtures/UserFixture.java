package users.fixtures;

import users.entities.User;
import users.models.UserCreation;

public class UserFixture {

    public static UserCreation getUser() {
        return getUser("Andreas",
                "Ellervee",
                "lokenslok",
                "andreas.ellervee@hotmail.com",
                "Estonia");
    }

    public static UserCreation getUser(String firstName, String lastName, String nickname, String email, String country) {
        UserCreation userCreation = new UserCreation();
        userCreation.setFirstName(firstName);
        userCreation.setLastName(lastName);
        userCreation.setNickname(nickname);
        userCreation.setEmail(email);
        userCreation.setCountry(country);
        userCreation.setPassword("TestStrongPassword1");
        userCreation.setPasswordConfirmation("TestStrongPassword1");
        return userCreation;
    }
}
