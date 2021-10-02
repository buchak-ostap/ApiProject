package com.apiProject.tests.regression.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.verifications.CalendarVerifications.verifyTimeLogCreation;
import static com.apiProject.util.DateConstants.getLocalDate;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class ValidUpdateTimeLogTests extends BaseTest {

    @Test
    public void validUpdateTimeLogTest() {
        //Get Time Log request body
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validCreateTimeLog.json");
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Change Date Calendar to current date
        requestBody.setDateCalendar(getLocalDate());

        //Create Time Log
        String response = createTimeLog(requestBody, CREATED.value());
        CreateTimeLogDto actual = JacksonUtil.deserializeWithDate(response, CreateTimeLogDto.class);

        //Update description and time spent
        final String updateRequestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validUpdateTimeLog.json");
        final CreateTimeLogDto updateRequestBody = JacksonUtil.deserializeWithDate(updateRequestBodyJson, CreateTimeLogDto.class);
        updateRequestBody.setId(actual.getId());

        String updatedTimeLogResponse = updateTimeLog(updateRequestBody, OK.value());
        CreateTimeLogDto expected = JacksonUtil.deserializeWithDate(updatedTimeLogResponse, CreateTimeLogDto.class);

        actual.setDescription("Edited description");
        actual.setTimeSpent(600);

        //Verify if Time Log exists in calendar and delete it
        try {
            verifyTimeLogCreation(actual, expected);
        } finally {
            deleteCreatedTimeLog(actual.getId());
        }
    }
}
