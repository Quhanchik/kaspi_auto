package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "_user")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"markets"})
public class User {
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String login;

    @JsonIgnore
    String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Market> markets;
}
