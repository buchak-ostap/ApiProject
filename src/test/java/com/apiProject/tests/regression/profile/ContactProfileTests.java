package com.apiProject.tests.regression.profile;

import com.apiProject.BaseTest;
import com.apiProject.model.contacts.ProfileDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.*;

public class ContactProfileTests extends BaseTest {

    @DataProvider(name = "contactProfileId")
    public static Object[][] contactProfileId() {
        return new Object[][]{
                {182, "profileUser182.json"}, {183, "profileUser183.json"}
        };
    }

    @Test(dataProvider = "contactProfileId")
    public void getProfilePage(int id, String expectedJson) {
        final String name = FileUtils.getTextFromResourceFile(CONTACT_PROFILE_USERS_PATH + expectedJson);
        ProfileDto expected = JacksonUtil.deserialize(name, ProfileDto.class);

        String response = getProfile(id, OK.value());
        ProfileDto actual = JacksonUtil.deserialize(response, ProfileDto.class);
        assertEquals(actual, expected, "Profile Page info is different");
    }
}
