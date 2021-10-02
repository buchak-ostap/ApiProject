package com.apiProject.tests.smoke;

import com.apiProject.BaseTest;
import com.apiProject.model.contacts.ProfileDto;
import com.apiProject.model.projects.ProjectFolderDto;
import com.apiProject.model.timeLog.TimeLogMonth;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.Test;

import static com.apiProject.util.DateConstants.getCurrentMonth;
import static com.apiProject.util.DateConstants.getCurrentYear;
import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.*;

public class SsSmokeTests extends BaseTest {

    @Test
    public void HomePageInfoSmokeTest() {
        final String response = getHomePageInfo(OK.value());
        assertNotNull(response);
    }

    @Test
    public void TimeLogsListSmokeTest() {
        final String response = getTimeLogMonth(getCurrentMonth(), getCurrentYear(), OK.value());
        TimeLogMonth actual = JacksonUtil.deserializeWithDate(response, TimeLogMonth.class);
        assertNotNull(actual);
    }

    @Test
    public void ProjectFolderSmokeTest() {
        final String response = getProjectFolder(OK.value());
        ProjectFolderDto actual = JacksonUtil.deserializeWithDate(response, ProjectFolderDto.class);
        assertNotNull(actual);
    }

    @Test
    public void ProfileSmokeTest() {
        String response = getProfile(182, OK.value());
        ProfileDto actual = JacksonUtil.deserialize(response, ProfileDto.class);
        assertNotNull(actual);
    }
}
