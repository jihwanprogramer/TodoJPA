package org.example.todojpa.controller;

import lombok.RequiredArgsConstructor;

import org.example.todojpa.dto.UserRequestDto;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.save(userRequestDto.getUserName(), userRequestDto.getEmail(),userRequestDto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        List<UserResponseDto> userResponseList = userService.findAll();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto.getUserName(), userRequestDto.getEmail());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
