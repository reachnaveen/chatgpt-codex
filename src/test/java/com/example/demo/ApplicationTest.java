package com.example.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @LocalServerPort
    private int port;

    @Test
    void helloEndpoint() {
        RestAssured.given().port(port)
                .when().get("/hello")
                .then().statusCode(200)
                .body(equalTo("Hello, World"));
    }
}
