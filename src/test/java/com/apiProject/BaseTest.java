package com.apiProject;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest extends CRUD {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = SS_BASE_URL;
    }
}
