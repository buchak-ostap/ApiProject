package com.apiProject.model.timeLog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeLogMonth {

    private List<DayDtos> dayDTOS;
}
