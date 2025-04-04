package org.example.todojpa.handler;

import jakarta.validation.ConstraintViolationException;
import org.example.todojpa.Exception.EmailFoundException;
import org.example.todojpa.Exception.PasswordEqualsCheckException;
import org.example.todojpa.Exception.NotFoundException;
import org.example.todojpa.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String messages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(new ErrorResponseDto("C001", messages, "BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponseDto("C002", ex.getMessage(), "BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleDefaultHandlerExceptionResolver(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(new ErrorResponseDto("C003", "전송 형식에 오류가있습니다", "METHOD_NOT_ALLOWED"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(PasswordEqualsCheckException.class)
    public ResponseEntity<ErrorResponseDto> handlePasswordEqualsCheckException(PasswordEqualsCheckException ex) {
        return new ResponseEntity<>(new ErrorResponseDto("C004", ex.getMessage(), "BAD_PASSWORD"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponseDto("C005", "이미 해당 이메일이 존재합니다", "BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public  ResponseEntity<ErrorResponseDto> handleTodoNotFindException(NotFoundException ex){
        return  new ResponseEntity<>(new ErrorResponseDto("C006",ex.getMessage(),"NOT_FOUND"),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailFoundException(EmailFoundException ex){
        return new ResponseEntity<>(new ErrorResponseDto("C007",ex.getMessage(),"FOUND"),HttpStatus.IM_USED);
    }

}
