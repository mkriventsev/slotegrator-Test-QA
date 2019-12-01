package com.slotegrator.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;


class RequestHelper {

    private static RequestSpecBuilder requestSpecificationBuilder;

    static RequestSpecBuilder getRequestSpecificationBuilder() {
        createRequestSpecification();
        return requestSpecificationBuilder;
    }

    private static void createRequestSpecification() {
        requestSpecificationBuilder = new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URI)
                .setBasePath(Constants.BASE_PATH)
                .addHeader("Accept", ContentType.JSON.getAcceptHeader());
    }

    static Response auth(String grantTypeValue, Map authParams) {
        createRequestSpecification();

        RequestSpecification authParamsSpecification = requestSpecificationBuilder
                .addParams(authParams)
                .build();

        return given().urlEncodingEnabled(true)
                .auth().preemptive()
                .basic(Constants.BASIC_AUTH_TOKEN, "")
                .param("grant_type", grantTypeValue)
                .spec(authParamsSpecification)
                .when()
                .log().all()
                .post(Constants.OAUTH2);
    }
}