package users.validation;

import org.apache.commons.lang3.StringUtils;
import users.enums.ErrorMessages;
import users.models.UserCreation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserValidation {

    public static final int MIN_PASSWORD_LENGTH = 12;
    public static final String DIGIT_REGEX = ".*\\d+.*";
    public static final List<String> NOT_ALLOWED_TO_CONTAIN = Arrays.asList("123");

    public static List<ErrorMessages> validateNewUser(UserCreation user) {
        List<ErrorMessages> errors = new ArrayList<>();
        if (StringUtils.isEmpty(user.getFirstName())) {
            errors.add(ErrorMessages.FIRST_NAME_BLANK);
        }
        if (StringUtils.isEmpty(user.getLastName())) {
            errors.add(ErrorMessages.LAST_NAME_BLANK);
        }
        if (StringUtils.isEmpty(user.getNickname())) {
            errors.add(ErrorMessages.NICKNAME_BLANK);
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.add(ErrorMessages.EMAIL_BLANK);
        }
        if (StringUtils.isEmpty(user.getCountry())) {
            errors.add(ErrorMessages.COUNTRY_BLANK);
        }
        if (isValidPassword(user.getPassword())) {
            if (!user.getPassword().equals(user.getPasswordConfirmation())) {
                errors.add(ErrorMessages.PASSWORDS_DONT_MATCH);
            }
        } else {
            errors.add(ErrorMessages.INVALID_PASSWORD);
        }

        return errors;
    }

    private static boolean isValidPassword(String password) {
        // From https://stackoverflow.com/a/16127946/6126380
        if (password == null || password.length() < MIN_PASSWORD_LENGTH
                || NOT_ALLOWED_TO_CONTAIN.stream().anyMatch(password::contains)
                || !password.matches(DIGIT_REGEX)
                || password.equals(password.toLowerCase())
                || password.equals(password.toUpperCase())) {
            return false;
        }
        return true;
    }

}
