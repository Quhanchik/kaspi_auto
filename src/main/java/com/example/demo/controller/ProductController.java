package com.example.demo.controller;

import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.service.MarketService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MarketService marketService;
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product) {

        productService.save(product);

        return ResponseEntity.ok("saved");
    }

    @GetMapping
    public ResponseEntity<?> getAllPagination(Authentication authentication,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("limit") Integer limit,
                                              @RequestParam("marketId") String marketId) {
        List<Market> markets = marketService.getMarketsByUser(userService.getUserByLogin(authentication.getName()).get());

        for (int i = 0; i < markets.size(); i++) {
            if (markets.get(i).getMarketId().equals(marketId)) {
                return ResponseEntity.ok(productService.getAllByMarketIdPagination(marketId, page, limit));
            }
        }

        return ResponseEntity.badRequest().body("access denied");
    }

}
