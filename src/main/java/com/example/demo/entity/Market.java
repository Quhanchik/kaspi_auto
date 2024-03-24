package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Table(name = "market")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market implements Persistable, Serializable {
    @Id
    private String marketId;
    private String phoneNumber;
    private Boolean isEnable;
    private String cookie;

    @Override
    public Object getId() {
        return marketId;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
