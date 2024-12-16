package org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.data;

import org.payment_services.wallet.domain.exceptions.ForbiddenTransactionException;
import org.payment_services.wallet.domain.exceptions.UserNotFoundException;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.IncorrectPasswordException;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletException;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletNotFoundException;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.data.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedExceptionsAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<Object> handleGeneralException(WalletException walletException,final WebRequest webRequest){
        final ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(webRequest.getDescription(false))
                .message(walletException.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException exception, final WebRequest webRequest){
        final ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(webRequest.getDescription(false))
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, final WebRequest webRequest){
        final ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(webRequest.getDescription(false))
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ForbiddenTransactionException.class)
    public ResponseEntity<Object> handleForbiddenTransactionException(ForbiddenTransactionException exception, final WebRequest webRequest){
        final ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .path(webRequest.getDescription(false))
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
