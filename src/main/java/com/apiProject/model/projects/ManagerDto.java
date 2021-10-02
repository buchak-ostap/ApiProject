package com.apiProject.model.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagerDto {

    private Integer id;
    private String title;
    private String fullName;
    private String email;
    private List<UserRolesDto> roles;
}
