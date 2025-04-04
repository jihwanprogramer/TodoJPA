package org.example.todojpa.dto;


import lombok.Data;
import org.example.todojpa.entity.User;

@Data
public class UserResponseDto {
    private final Long id;

    private final String userName;

    private final String email;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
