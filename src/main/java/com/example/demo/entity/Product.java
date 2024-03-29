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
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    String marketId;

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

}
