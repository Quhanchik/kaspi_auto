package com.example.demo.service;

import com.example.demo.entity.Market;
import com.example.demo.entity.User;
import com.example.demo.repository.MarketRepository;
import com.example.demo.utils.MarketAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    public void save(Market market) throws MarketAlreadyExist {
        Optional<Market> marketOptional = marketRepository.getMarketByMarketId(market.getMarketId());

        if(marketOptional.isPresent()) {
            throw new MarketAlreadyExist();
        }

        marketRepository.save(market);
    }

    public BasicHeader[] getDefaultHeaders(String contentType) {
        List<BasicHeader> headers = new ArrayList<>();

        headers.add(new BasicHeader("accept", "application/json, text/plain, */*"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 YaBrowser/24.1.0.0 Safari/537.36"));
        headers.add(new BasicHeader("content-type", contentType));
        headers.add(new BasicHeader("Referer", "https://kaspi.kz/mc/"));
        headers.add(new BasicHeader("Referrer-Policy", "strict-origin-when-cross-origin"));

        return headers.toArray(BasicHeader[]::new);
    }

    public BasicHeader[] getHeadersWithCookie(String contentType, String cookie) {
        List<BasicHeader> headers = new ArrayList<>();

        headers.add(new BasicHeader("accept", "application/json, text/plain, */*"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 YaBrowser/24.1.0.0 Safari/537.36"));
        headers.add(new BasicHeader("content-type", contentType));
        headers.add(new BasicHeader("Referer", "https://kaspi.kz/mc/"));
        headers.add(new BasicHeader("Referrer-Policy", "strict-origin-when-cross-origin"));
        headers.add(new BasicHeader("cookie", String.format("_ga=GA1.1.164084696.1708629895; ssaid=0f5c9f20-d1b8-11ee-89f5-1fd379db52a1; test.user.group=73; test.user.group_exp=7; test.user.group_exp2=81; _hjSessionUser_283363=eyJpZCI6IjIzM2RjZDRhLTliMmEtNTJmYi1iMzNjLWZmN2IwNjgwMjEyMSIsImNyZWF0ZWQiOjE3MDk1NDU5NTY0ODAsImV4aXN0aW5nIjp0cnVlfQ==; _ga_0R30CM934D=GS1.1.1709894944.2.1.1709895279.60.0.0; _ym_uid=1709896177112036033; _ym_d=1709896177; _ga_6273EB2NKQ=GS1.1.1710181037.1.0.1710181037.0.0.0; _hjSession_283363=eyJpZCI6IjIyMTY0ZTBhLWFjMjAtNDQ5Ny04NWU4LTdmMzI4MDIzMzkzMyIsImMiOjE3MTExODI4OTMzMTgsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MX0=; mc-session=1711182916.687.9613.329610|825e5f3659dba1ed7b5d7b2cbf5f1012; ks.ngs.m=ee67734cd775bbcf4056a60a267a5209; amp_6e9c16=1qH5ms0K78MnTpaHAe9Mc3...1hpl75ka2.1hpl8tvtp.3m.0.3m; X-Mc-Api-Session-Id=%s", cookie)));

        return headers.toArray(BasicHeader[]::new);
    }

    public List<Market> getAll() {
        return marketRepository.findAll();
    }

    public List<Market> getMarketsByUser(User user) {
        return marketRepository.getMarketsByUser(user);
    }
}
