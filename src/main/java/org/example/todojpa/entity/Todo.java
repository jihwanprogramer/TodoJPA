package org.example.todojpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {

    /**
     * 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 제목.
     */
    @Column(nullable = false)
    private String title;

    /**
     * 내용.
     */
    @Column(columnDefinition = "longtext")
    private String contents;

    /**
     * 사용자.
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Todo 객체 생성자입니다.
     *
     * @param title 제목
     * @param contents 내용
     */
    public Todo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    /**
     * 기본 생성자.
     */
    public Todo() {
    }

    /**
     * Todo 정보를 업데이트합니다.
     *
     * @param title 제목
     * @param contents 내용
     */
    public void updateTodo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
