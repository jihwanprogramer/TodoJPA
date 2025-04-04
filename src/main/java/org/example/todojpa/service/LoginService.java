package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.Exception.PasswordEqualsCheckException;
import org.example.todojpa.config.PasswordEncoder;
import org.example.todojpa.dto.LoginResponseDto;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(String email, String password) {
        User user =userRepository.findByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordEqualsCheckException();
        }

        return new LoginResponseDto(user.getId());
    }

    public String getEmail(String email) {
        if(userRepository.hasSameEmail(email))
        {
            return "이미 존재하는 이메일 입니다";
        }else{
            return "사용 가능한 이름 입니다";
        }
    }
}
