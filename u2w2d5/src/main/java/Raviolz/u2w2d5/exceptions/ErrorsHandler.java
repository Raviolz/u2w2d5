package Raviolz.u2w2d5.exceptions;


import Raviolz.u2w2d5.payloads.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    //  404
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundEx ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    // per prendere quelle delle annotazioni
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(); // tutto questo per tirare fuori i messaggi dalle annotazione nei dto, non ho trovato altro modo..
        return new ErrorDTO(message, LocalDateTime.now()); // se visto in classe me lo sono perso
    }

    // 500
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
