package com.apiProject.model.contacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {

    private int id;
    private String firstname;
    private String lastname;
    private String birthDate;
    private String phoneOne;
    private String phoneTwo;
    private String skype;
    private String email;
    private String corporateEmail;
    private String level;
    private String position;
    private String startsWork;
    private String avatar;
    private String base64Avatar;
//    private String lastLogin;
    private String messages;
    private String allProjectManagers;
    private ProfileDto mentor;
    private ProfileDto mainManager;
    private ProfileDto myHR;
    private String teamLeadOfProjects;
    private String department;
    private String office;
    private String allDepartments;
    private List<LanguagesDto> languages;
    private String comment;
    private String location;
    private Boolean officeLead;
    private Boolean technicalLead;
    private Boolean topManager;
}
