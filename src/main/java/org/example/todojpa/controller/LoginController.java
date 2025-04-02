package org.example.todojpa.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.todojpa.common.Const;
import org.example.todojpa.dto.LoginRequestDto;
import org.example.todojpa.dto.LoginResponseDto;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.service.LoginService;
import org.example.todojpa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/session-login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletRequest request){
        LoginResponseDto loginResponseDto = loginService.login(dto.getEmail(), dto.getPassword());

        if(loginResponseDto.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(loginResponseDto.getId());
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
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
