package org.example.todojpa.dto;

import lombok.Data;
import org.example.todojpa.entity.User;

@Data
public class UserResponseDto {

    /**
     * 사용자 식별자.
     */
    private final Long id;

    /**
     * 사용자 이름.
     */
    private final String userName;

    /**
     * 이메일.
     */
    private final String email;

    /**
     * User 객체를 DTO로 변환합니다.
     *
     * @param user 변환할 User 객체
     * @return UserResponseDto 객체
     */
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
