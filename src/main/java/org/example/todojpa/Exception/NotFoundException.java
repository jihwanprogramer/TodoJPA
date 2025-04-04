package org.example.todojpa.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(id + "이 존재하지 않습니다.");
    }
}
