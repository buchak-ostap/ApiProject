package com.apiProject.util;

import com.apiProject.model.timeLog.DayDtos;
import com.apiProject.model.timeLog.TimeLogListDto;

import java.util.List;

import static com.apiProject.util.DateConstants.getLocalDate;

public class TimeLogUtils {

    public static TimeLogListDto getTimeLog(List<DayDtos> response, int timeLogId) {
        DayDtos day = response.stream().filter(d -> d.getDate().equals(getLocalDate())).findFirst().orElse(null);
        assert day != null;
        return getTimeLogsFromList(day.getTimeLogDTOList(), timeLogId);
    }

    public static TimeLogListDto getTimeLogsFromList(List<TimeLogListDto> timeLog, int timeLogId) {
        return timeLog.stream().filter(t -> t.getId().equals(timeLogId)).findFirst().orElse(null);

    }
}
