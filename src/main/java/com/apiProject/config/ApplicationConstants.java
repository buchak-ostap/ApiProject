package com.apiProject.config;

public interface ApplicationConstants {

    String SS_BASE_URL = "https://space.sombrainc.com/";
    String LOGIN_ENDPOINT = "api/authentication";
    String USER_PROFILE = "api/contacts/";
    String CREATE_TIME_LOG = "api/timeLog";
    String DELETE_TIME_LOG = "api/timeLog/";
    String TIME_LOG_MONTH = "/api/timeLog/month";

    //Messages
    String TIME_LOG_SUCCESSFUL_DELETE_MESSAGE = "The time log was successfully deleted.";

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
