package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.config.PasswordEncoder;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto save(String name, String email, String password) {

        User user = new User(name, email, passwordEncoder.encode(password));
        User save = userRepository.save(user);

        return new UserResponseDto(save.getId(), save.getUsername(), save.getEmail());

    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return UserResponseDto.toDto(findUser);
    }

    public UserResponseDto updateUser(Long id, String username, String email) {
        User updateUser = userRepository.findByIdOrElseThrow(id);
        updateUser.updateUser(username, email);
        return UserResponseDto.toDto(updateUser);
    }

    public void deleteUser(Long id) {
        User deleteUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(deleteUser);
    }

}
