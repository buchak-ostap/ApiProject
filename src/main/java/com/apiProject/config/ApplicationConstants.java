package com.apiProject.config;

public interface ApplicationConstants {

    String SS_BASE_URL = "https://space.sombrainc.com/";
    String LOGIN_ENDPOINT_ENDPOINT = "api/authentication";
    String USER_PROFILE_ENDPOINT = "api/contacts/";
    String CREATE_UPDATE_TIME_LOG_ENDPOINT = "api/timeLog";
    String DELETE_TIME_LOG_ENDPOINT = "api/timeLog/";
    String TIME_LOG_MONTH_ENDPOINT = "/api/timeLog/month";
    String PROJECT_FOLDER_ENDPOINT = "/api/project/folder";
    String HOME_PAGE_INFO_ENDPOINT = "/api/account/home-page-info";

    //JSON's paths
    String CONTACT_PROFILE_USERS_PATH = "/testData/jsons/contactProfile/";
    String TIME_LOG_PATH = "/testData/jsons/timeLog/";
    String PROJECTS_PATH = "/testData/jsons/projects/";

    //Messages
    String TIME_LOG_SUCCESSFUL_DELETE_MESSAGE = "The time log was successfully deleted.";
    String ERROR_EMPTY_DESCRIPTION = "You can't create a time log without description.";
    String ERROR_EMPTY_TIME_SPENT = "Time spent can't be a null";
    String ERROR_FUTURE_DATE_TIME_LOG_CREATE = "You cannot create time log on a future date ";

    //Login constants
    String J_USERNAME = "j_username";
    String J_PASSWORD = "j_password";
    String COOKIE = "Cookie";
    String OSTAP_EMAIL = "ostap.buchak@sombrainc.com";
    String OSTAP_PASSWORD = "TallTree123";

    //Params
    String MONTH = "month";
    String YEAR = "year";
}
