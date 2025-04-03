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

    @PostMapping("/session-login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){
        LoginResponseDto loginResponseDto = loginService.login(dto.getEmail(), dto.getPassword());

        if(loginResponseDto.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpSession session = request.getSession();
        UserResponseDto loginUser = userService.findById(loginResponseDto.getId());
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(
            @NotBlank(message = "이메일 입력은 필수 입니다.")
            @Email
            @RequestParam String email){
        String result = loginService.getEmail(email);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/session-logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
