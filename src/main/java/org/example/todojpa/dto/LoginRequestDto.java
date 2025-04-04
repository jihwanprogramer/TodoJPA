package org.example.todojpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "이메일 입력은 필수 입니다.")
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;

    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이여야 합니다")
    private final String password;
}
