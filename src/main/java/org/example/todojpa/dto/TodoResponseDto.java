package org.example.todojpa.dto;

import lombok.Data;
import org.example.todojpa.entity.Todo;

@Data
public class TodoResponseDto {

    /**
     * 식별자.
     */
    private final Long id;

    /**
     * 이메일.
     */
    private final String email;

    /**
     * 제목.
     */
    private final String title;

    /**
     * 내용.
     */
    private final String contents;

    /**
     * Todo 객체를 DTO로 변환합니다.
     *
     * @param todo 변환할 Todo 객체
     * @return TodoResponseDto 객체
     */
    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getUser().getEmail(), todo.getTitle(), todo.getContents());
    }
}
