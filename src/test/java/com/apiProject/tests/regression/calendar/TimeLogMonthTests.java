package com.apiProject.tests.regression.calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.TimeLogMonth;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.apiProject.util.DateConstants.getCurrentMonth;
import static com.apiProject.util.DateConstants.getCurrentYear;
import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.*;

public class TimeLogMonthTests extends BaseTest {

    @Test
    @Description("Verify if user can get all Time Logs for current month")
    public void timeLogMonthTest() {
        final String response = getTimeLogMonth(getCurrentMonth(), getCurrentYear(), OK.value());
        TimeLogMonth actual = JacksonUtil.deserializeWithDate(response, TimeLogMonth.class);

        //TODO need to add more verifications
        assertNotNull(actual.getDayDTOS());
    }
}
