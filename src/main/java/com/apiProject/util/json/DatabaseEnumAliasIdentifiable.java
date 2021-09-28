package com.apiProject.util.json;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface DatabaseEnumAliasIdentifiable {

    Integer toDbValue();

    /**
     * Util method that converts a collection of {{@link DatabaseEnumAliasIdentifiable}} to a list of integers
     * @param collection non-null collection that we need to convert
     * @return a list of integer which are mapped to underlying enumerated values
     */
    static List<Integer> toDbValues(Collection<? extends DatabaseEnumAliasIdentifiable> collection){
        return collection.stream()
                .map(DatabaseEnumAliasIdentifiable::toDbValue)
                .collect(Collectors.toList());
    }

}
