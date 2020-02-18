package com.trilogyed.tasker.controller;



//import com.company.exception.NoTasksFoundInDb;
//import com.company.exception.NotFoundCategory;
//import com.company.exception.NotFoundException;
//import com.company.exception.NotFoundId;
import com.trilogyed.tasker.exception.NotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<VndErrors> jsonParserErrorException(HttpMessageNotReadableException e, WebRequest request)
    {
        VndErrors error = new VndErrors(request.toString(), "Please enter valid entries!");
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<VndErrors> integrityException(SQLIntegrityConstraintViolationException e, WebRequest request)
    {
        VndErrors error = new VndErrors(request.toString(), "Please enter valid entries!");
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(value = {NotFoundException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<VndErrors> notFoundException(NotFoundException e, WebRequest request)
    {
        VndErrors error = new VndErrors(request.toString(), "Given ID/Category/or Task(s) NotFound in the DataBase");
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        return responseEntity;
    }

//    @ExceptionHandler(value = {NotFoundException.class, RuntimeException.class})
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public ResponseEntity<VndErrors> notFoundException(NotFoundId e, WebRequest request)
//    {
//        VndErrors error = new VndErrors(request.toString(), "The given task for stated id Does not exist");
//        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        return responseEntity;
//    }
//
//    @ExceptionHandler(value = {NotFoundException.class, RuntimeException.class})
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public ResponseEntity<VndErrors> notFoundException(NoTasksFoundInDb e, WebRequest request)
//    {
//        VndErrors error = new VndErrors(request.toString(), "there are no tasks to display: ");
//        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        return responseEntity;
//    }
//
//
//    @ExceptionHandler(value = {NotFoundException.class, RuntimeException.class})
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public ResponseEntity<VndErrors> notFoundException(NotFoundCategory e, WebRequest request)
//    {
//        VndErrors error = new VndErrors(request.toString(), "\"there is no task with the given category: ");
//        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        return responseEntity;
//    }



}
