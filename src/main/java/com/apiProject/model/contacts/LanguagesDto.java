package com.apiProject.model.contacts;

import lombok.Data;

@Data
public class LanguagesDto {

    private int id;
    private int userId;
    private LanguageDto language;
    private LevelDto level;
    private LevelDto nextLevel;
    private String lastReview;
}
