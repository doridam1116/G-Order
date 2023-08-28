package com.gorder.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.Locale;

import static com.gorder.config.PropertyConfig.getMessageSource;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorResponse errorResponse;

    public GlobalExceptionHandler(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
////        String errorMessage = messageSource.getMessage("error.unique", null, Locale.getDefault());
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSqlException(SQLException e){
        switch (e.getErrorCode()){
            case 12899 :
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessageSource().getMessage("HTTP_BAD_REQUEST",null, Locale.getDefault()));
            case 1:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(getMessageSource().getMessage("HTTP_CONFLICT",null,Locale.getDefault()));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessageSource().getMessage("HTTP_BAD_REQUEST", null, Locale.getDefault()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse.errorMsg(ex, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessageSource().getMessage("HTTP_BAD_REQUEST",null,Locale.getDefault()));
    }

}
