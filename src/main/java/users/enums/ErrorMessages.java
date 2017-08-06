package users.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    FIRST_NAME_BLANK("First Name can not be blank"),
    LAST_NAME_BLANK("Last Name can not be blank"),
    NICKNAME_BLANK("Nickname can not be blank"),
    EMAIL_BLANK("Email can not be blank"),
    COUNTRY_BLANK("Country can not be blank"),
    INVALID_PASSWORD("Password is not valid. Minimum length is 12, can not contain 'abc', " +
            "has to contain 1 digit, 1 uppercase letter, 1 lowercase letter"),
    PASSWORDS_DONT_MATCH("Password does not match with the password confirmation"),
    NEW_USER_VALIDATION_FAILED("New user validation failed"),

    UPDATE_USER_ID_NULL("User ID can not be null when updating an user"),
    USER_NOT_FOUND("User not found"),

    EMAIL_ALREADY_REGISTERED("User with such email has already been registered"),
    NICKNAME_ALREADY_REGISTERED("User with such nickname has already been registered")
    ;

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
