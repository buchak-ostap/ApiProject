package com.apiProject.tests.regression.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.verifications.CalendarVerifications.verifyErrorMessage;
import static com.apiProject.util.DateConstants.getLocalDate;
import static com.apiProject.util.DateConstants.getNextDayDate;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class InvalidCreateTimeLogTests extends BaseTest {

    @Test
    public void createNewTimeLogWithoutTimeSpentTest() {
        //Get Time Log request body
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validCreateTimeLog.json");
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Change Date Calendar to current date
        requestBody.setDateCalendar(getLocalDate());

        //Set 0 Time spent
        requestBody.setTimeSpent(0);
        requestBody.setHours(0);
        requestBody.setInSeconds(0);

        //Create Time Log
        String response = createTimeLog(requestBody, BAD_REQUEST.value());
        MessageDto actual = JacksonUtil.deserialize(response, MessageDto.class);
        verifyErrorMessage(actual, ERROR_EMPTY_TIME_SPENT, false);
    }

    @Test
    public void createNewTimeLogForNextDayTest() {
        //Get Time Log request body
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validCreateTimeLog.json");
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Change Date Calendar to next date
        requestBody.setDateCalendar(getNextDayDate());

        //Create Time Log
        String response = createTimeLog(requestBody, BAD_REQUEST.value());
        MessageDto actual = JacksonUtil.deserialize(response, MessageDto.class);
        verifyErrorMessage(actual, ERROR_FUTURE_DATE_TIME_LOG_CREATE, false);
    }
}
