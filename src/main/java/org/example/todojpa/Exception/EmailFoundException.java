package org.example.todojpa.Exception;

public class EmailFoundException extends RuntimeException{
    public EmailFoundException(String email) {
        super(email+"이 없습니다.");
    }
}
