package org.example.todojpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequestDto {

    /**
     * 제목.
     */
    @Size(min = 2, message = "두글자 이상 입력하세요")
    private final String title;

    /**
     * 내용.
     */
    @NotBlank(message = "내용을 입력하세요")
    private final String contents;
}
