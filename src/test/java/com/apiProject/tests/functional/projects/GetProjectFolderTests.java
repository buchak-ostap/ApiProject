package com.apiProject.tests.functional.projects;

import com.apiProject.BaseTest;
import com.apiProject.model.projects.ProjectFolderDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.assertEquals;

public class GetProjectFolderTests extends BaseTest {

    @Test
    @Description("Verify if user can get Project folder info")
    public void getProjectFolderInfoTest() {
        final String expectedJson = FileUtils.getTextFromResourceFile(PROJECTS_PATH + "projectsList.json");
        final ProjectFolderDto expected = JacksonUtil.deserialize(expectedJson, ProjectFolderDto.class);

        final String response = getProjectFolder(OK.value());
        ProjectFolderDto actual = JacksonUtil.deserializeWithDate(response, ProjectFolderDto.class);

        assertEquals(actual.getProjectFolders(), expected.getProjectFolders(), "Project Folders are different");
        assertEquals(actual.getProjects(), expected.getProjects(), "Projects are different");
    }
}
