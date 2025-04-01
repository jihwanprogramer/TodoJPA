package org.example.todojpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

//  @Column(nullable = false)
//  private String password;


    public User() {
    }

    @Column(nullable = true)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
