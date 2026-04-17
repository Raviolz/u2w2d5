package Raviolz.u2w2d5.exceptions;


import Raviolz.u2w2d5.payloads.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

    //  400
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(BadRequestEx ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    //  400
    @ExceptionHandler(AlreadyExistEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleAlreadyExists(AlreadyExistEx ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    // 🟠 404 - Not Found
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundEx ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    // 🔥 500 - Generic Error
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGeneric(Exception ex) {
        ex.printStackTrace(); // per debug
        return new ErrorDTO(
                "Errore mio! Opsy!",
                LocalDateTime.now()
        );
    }
}
