package org.example.todojpa.repository;

import org.example.todojpa.Exception.EmailFoundException;
import org.example.todojpa.Exception.NotFoundException;
import org.example.todojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new EmailFoundException(email));
    }

    default boolean hasSameEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
