package org.example.todojpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.todojpa.entity.Todo;

@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private final Long id;
    private final String title;
    private final String contents;

    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }

}
