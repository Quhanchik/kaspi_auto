package com.example.demo.controller;

import com.example.demo.entity.LoginCodeReq;
import com.example.demo.entity.LoginMarketReq;
import com.example.demo.entity.LoginPhoneReq;
import com.example.demo.service.MarketService;
import com.example.demo.utils.HTTP;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.message.BufferedHeader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;
//    @GetMapping("/login")
//    public ResponseEntity<?> login() throws IOException {
//        BasicHeader[] headers = marketService.getDefaultHeaders();
//
//        CloseableHttpClient client = HttpClients.createDefault();
//
//        HttpPost post = new HttpPost("https://kaspi.kz/mc/api/login");
//
//        post.setHeaders(headers);
//
//        List<NameValuePair> body = new ArrayList<>();
//        body.add(new BasicNameValuePair("username", "seidaliyevagabi@gmail.com"));
//        body.add(new BasicNameValuePair("password", "19283746511Agab!"));
//
//        post.setEntity(new UrlEncodedFormEntity(body));
//
//        CloseableHttpResponse response = client.execute(post);
//
//        Arrays.stream(response.getHeaders()).forEach(header -> {
//            System.out.println(header);
//        });
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<String> response1 = restTemplate.getForEntity("http", String.class);
//
//        return ResponseEntity.ok("ok");
//    }

}
