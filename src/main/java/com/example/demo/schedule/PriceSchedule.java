package com.example.demo.schedule;

import com.example.demo.entity.Market;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.MarketService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.utils.HTTP;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class PriceSchedule {
    private final ProductService productService;
    private final MarketService marketService;
    private final UserService userService;

    private Integer dumpingCounter = 0;

    @Scheduled(fixedRate = 20000)
    public void update2() throws IOException, InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        BasicHeader[] loginHeaders = marketService.getDefaultHeaders("application/x-www-form-urlencoded");
        List<User> users = userService.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            List<Market> markets = user.getMarkets();

            for (int j = 0; j < markets.size(); j++) {

                Market market = markets.get(j);

                HttpPost post = new HttpPost("https://kaspi.kz/mc/api/login");

                List<NameValuePair> body = new ArrayList<>();
                body.add(new BasicNameValuePair("username", market.getLogin()));
                body.add(new BasicNameValuePair("password", market.getPassword()));

                post.setEntity(new UrlEncodedFormEntity(body));

                post.setHeaders(loginHeaders);

                CloseableHttpResponse response = client.execute(post);

                BasicHeader[] headersWithCookie = marketService.getHeadersWithCookie("application/json; charset=UTF-8", response.getLastHeader("Set-Cookie").getValue().split(";")[0].split("=")[1]);

                HttpGet get = new HttpGet(String.format("https://mc.shop.kaspi.kz/offers/api/v1/offer/count?m=%s", market.getMarketId()));
                get.setHeaders(headersWithCookie);

                response = client.execute(get);

                Integer itemsAmount = mapper.readTree(new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8)).get("published").asInt();

                get = new HttpGet(String.format("https://mc.shop.kaspi.kz/bff/offer-view/list?m=%s&p=0&l=%s&a=true&t=&c=", market.getMarketId(), itemsAmount));
                get.setHeaders(headersWithCookie);

                response = client.execute(get);

                JsonNode listOfItemsNode = mapper.readTree(new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8)).get("data");

                for (int k = 0; k < itemsAmount; k++) {
                    client = HttpClients.createDefault();

                    JsonNode item = listOfItemsNode.get(k);

                    String masterSku = item.get("masterSku").asText();
                    String sku = item.get("sku").asText();
                    String offersBody = String.format("{\"cityId\":\"750000000\",\"id\":\"%s\",\"merchantUID\":\"\",\"limit\":1,\"page\":0,\"sort\":true,\"zoneId\":\"Magnum_ZONE1\",\"installationId\":\"-1\"}", masterSku);

                    post = new HttpPost(String.format("https://kaspi.kz/yml/offer-view/offers/%s", masterSku));
                    post.setEntity(new StringEntity(offersBody));
                    post.setHeaders(marketService.getDefaultHeaders("application/json"));

                    response = client.execute(post);
                    JsonNode offersNode = mapper.readTree(new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8)).get("offers");

                    if (!offersNode.get(0).get("merchantId").asText().equals(market.getMarketId())) {
                        String jsonSend = "{\"merchantUid\":\"30082479\",\"sku\":\"510820\",\"model\":\"Внешний аккумулятор CUKTECH PB200P 20000 мАч серый\",\"price\":29990,\"availabilities\":[{\"available\":\"yes\",\"storeId\":\"30082479_PP1\"}],\"cityPrices\":[{\"value\":29990,\"cityId\":\"750000000\"}]}";

                        JsonNode jsonSendNode = mapper.readTree(jsonSend);

                        Integer lowestPrice = offersNode.get(0).get("price").asInt();

                        Optional<Product> productOptional = productService.getProductByProductId(sku);
                        if (dumpingCounter < 100) {
                            if (productOptional.isPresent()) {
                                Product product = productOptional.get();

                                if (lowestPrice - product.getDampingCost() > product.getSelfCost() * (product.getMinProfitPercent() / 100) + product.getSelfCost()) {
                                    ((ObjectNode) jsonSendNode).put("price", lowestPrice - product.getDampingCost());
                                    ((ObjectNode) jsonSendNode.get("cityPrices").get(0)).put("value", lowestPrice - product.getDampingCost());
                                    ((ObjectNode) jsonSendNode).put("sku", item.get("sku"));
                                    ((ObjectNode) jsonSendNode).put("model", item.get("model"));
                                }
                            } else {
                                ((ObjectNode) jsonSendNode).put("price", lowestPrice - 1);
                                ((ObjectNode) jsonSendNode.get("cityPrices").get(0)).put("value", lowestPrice - 1);
                                ((ObjectNode) jsonSendNode).put("sku", item.get("sku"));
                                ((ObjectNode) jsonSendNode).put("model", item.get("model"));
                            }

                            dumpingCounter++;
                        } else {
                            ((ObjectNode) jsonSendNode).put("price", lowestPrice + 200);
                            ((ObjectNode) jsonSendNode.get("cityPrices").get(0)).put("value", lowestPrice + 200);
                            ((ObjectNode) jsonSendNode).put("sku", item.get("sku"));
                            ((ObjectNode) jsonSendNode).put("model", item.get("model"));

                            dumpingCounter = 0;
                        }

                        post = new HttpPost("https://mc.shop.kaspi.kz/pricefeed/upload/merchant/process");
                        post.setEntity(new StringEntity(jsonSendNode.toString()));
                        post.setHeaders(headersWithCookie);

                        response = client.execute(post);
                    }

                    Thread.sleep(500);
                }
            }
        }
    }

}
