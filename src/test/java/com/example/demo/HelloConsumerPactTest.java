package com.example.demo;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.ConsumerPactTest;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.core.model.RequestResponsePact;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(PactConsumerTestExt.class)
class HelloConsumerPactTest extends ConsumerPactTest {

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("default state")
                .uponReceiving("hello request")
                .path("/hello")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("Hello, World")
                .toPact();
    }

    @Override
    protected String providerName() {
        return "hello-provider";
    }

    @Override
    protected String consumerName() {
        return "hello-consumer";
    }

    @Override
    protected void runTest(MockServer mockServer) {
        RestAssured.given().baseUri(mockServer.getUrl())
                .when().get("/hello")
                .then().statusCode(200)
                .body(equalTo("Hello, World"));
    }
}
