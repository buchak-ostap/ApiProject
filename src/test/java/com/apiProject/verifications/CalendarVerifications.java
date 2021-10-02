package com.apiProject.verifications;

import com.apiProject.model.timeLog.CreateTimeLogDto;
import com.apiProject.model.timeLog.MessageDto;
import com.apiProject.model.timeLog.TimeLogListDto;

import static org.testng.Assert.*;

public class CalendarVerifications {

    public static void verifyTimeLogExistsInCalendar(TimeLogListDto actual, CreateTimeLogDto expected) {
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getTimeSpent(), expected.getTimeSpent());
        assertEquals(actual.getProjectId(), expected.getProjectId());
        assertEquals(actual.getDescription(), expected.getDescription());
        assertEquals(actual.getCreatedByEmail(), expected.getCreatedByEmail());
        assertEquals(actual.getDateCalendar(), expected.getDateCalendar());
    }

    public static void verifyTimeLogCreation(CreateTimeLogDto actual, CreateTimeLogDto expected) {
        assertEquals(actual.getTimeSpent(), expected.getTimeSpent(), "Time spent value is different");
        assertEquals(actual.getProjectId(), expected.getProjectId(), "Project ID is different");
        assertEquals(actual.getDescription(), expected.getDescription(), "Description is different");
        assertEquals(actual.getDateCalendar(), expected.getDateCalendar(), "Date Calendar is different");
        assertEquals(actual.getCreatedByEmail(), expected.getCreatedByEmail(), "Created by email is different");
    }

    public static void verifyErrorMessage(MessageDto actual, String message, Boolean status) {
        assertEquals(actual.getMessage(), message);
        if (status.equals(true)) {
            assertTrue(actual.getSuccess());
        } else {
            assertFalse(actual.getSuccess());
        }
    }
}
