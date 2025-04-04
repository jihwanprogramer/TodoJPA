package org.example.todojpa.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todojpa.common.Const;
import org.example.todojpa.dto.TodoRequestDto;
import org.example.todojpa.dto.TodoResponseDto;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * 새로운 Todo 항목을 저장하는 API입니다.
     *
     * @param todoRequestDto Todo 요청 데이터
     * @param request HTTP 요청 객체
     * @return 생성된 Todo의 응답 데이터와 함께 201 Created 상태 코드
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> save(@Valid @RequestBody TodoRequestDto todoRequestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        TodoResponseDto save = todoService.save(loginUser.getEmail(), todoRequestDto.getTitle(), todoRequestDto.getContents());

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * 모든 Todo 항목을 조회하는 API입니다.
     *
     * @return Todo 목록과 함께 200 OK 상태 코드
     */
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAll() {

        List<TodoResponseDto> todoResponseList = todoService.findAll();

        return new ResponseEntity<>(todoResponseList, HttpStatus.OK);
    }

    /**
     * ID로 Todo 항목을 조회하는 API입니다.
     *
     * @param id Todo 항목의 ID
     * @return 조회된 Todo의 응답 데이터와 함께 200 OK 상태 코드
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id) {

        TodoResponseDto find = todoService.findById(id);

        return new ResponseEntity<>(find, HttpStatus.OK);
    }

    /**
     * Todo 항목을 수정하는 API입니다.
     *
     * @param id Todo 항목의 ID
     * @param todoRequestDto 수정할 Todo 데이터
     * @return 수정된 Todo의 응답 데이터와 함께 200 OK 상태 코드
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@Valid @PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {

        TodoResponseDto updateTodo = todoService.updateTodo(id, todoRequestDto.getTitle(), todoRequestDto.getContents());

        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    /**
     * Todo 항목을 삭제하는 API입니다.
     *
     * @param id Todo 항목의 ID
     * @return 200 OK 상태 코드
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {

        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
