package com.example.automation.utils.commonUtils;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtils {
   
    public Response sendGetRequest(String url) {
        return RestAssured.given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }
    public Response sendPostRequest(String url, Object body, String contentType) {
        return RestAssured.given()
                .contentType(contentType)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public Response sendPutRequest(String url, Object body, String contentType) {
        return RestAssured.given()
                .contentType(contentType)
                .body(body)
                .when()
                .put(url)
                .then()
                .extract()
                .response();
    }

    public Response sendDeleteRequest(String url) {
        return RestAssured.given()
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }
}
