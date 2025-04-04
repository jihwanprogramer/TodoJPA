package org.example.todojpa.Exception;

public class EmailFoundException extends RuntimeException {

    /**
     * 이메일을 찾을 수 없을 때 발생하는 예외입니다.
     *
     * @param email 찾을 수 없는 이메일
     */
    public EmailFoundException(String email) {
        super(email + "이 없습니다.");
    }
}
