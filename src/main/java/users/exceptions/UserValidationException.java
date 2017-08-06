package users.exceptions;

import lombok.Getter;
import users.enums.ErrorMessages;

import java.util.List;

@Getter
public class UserValidationException extends RuntimeException {

    private List<ErrorMessages> errors;

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, List<ErrorMessages> errors) {
        super(message);
        this.errors = errors;
    }
}
