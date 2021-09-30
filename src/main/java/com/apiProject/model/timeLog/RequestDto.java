package com.apiProject.model.timeLog;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDto {

    private int id;
    private int userId;
    private String dayType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int targetUserId;
    private String description;
    private String username;
    private LocalDate requestTime;
}
