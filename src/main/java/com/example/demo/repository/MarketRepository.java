package com.example.demo.repository;

import com.example.demo.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {
    Optional<Market> getMarketByMarketId(String id);
}
