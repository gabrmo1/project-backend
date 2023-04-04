package br.com.project.exceptions.handler;

import br.com.project.exceptions.NoPermissionForThat;
import br.com.project.exceptions.ResourceNotFoundException;
import br.com.project.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(ResourceNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionForThat.class)
    public final ResponseEntity<ExceptionResponse> handleNoPermissionForThatExceptions(NoPermissionForThat ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

}
