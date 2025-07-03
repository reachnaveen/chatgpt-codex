package com.example.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class WireMockExampleTest {
    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/hello")).willReturn(aResponse().withBody("Hello, World")));
    }

    @AfterEach
    void teardown() {
        wireMockServer.stop();
    }

    @Test
    void sampleWireMock() {
        given().baseUri("http://localhost:" + wireMockServer.port())
                .when().get("/hello")
                .then().statusCode(200).body(equalTo("Hello, World"));
    }
}
