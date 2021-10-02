package com.apiProject.tests.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.*;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.verifications.CalendarVerifications.verifyTimeLogCreation;
import static com.apiProject.util.DateConstants.*;
import static org.springframework.http.HttpStatus.*;

public class TimeLogCreateTests extends BaseTest {

    public static final String TIME_LOG_PATH = "/testData/jsons/timeLog/validCreateTimeLog.json";

    @Test
    public void createNewTimeLogTest() {
        //Get Time Log request body
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH);
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);
        CreateTimeLogDto expected = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Change Date Calendar to current date
        expected.setDateCalendar(getLocalDate());
        requestBody.setDateCalendar(getLocalDate());

        //Create Time Log
        String response = createTimeLog(requestBody, CREATED.value());
        CreateTimeLogDto actual = JacksonUtil.deserializeWithDate(response, CreateTimeLogDto.class);

        //Verify if Time Log exists in calendar and delete it
        try {
            verifyTimeLogCreation(actual, expected);
        } finally {
            deleteCreatedTimeLog(actual.getId());
        }
    }
}
