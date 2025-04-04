package org.example.todojpa.service;

import lombok.Locked;
import lombok.RequiredArgsConstructor;
import org.example.todojpa.dto.TodoResponseDto;
import org.example.todojpa.entity.Todo;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.TodoRepository;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    /**
     * 새로운 Todo를 저장합니다.
     *
     * @param email 사용자 이메일
     * @param title Todo 제목
     * @param contents Todo 내용
     * @return 저장된 Todo의 응답 DTO
     */
    @Transactional
    public TodoResponseDto save(String email, String title, String contents) {
        User findUser = userRepository.findByEmailOrElseThrow(email);

        Todo todo = new Todo(title, contents);
        todo.setUser(findUser);

        Todo save = todoRepository.save(todo);

        return new TodoResponseDto(save.getId(), email, save.getTitle(), save.getContents());
    }

    /**
     * 모든 Todo를 조회합니다.
     *
     * @return Todo 응답 DTO의 리스트
     */
    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll().stream().map(TodoResponseDto::toDto).toList();
    }

    /**
     * ID로 Todo를 찾습니다.
     *
     * @param id 찾을 Todo의 ID
     * @return 찾은 Todo의 응답 DTO
     */
    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        return TodoResponseDto.toDto(findTodo);
    }

    /**
     * Todo를 업데이트합니다.
     *
     * @param id Todo의 ID
     * @param title 새로운 제목
     * @param contents 새로운 내용
     * @return 업데이트된 Todo의 응답 DTO
     */
    @Transactional
    public TodoResponseDto updateTodo(Long id, String title, String contents) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        findTodo.updateTodo(title, contents);
        return TodoResponseDto.toDto(findTodo);
    }

    /**
     * Todo를 삭제합니다.
     *
     * @param id 삭제할 Todo의 ID
     */
    @Transactional
    public void deleteTodo(Long id) {
        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.delete(findTodo);
    }
}
