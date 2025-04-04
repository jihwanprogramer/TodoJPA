package org.example.todojpa.repository;

import org.example.todojpa.Exception.NotFoundException;
import org.example.todojpa.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * ID로 Todo를 찾고, 존재하지 않으면 NotFoundException을 던집니다.
     *
     * @param id 찾을 Todo의 ID
     * @return 찾은 Todo 객체
     * @throws NotFoundException Todo가 존재하지 않으면 발생
     */
    default Todo findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
