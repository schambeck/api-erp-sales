package com.schambeck.erp.sales.app.entrypoint.rest;

import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderLineWeb;
import com.schambeck.erp.sales.app.entrypoint.controller.model.OrderWeb;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OrderRestEntryPointIT {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void create() {
        OrderWeb payload = OrderWeb.builder()
                .clientId(UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3"))
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        given().
                basePath("orders").
                contentType(JSON).
                body(payload).
        when().
                post().
        then().
                statusCode(200).
                body("clientId", is("1750a631-3461-442e-8d99-8f0d6a127cb3"));
    }

    @Test
    @Order(2)
    void findAll() {
        given().
                basePath("orders").
        when().
                get().
        then().
                statusCode(200).
                body("clientId", hasItem("1750a631-3461-442e-8d99-8f0d6a127cb3"));
    }

    @Test
    @Order(3)
    void findById() {
        OrderWeb payload = OrderWeb.builder()
                .clientId(UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3"))
                .build();
        Response response =
        given().
                basePath("orders").
                contentType(JSON).
                body(payload).
        when().
                post();
        String id = response.jsonPath().get("id");

        given().
                basePath("orders").
        when().
                get("{id}", id).
        then().
                statusCode(200).
                body("clientId", is("1750a631-3461-442e-8d99-8f0d6a127cb3"));
    }

    @Test
    @Order(4)
    void close() {
        OrderWeb payload = OrderWeb.builder()
                .clientId(UUID.fromString("1750a631-3461-442e-8d99-8f0d6a127cb3"))
                .item(OrderLineWeb.builder()
                        .productId(UUID.fromString("c90e4992-3e48-43ca-9da3-7ca012f44236"))
                        .quantity(new BigDecimal("3.00"))
                        .price(new BigDecimal("1.50"))
                        .build())
                .build();
        Response response =
        given().
                basePath("orders").
                contentType(JSON).
                body(payload).
        when().
                post();
        String id = response.jsonPath().get("id");

        given().
                basePath("orders").
        when().
                post("{id}/close", id).
        then().
                statusCode(200);
    }
}
