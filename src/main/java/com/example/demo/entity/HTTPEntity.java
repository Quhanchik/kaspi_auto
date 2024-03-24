package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class HTTPEntity {
    private String body;
    private Map<String, List<String>> header;
}
