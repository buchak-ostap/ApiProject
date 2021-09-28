package com.apiProject.dto;

import com.apiProject.util.json.DatabaseEnumAliasIdentifiable;

public interface JsonEnumAliasIdentifiable<T> extends DatabaseEnumAliasIdentifiable {

    @Override
    Integer toDbValue();
}
