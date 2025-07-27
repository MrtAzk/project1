package com.libarymanagementsystem.project1.core.config;


import com.libarymanagementsystem.project1.core.exception.NotFoundException;
import com.libarymanagementsystem.project1.core.result.Result;
import com.libarymanagementsystem.project1.core.result.ResultData;
import com.libarymanagementsystem.project1.core.utiles.CreateResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundErrors(NotFoundException e){
        return new ResponseEntity<>(CreateResult.notFoundError(e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){

        List<String> validationErrorList=e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();



        return new ResponseEntity<>(CreateResult.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }
}
