package ua.skillsup.practice.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidFormatClientException extends RuntimeException {
    public InvalidFormatClientException(String message) {
        super(message);
    }
}
