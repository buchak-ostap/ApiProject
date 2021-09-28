package com.apiProject;

import com.apiProject.config.ApplicationConstants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.apiProject.config.SessionProvider.getSessionToken;
import static io.restassured.RestAssured.given;

public class CRUD implements ApplicationConstants {

    @Step
    public String getProfile(int id) {
        Response response = given()
                .header(COOKIE, getSessionToken(OSTAP_EMAIL, OSTAP_PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .get(USER_PROFILE + id)
                .then()
                .extract().response();
        return response.body().asString();
    }
}
