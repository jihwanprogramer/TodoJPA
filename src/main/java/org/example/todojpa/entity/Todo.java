package org.example.todojpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;


    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


    public Todo() {

    }



    public void updateTodo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
