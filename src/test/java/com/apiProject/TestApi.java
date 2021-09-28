package com.apiProject;

import com.apiProject.model.contacts.ProfileDto;
import com.apiProject.util.FileUtils;
import com.apiProject.util.json.JacksonUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestApi extends BaseTest {

    public static final String CONTACT_PROFILE_USERS_PATH = "/testData/jsons/contactProfile/";

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

        String response = getProfile(id);
        ProfileDto actual = JacksonUtil.deserialize(response, ProfileDto.class);
        Assert.assertEquals(actual, expected);
    }
}
