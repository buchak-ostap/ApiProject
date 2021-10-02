package com.apiProject.model.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectFolderDto {

    private List<ProjectFoldersDto> projectFolders;
    private List<ProjectsDto> projects;
}
