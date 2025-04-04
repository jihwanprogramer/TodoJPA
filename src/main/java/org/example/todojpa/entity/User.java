package org.example.todojpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /**
     * 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 이름.
     */
    @Column(nullable = false)
    private String username;

    /**
     * 비밀번호.
     */
    @Column(nullable = false)
    private String password;

    /**
     * 이메일.
     */
    @Column(nullable = true, unique = true)
    private String email;

    /**
     * 기본 생성자.
     */
    public User() {
    }

    /**
     * User 객체 생성자입니다.
     *
     * @param username 사용자 이름
     * @param email 이메일
     * @param password 비밀번호
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param username 사용자 이름
     * @param email 이메일
     */
    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
