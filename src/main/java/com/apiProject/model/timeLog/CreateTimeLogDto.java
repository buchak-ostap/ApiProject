package com.apiProject.model.timeLog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTimeLogDto {

    private int id;
    private int timeSpent;
    private int minutes;
    private int hours;
    private String projectName;
    private int projectId;
    private String description;
    private int inSeconds;
    private LocalDate dateCalendar;
    private int zoneOffset;
    private String createdByEmail;
    private String timeLogType;
    private LocalDate dateCreate;
    private Boolean needApprove;
}
