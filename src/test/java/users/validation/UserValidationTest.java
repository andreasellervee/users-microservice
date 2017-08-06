package users.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import users.enums.ErrorMessages;
import users.fixtures.UserFixture;
import users.models.UserCreation;

import java.util.List;

import static org.junit.Assert.*;

public class UserValidationTest {

    @Test
    public void validateNewUser_emptyUser() {
        UserCreation emptyUser = new UserCreation();
        List<ErrorMessages> errors = UserValidation.validateNewUser(emptyUser);
        assertTrue(errors.contains(ErrorMessages.FIRST_NAME_BLANK));
        assertTrue(errors.contains(ErrorMessages.LAST_NAME_BLANK));
        assertTrue(errors.contains(ErrorMessages.NICKNAME_BLANK));
        assertTrue(errors.contains(ErrorMessages.EMAIL_BLANK));
        assertTrue(errors.contains(ErrorMessages.COUNTRY_BLANK));
        assertTrue(errors.contains(ErrorMessages.INVALID_PASSWORD));
    }

    @Test
    public void validateNewUser_passwordValidation() {
        UserCreation user = UserFixture.getUser();

        setUserPassword(user, "TooShort1");
        List<ErrorMessages> errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.INVALID_PASSWORD));

        setUserPassword(user, "StrongPasswordWith123");
        errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.INVALID_PASSWORD));

        setUserPassword(user, "StrongPasswordWithNoDigit");
        errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.INVALID_PASSWORD));

        setUserPassword(user, "STRONGPASSWORDWITHNOLOWERCASELETTER1");
        errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.INVALID_PASSWORD));

        setUserPassword(user, "strongpasswordwithnouppercaseletter1");
        errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.INVALID_PASSWORD));

        setUserPassword(user, "ValidStrongPassword1");
        errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.isEmpty());
    }

    private void setUserPassword(UserCreation user, String newPassword) {
        user.setPassword(newPassword);
        user.setPasswordConfirmation(newPassword);
    }

    @Test
    public void validateNewUser_passwordsDontMatch() {
        UserCreation user = UserFixture.getUser();
        user.setPasswordConfirmation("NotTheSameAsFirstOne");
        List<ErrorMessages> errorMessages = UserValidation.validateNewUser(user);
        assertTrue(errorMessages.contains(ErrorMessages.PASSWORDS_DONT_MATCH));
    }


}