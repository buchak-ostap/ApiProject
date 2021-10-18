package com.apiProject;

import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static com.apiProject.verifications.CalendarVerifications.verifySuccessMessage;
import static org.springframework.http.HttpStatus.OK;

public class BaseTest extends CRUD {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = SS_BASE_URL;
    }

    @Step
    public void deleteCreatedTimeLog(int timeLogId) {
        String timeLogDeleteMessageJson = deleteTimeLog(timeLogId, OK.value());
        MessageDto actual = JacksonUtil.deserialize(timeLogDeleteMessageJson, MessageDto.class);

        verifySuccessMessage(actual, TIME_LOG_SUCCESSFUL_DELETE_MESSAGE, true);
    }
}
