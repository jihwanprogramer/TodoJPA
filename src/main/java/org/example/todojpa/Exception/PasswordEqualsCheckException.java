package org.example.todojpa.Exception;

public class PasswordEqualsCheckException extends RuntimeException {

    /**
     * 비밀번호가 일치하지 않을 때 발생하는 예외입니다.
     */
    public PasswordEqualsCheckException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
