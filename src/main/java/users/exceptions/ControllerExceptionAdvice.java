package users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import users.enums.ErrorMessages;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleUserNotFoundException() {
        return new ResponseEntity(ErrorMessages.USER_NOT_FOUND.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleUserValidationException(UserValidationException e) {
        return new ResponseEntity(e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        if (message.contains("USER(EMAIL)")) {
            message = ErrorMessages.EMAIL_ALREADY_REGISTERED.getErrorMessage();
        }
        if (message.contains("USER(NICKNAME)")) {
            message = ErrorMessages.NICKNAME_ALREADY_REGISTERED.getErrorMessage();
        }
        return new ResponseEntity(message, HttpStatus.CONFLICT);
    }
}
