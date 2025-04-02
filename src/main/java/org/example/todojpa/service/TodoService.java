package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.dto.TodoResponseDto;
import org.example.todojpa.entity.Todo;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.TodoRepository;
import org.example.todojpa.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoResponseDto save(String email,String password,String title, String contents) {

        User findUser = userRepository.findByEmailOrElseThrow(email);

        if(!findUser.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지않음");
        }

        Todo todo = new Todo(title, contents);
        todo.setUser(findUser);

        Todo save = todoRepository.save(todo);

        return new TodoResponseDto(save.getId(),save.getUser().getEmail(),save.getTitle(), save.getContents());

    }

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll().stream().map(TodoResponseDto::toDto).toList();
    }

    public TodoResponseDto findById(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);

        return TodoResponseDto.toDto(findTodo);
    }

    public TodoResponseDto updateTodo(Long id, String title, String contents) {

        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        findTodo.updateTodo(title, contents);

        return TodoResponseDto.toDto(findTodo);
    }

    public void deleteTodo(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.delete(findTodo);
    }
}
