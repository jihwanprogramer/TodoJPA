package org.example.todojpa.controller;

import jakarta.validation.Valid;
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

    /**
     * 새로운 사용자를 등록하는 API입니다.
     *
     * @param userRequestDto 사용자 요청 데이터
     * @return 생성된 사용자의 응답 데이터와 함께 201 Created 상태 코드
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.save(userRequestDto.getUserName(), userRequestDto.getEmail(), userRequestDto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    /**
     * 모든 사용자 정보를 조회하는 API입니다.
     *
     * @return 사용자 목록과 함께 200 OK 상태 코드
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        List<UserResponseDto> userResponseList = userService.findAll();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    /**
     * ID로 사용자 정보를 조회하는 API입니다.
     *
     * @param id 사용자 ID
     * @return 조회된 사용자의 응답 데이터와 함께 200 OK 상태 코드
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    /**
     * 사용자 정보를 수정하는 API입니다.
     *
     * @param id 사용자 ID
     * @param userRequestDto 수정할 사용자 데이터
     * @return 수정된 사용자의 응답 데이터와 함께 200 OK 상태 코드
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto.getUserName(), userRequestDto.getEmail());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    /**
     * 사용자를 삭제하는 API입니다.
     *
     * @param id 사용자 ID
     * @return 200 OK 상태 코드
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
