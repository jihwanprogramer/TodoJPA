package org.example.todojpa.Exception;

public class NotFoundException extends RuntimeException {

    /**
     * 객체를 찾을 수 없을 때 발생하는 예외입니다.
     *
     * @param id 찾을 수 없는 객체의 식별자
     */
    public NotFoundException(Long id) {
        super(id + "이 존재하지 않습니다.");
    }
}
