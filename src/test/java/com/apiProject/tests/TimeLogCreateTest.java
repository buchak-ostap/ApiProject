package com.apiProject.tests;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.springframework.http.HttpStatus.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TimeLogCreateTest extends BaseTest {

    public static final String TIME_LOG_PATH = "/testData/jsons/timeLog/validCreateTimeLog.json";

    @Test
    public void createNewTimeLog() {
        final String requestBody = FileUtils.getTextFromResourceFile(TIME_LOG_PATH);
        CreateTimeLogDto expected = JacksonUtil.deserializeWithDate(requestBody, CreateTimeLogDto.class);

        String response = createTimeLog(requestBody, CREATED.value());
        CreateTimeLogDto actual = JacksonUtil.deserializeWithDate(response, CreateTimeLogDto.class);

        verifyTimeLogCreation(actual, expected);
        deleteCreatedTimeLog(actual.getId());
    }

    @Step
    public void deleteCreatedTimeLog(int timeLogId) {
        String timeLogDeleteMessageJson = deleteTimeLog(timeLogId, OK.value());
        MessageDto timeLogDeleteMessage = JacksonUtil.deserialize(timeLogDeleteMessageJson, MessageDto.class);
        assertEquals(timeLogDeleteMessage.getMessage(), TIME_LOG_SUCCESSFUL_DELETE_MESSAGE, "Message is different");
        assertTrue(timeLogDeleteMessage.getSuccess());
    }

    public void verifyTimeLogCreation(CreateTimeLogDto actual, CreateTimeLogDto expected) {
        assertEquals(actual.getTimeSpent(), expected.getTimeSpent(), "Time spent value is different");
        assertEquals(actual.getProjectId(), expected.getProjectId(), "Project ID is different");
        assertEquals(actual.getDescription(), expected.getDescription(), "Description is different");
        assertEquals(actual.getDateCalendar(), expected.getDateCalendar(), "Date Calendar is different");
        assertEquals(actual.getCreatedByEmail(), expected.getCreatedByEmail(), "Created by email is different");
    }
}
