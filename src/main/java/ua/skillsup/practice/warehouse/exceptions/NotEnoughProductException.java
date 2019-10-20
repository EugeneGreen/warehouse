package ua.skillsup.practice.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughProductException extends RuntimeException {
    public NotEnoughProductException(String message) {
        super(message);
    }
}
