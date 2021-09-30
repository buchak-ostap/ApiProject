package com.apiProject;

import com.apiProject.config.ApplicationConstants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.apiProject.config.SessionProvider.getSessionToken;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CRUD implements ApplicationConstants {

    @Step
    public String getProfile(int id, int statusCode) {
        Response response = given()
                .header(COOKIE, getSessionToken(OSTAP_EMAIL, OSTAP_PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .get(USER_PROFILE + id)
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), statusCode);
        return response.body().asString();
    }

    @Step
    public String getTimeLogMonth(int month, int year, int statusCode) {
        Response response = given()
                .header(COOKIE, getSessionToken(OSTAP_EMAIL, OSTAP_PASSWORD))
                .contentType(ContentType.JSON)
                .when()
                .queryParam(MONTH, month)
                .queryParam(YEAR, year)
                .get(TIME_LOG_MONTH)
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), statusCode);
        return response.body().asString();
    }

    @Step
    public String createTimeLog(Object requestBody, int statusCode) {
        Response response = given()
                .header(COOKIE, getSessionToken(OSTAP_EMAIL, OSTAP_PASSWORD))
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post(CREATE_TIME_LOG)
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), statusCode);
        return response.body().asString();
    }

    @Step
    public String deleteTimeLog(int id, int statusCode) {
        Response response = given()
                .header(COOKIE, getSessionToken(OSTAP_EMAIL, OSTAP_PASSWORD))
                .when()
                .delete(DELETE_TIME_LOG + id)
                .then()
                .extract().response();
        assertEquals(response.getStatusCode(), statusCode);
        return response.body().asString();
    }
}
