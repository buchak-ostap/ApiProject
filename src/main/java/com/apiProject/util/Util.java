package com.apiProject.util;

import com.apiProject.exception.SystemException;
import com.apiProject.exception.code.ServiceErrorCode;
import com.apiProject.util.json.DatabaseEnumAliasIdentifiable;

public final class Util {

    public static <T extends DatabaseEnumAliasIdentifiable> T determineEnumValue(final Class<T> enumClass, final Integer dbValue){
        T result = null;
        if(dbValue!=null){
            for(final DatabaseEnumAliasIdentifiable enumInstance: enumClass.getEnumConstants()){
                if(dbValue.equals(enumInstance.toDbValue())){
                    result = (T) enumInstance;
                    break;
                }
            }
        }
        return result;
    }

    public static void assertNotNull(final Object object) {
        if (object == null) {
            throw new SystemException("Element can't be null", ServiceErrorCode.INVALID_ARGUMENT);
        }
    }
}
