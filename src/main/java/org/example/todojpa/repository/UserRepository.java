package org.example.todojpa.repository;

import org.example.todojpa.Exception.EmailFoundException;
import org.example.todojpa.Exception.NotFoundException;
import org.example.todojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자를 찾습니다.
     *
     * @param email 찾을 사용자의 이메일
     * @return 찾은 사용자 객체(Optional)
     */
    Optional<User> findByEmail(String email);

    /**
     * ID로 사용자를 찾고, 존재하지 않으면 NotFoundException을 던집니다.
     *
     * @param id 찾을 사용자의 ID
     * @return 찾은 사용자 객체
     * @throws NotFoundException 사용자 존재하지 않을 때 발생
     */
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    /**
     * 이메일로 사용자를 찾고, 존재하지 않으면 EmailFoundException을 던집니다.
     *
     * @param email 찾을 사용자의 이메일
     * @return 찾은 사용자 객체
     * @throws EmailFoundException 이메일이 존재하지 않을 때 발생
     */
    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new EmailFoundException(email));
    }

    /**
     * 주어진 이메일이 이미 존재하는지 확인합니다.
     *
     * @param email 확인할 이메일
     * @return 이메일이 존재하면 true, 그렇지 않으면 false
     */
    default boolean hasSameEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
