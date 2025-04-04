package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.Exception.PasswordEqualsCheckException;
import org.example.todojpa.config.PasswordEncoder;
import org.example.todojpa.dto.LoginResponseDto;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자를 로그인합니다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 응답 DTO
     * @throws PasswordEqualsCheckException 비밀번호가 일치하지 않을 때 발생
     */
    @Transactional
    public LoginResponseDto login(String email, String password) {
        User user = userRepository.findByEmailOrElseThrow(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordEqualsCheckException();
        }

        return new LoginResponseDto(user.getId());
    }

    /**
     * 주어진 이메일의 사용 가능 여부를 확인합니다.
     *
     * @param email 확인할 이메일
     * @return 사용 가능 여부 메시지
     */
    @Transactional
    public String getEmail(String email) {
        if (userRepository.hasSameEmail(email)) {
            return "이미 존재하는 이메일 입니다";
        } else {
            return "사용 가능한 이름 입니다";
        }
    }
}
