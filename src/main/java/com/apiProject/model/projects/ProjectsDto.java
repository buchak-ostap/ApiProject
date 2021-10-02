package com.apiProject.model.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectsDto {

    private Integer id;
    private String title;
    private ManagerDto manager;
}
