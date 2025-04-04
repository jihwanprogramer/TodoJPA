package org.example.todojpa.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.example.todojpa.common.Const;
import org.example.todojpa.dto.LoginRequestDto;
import org.example.todojpa.dto.LoginResponseDto;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.service.LoginService;
import org.example.todojpa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    /**
     * 사용자의 로그인 요청을 처리합니다.
     *
     * @param dto 로그인 요청 데이터
     * @param request HTTP 요청 객체
     * @return 로그인 응답 데이터와 함께 200 OK 상태 코드 또는 404 Not Found
     */

    @PostMapping("/session-login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {
        LoginResponseDto loginResponseDto = loginService.login(dto.getEmail(), dto.getPassword());

        if (loginResponseDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpSession session = request.getSession();
        UserResponseDto loginUser = userService.findById(loginResponseDto.getId());
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    /**
     * 사용자의 로그아웃 요청을 처리합니다.
     *
     * @param request HTTP 요청 객체
     * @return 200 OK 상태 코드
     */

    @PostMapping("/session-logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 이메일의 존재 여부를 확인하는 API입니다.
     *
     * @param email 확인할 이메일
     * @return 이메일의 존재 여부 결과와 함께 200 OK 상태 코드
     */

    @GetMapping("/check")
    public ResponseEntity<String> check(
            @NotBlank(message = "이메일 입력은 필수 입니다.")
            @Email
            @RequestParam String email) {
        String result = loginService.getEmail(email);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
