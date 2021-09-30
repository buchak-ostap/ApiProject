package com.apiProject;

import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseTest extends CRUD {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = SS_BASE_URL;
    }

    @Step
    public void deleteCreatedTimeLog(int timeLogId) {
        String timeLogDeleteMessageJson = deleteTimeLog(timeLogId, OK.value());
        MessageDto timeLogDeleteMessage = JacksonUtil.deserialize(timeLogDeleteMessageJson, MessageDto.class);
        assertEquals(timeLogDeleteMessage.getMessage(), TIME_LOG_SUCCESSFUL_DELETE_MESSAGE, "Message is different");
        assertTrue(timeLogDeleteMessage.getSuccess());
    }
}
