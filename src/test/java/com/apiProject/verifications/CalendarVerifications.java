package com.apiProject.verifications;

import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.model.timeLog.TimeLogListDto;

import static org.testng.Assert.*;

public class CalendarVerifications {

    public static void verifyTimeLogExistsInCalendar(TimeLogListDto actual, CreateTimeLogDto expected) {
        assertEquals(actual.getId(), expected.getId(), "ID value if different");
        assertEquals(actual.getTimeSpent(), expected.getTimeSpent(), "Time Spent value is different");
        assertEquals(actual.getProjectId(), expected.getProjectId(), "Project ID value is different");
        assertEquals(actual.getDescription(), expected.getDescription(), "Description value is different");
        assertEquals(actual.getCreatedByEmail(), expected.getCreatedByEmail(), "Created By Email value is different");
        assertEquals(actual.getDateCalendar(), expected.getDateCalendar(), "Date Calendar value is different");
    }

    public static void verifyTimeLogCreation(CreateTimeLogDto actual, CreateTimeLogDto expected) {
        assertEquals(actual.getTimeSpent(), expected.getTimeSpent(), "Time spent value is different");
        assertEquals(actual.getProjectId(), expected.getProjectId(), "Project ID is different");
        assertEquals(actual.getDescription(), expected.getDescription(), "Description is different");
        assertEquals(actual.getDateCalendar(), expected.getDateCalendar(), "Date Calendar is different");
        assertEquals(actual.getCreatedByEmail(), expected.getCreatedByEmail(), "Created by email is different");
    }

    public static void verifyErrorMessage(MessageDto actual, String message) {
        assertEquals(actual.getMessage(), message, "Message is different");
    }

    public static void verifySuccessMessage(MessageDto actual, String message, Boolean status) {
        assertEquals(actual.getMessage(), message, "Message is different");
        if (status.equals(true)) {
            assertTrue(actual.getSuccess(), "Message should be successful");
        } else {
            assertFalse(actual.getSuccess(), "Message should be unsuccessful");
        }
    }
}
