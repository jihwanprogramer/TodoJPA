package org.example.todojpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private final String userName;

    private final String email;

    private final String password;


}
