package com.apiProject.tests.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.verifications.CalendarVerifications.verifyErrorMessage;
import static com.apiProject.util.DateConstants.getLocalDate;
import static org.springframework.http.HttpStatus.*;

public class InvalidUpdateTimeLogTests extends BaseTest {

    public static final String TIME_LOG_PATH = "/testData/jsons/timeLog/";

    @Test
    public void updateTimeLogWithoutDescriptionTest() {
        //Get Time Log request body
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validCreateTimeLog.json");
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Change Date Calendar to current date
        requestBody.setDateCalendar(getLocalDate());

        //Create Time Log
        String response = createTimeLog(requestBody, CREATED.value());
        CreateTimeLogDto timeLogResponse = JacksonUtil.deserializeWithDate(response, CreateTimeLogDto.class);

        //Update description and time spent
        final String updateRequestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validUpdateTimeLog.json");
        final CreateTimeLogDto updateRequestBody = JacksonUtil.deserializeWithDate(updateRequestBodyJson, CreateTimeLogDto.class);
        updateRequestBody.setId(timeLogResponse.getId());

        //Set empty description
        updateRequestBody.setDescription("");
        String actualJson = updateTimeLog(updateRequestBody, BAD_REQUEST.value());
        MessageDto actual = JacksonUtil.deserialize(actualJson, MessageDto.class);

        //Verify error message and delete Time Log
        try {
            verifyErrorMessage(actual, ERROR_EMPTY_DESCRIPTION, false);
        } finally {
            deleteCreatedTimeLog(timeLogResponse.getId());
        }
    }
}
