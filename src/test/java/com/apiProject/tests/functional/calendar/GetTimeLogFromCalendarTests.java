package com.apiProject.tests.functional.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.DayDtos;
import com.apiProject.model.timeLog.TimeLogListDto;
import com.apiProject.model.timeLog.TimeLogMonth;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

import static com.apiProject.verifications.CalendarVerifications.verifyTimeLogExistsInCalendar;
import static com.apiProject.util.DateConstants.*;
import static com.apiProject.util.TimeLogUtils.getTimeLog;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class GetTimeLogFromCalendarTests extends BaseTest {

    @Test
    @Description("Get all Time Logs existed in Calendar and verify if created Time Log exists there")
    public void getTimeLogFromCalendarTest() {
        //Get request body to create Time Log
        final String requestBodyJson = FileUtils.getTextFromResourceFile(TIME_LOG_PATH + "validCreateTimeLog.json");
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

        //Verify if Time Log exists in calendar and delete it
        try {
            verifyTimeLogExistsInCalendar(actual, expected);
        } finally {
            deleteCreatedTimeLog(timeLogId);
        }
    }
}
