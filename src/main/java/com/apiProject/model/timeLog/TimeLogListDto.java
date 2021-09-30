package com.apiProject.model.timeLog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeLogListDto {

    private Integer id;
    private String projectName;
    private int projectId;
    private String description;
    private String createdByEmail;
    private int timeSpent;
    private LocalDate dateCreate;
    private String zoneOffset;
    private LocalDate dateCalendar;
    private String timeLogType;
    private Boolean needApprove;
}
