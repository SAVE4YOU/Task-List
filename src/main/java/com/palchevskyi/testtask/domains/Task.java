package com.palchevskyi.testtask.domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean isDone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getUserName() {
        return user!= null ? user.getUsername() : "No author";
    }

    public Task(String text, User user) {
        this.text = text;
        this.user = user;
     }
    public Task () {}
}
