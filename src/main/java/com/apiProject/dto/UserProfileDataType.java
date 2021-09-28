package com.apiProject.dto;

import com.apiProject.exception.SystemException;
import com.apiProject.exception.code.ServiceErrorCode;

import java.util.stream.Stream;

public enum UserProfileDataType implements JsonEnumAliasIdentifiable<UserProfileDataType> {

    FIRST_NAME(1), LAST_NAME(2), EMAIL(3);

    private Integer dbAlias;

    private UserProfileDataType(final Integer dbAlias) {
        this.dbAlias = dbAlias;
    }

    @Override
    public Integer toDbValue() {
        return dbAlias;
    }

    public static UserProfileDataType fromDbValue(final Integer alias) {
        return Stream.of(values()).filter(e -> e.toDbValue().equals(alias)).findAny()
                .orElseThrow(() -> new SystemException(String.format("Cannot get UserProfileDataType enum for alias %s", alias), ServiceErrorCode.INVALID_ARGUMENT));
    }

}