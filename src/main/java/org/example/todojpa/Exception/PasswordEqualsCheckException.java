package org.example.todojpa.Exception;

public class PasswordEqualsCheckException extends RuntimeException {

    public PasswordEqualsCheckException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
