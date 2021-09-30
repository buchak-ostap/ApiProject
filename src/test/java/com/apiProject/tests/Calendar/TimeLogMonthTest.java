package com.apiProject.tests.Calendar;

import com.apiProject.BaseTest;
import com.apiProject.model.timeLog.TimeLogMonth;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.util.DateConstants.getCurrentMonth;
import static com.apiProject.util.DateConstants.getCurrentYear;
import static org.springframework.http.HttpStatus.OK;

public class TimeLogMonthTest extends BaseTest {

    @Test
    public void timeLogMonthTest() {
        final String response = getTimeLogMonth(getCurrentMonth(), getCurrentYear(), OK.value());
        TimeLogMonth actual = JacksonUtil.deserializeWithDate(response, TimeLogMonth.class);
        System.out.println(actual);
    }
}
