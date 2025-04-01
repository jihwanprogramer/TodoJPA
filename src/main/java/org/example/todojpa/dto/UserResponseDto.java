package org.example.todojpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.todojpa.entity.User;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private final Long id;

    private final String userName;

    private final String email;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
