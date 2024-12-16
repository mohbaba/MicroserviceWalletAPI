package com.mohservices.identity.infrastructure.adapters.output.customizedExceptions.data;

import com.mohservices.identity.domain.exceptions.IdentityServiceException;
import com.mohservices.identity.infrastructure.adapters.output.customizedExceptions.data.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedExceptionsAdapter extends ResponseEntityExceptionHandler {


    @ExceptionHandler(IdentityServiceException.class)
    public final ResponseEntity<Object> handleGlobalException(IdentityServiceException identityServiceException, final WebRequest request){
        final ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(request.getDescription(false))
                .message(identityServiceException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
