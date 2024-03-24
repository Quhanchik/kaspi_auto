package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Table(name = "product_metadata")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Persistable, Serializable {
    @Column(name = "product_id")
    @Id
    String productId;

    @Column(name = "self_cost")
    double selfCost;

    @Column(name = "weight")
    double weight;

    @Column(name = "damping_cost")
    Integer dampingCost;

    @Column(name = "min_profit_percent")
    double minProfitPercent;

    @Column(name = "amount")
    Integer amount;

    @Column(name = "seller_id")
    Integer sellerId;

    @Override
    public Object getId() {
        return productId;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
