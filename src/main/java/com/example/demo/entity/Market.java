package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "market")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class Market {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String marketId;
    private String phoneNumber;
    private Boolean isEnable;
    private String login;
    @JsonIgnore
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    public Market(String marketId,
                  String phoneNumber,
                  Boolean isEnable,
                  User user,
                  String login,
                  String password) {
        this.marketId = marketId;
        this.isEnable = isEnable;
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }
}
