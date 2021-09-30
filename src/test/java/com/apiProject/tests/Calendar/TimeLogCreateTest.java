package com.apiProject.tests.Calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.*;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import java.util.List;

import static com.apiProject.tests.Calendar.CalendarVerifications.verifyTimeLogCreation;
import static com.apiProject.tests.Calendar.CalendarVerifications.veryfyTimeLogExistsInCalendar;
import static com.apiProject.util.DateConstants.*;
import static com.apiProject.util.TimeLogUtils.getTimeLog;
import static org.springframework.http.HttpStatus.*;

public class TimeLogCreateTest extends BaseTest {

    public static final String TIME_LOG_PATH = "/testData/jsons/timeLog/validCreateTimeLog.json";

    @Test
    public void createNewTimeLog() {
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

        //Verify if Time Log exists in calendar
        verifyTimeLogCreation(actual, expected);

        //Delete created Time Log
        deleteCreatedTimeLog(actual.getId());
    }

    @Test
    public void getTimeLogFromCalendar() {
        //Get request body to create Time Log
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH);
        final CreateTimeLogDto requestBody = JacksonUtil.deserializeWithDate(requestBodyJson, CreateTimeLogDto.class);

        //Create new Time Log
        requestBody.setDateCalendar(getLocalDate());
        String expectedJson = createTimeLog(requestBody, CREATED.value());
        CreateTimeLogDto expected = JacksonUtil.deserializeWithDate(expectedJson, CreateTimeLogDto.class);
        int timeLogId = expected.getId();

        //Get Time Log from calendar
        final String response = getTimeLogMonth(getCurrentMonth(), getCurrentYear(), OK.value());
        TimeLogMonth actualResponse = JacksonUtil.deserializeWithDate(response, TimeLogMonth.class);
        List<DayDtos> listOfDays = actualResponse.getDayDTOS();
        TimeLogListDto actual = getTimeLog(listOfDays, timeLogId);

        //Verify if Time Log exists in calendar
        veryfyTimeLogExistsInCalendar(actual, expected);

        //Delete created Time Log
        deleteCreatedTimeLog(timeLogId);
    }


}
