package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MissingUserExceptionAdvice
{
    @ResponseBody
    @ExceptionHandler(MissingUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(MissingUserException ex) {
        return ex.getMessage();
    }
}
