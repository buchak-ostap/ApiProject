package com.apiProject.model.timeLog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayDtos {

    private Integer id;
    private LocalDate date;
    private int userID;
    private RequestDto request;
    private String dayType;
    private Boolean workingFromHome;
    private int timeSpent;
    private List<TimeLogListDto> timeLogDTOList;
    private String projectTitleDTOs;
}
