package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.dto.LoginResponseDto;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public LoginResponseDto login(String email, String password) {
        User user =userRepository.findByEmailOrElseThrow(email);

        if(!user.getPassword().equals(password)){
            return new LoginResponseDto(null);
        }

        return new LoginResponseDto(user.getId());
    }
}
