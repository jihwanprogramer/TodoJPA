package org.example.todojpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    /**
     * 사용자 이름.
     */
    @NotBlank(message = "이름을 입력하세요")
    private final String userName;

    /**
     * 이메일.
     */
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식으로 입력하세요")
    private final String email;

    /**
     * 비밀번호.
     */
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이여야 합니다")
    private final String password;

}
