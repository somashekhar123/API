package com.Demo.app.Exception;

import com.Demo.app.DTO.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails>exception(ResourceNotFoundException exception, WebRequest webRequest)
    {

        ErrorDetails s=new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
    return new ResponseEntity<>(s, HttpStatus.NOT_FOUND);

    }
}
