package org.example.todojpa.service;

import lombok.RequiredArgsConstructor;
import org.example.todojpa.config.PasswordEncoder;
import org.example.todojpa.dto.UserResponseDto;
import org.example.todojpa.entity.User;
import org.example.todojpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 새로운 사용자를 저장합니다.
     *
     * @param name 사용자 이름
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 저장된 사용자 응답 DTO
     */
    @Transactional
    public UserResponseDto save(String name, String email, String password) {
        User user = new User(name, email, passwordEncoder.encode(password));
        User save = userRepository.save(user);

        return new UserResponseDto(save.getId(), save.getUsername(), save.getEmail());
    }

    /**
     * 모든 사용자를 조회합니다.
     *
     * @return 사용자 응답 DTO의 리스트
     */
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    /**
     * ID로 사용자를 찾습니다.
     *
     * @param id 찾을 사용자의 ID
     * @return 찾은 사용자 응답 DTO
     */
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return UserResponseDto.toDto(findUser);
    }

    /**
     * 사용자의 정보를 업데이트합니다.
     *
     * @param id 사용자의 ID
     * @param username 새로운 사용자 이름
     * @param email 새로운 사용자 이메일
     * @return 업데이트된 사용자 응답 DTO
     */
    @Transactional
    public UserResponseDto updateUser(Long id, String username, String email) {
        User updateUser = userRepository.findByIdOrElseThrow(id);
        updateUser.updateUser(username, email);
        return UserResponseDto.toDto(updateUser);
    }

    /**
     * 사용자를 삭제합니다.
     *
     * @param id 삭제할 사용자의 ID
     */
    @Transactional
    public void deleteUser(Long id) {
        User deleteUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(deleteUser);
    }
}
