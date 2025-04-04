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

    @PostMapping
    public ResponseEntity<TodoResponseDto> save(@Valid @RequestBody TodoRequestDto todoRequestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        TodoResponseDto save = todoService.save(loginUser.getEmail(), todoRequestDto.getTitle(), todoRequestDto.getContents());

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findAll() {

        List<TodoResponseDto> todoResponseList = todoService.findAll();

        return new ResponseEntity<>(todoResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id) {

        TodoResponseDto find = todoService.findById(id);

        return new ResponseEntity<>(find, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@Valid @PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {

        TodoResponseDto updateTodo = todoService.updateTodo(id, todoRequestDto.getTitle(), todoRequestDto.getContents());

        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {

        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
