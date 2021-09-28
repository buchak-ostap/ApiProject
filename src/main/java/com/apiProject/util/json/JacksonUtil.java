package com.apiProject.util.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.apiProject.dto.JsonEnumAliasIdentifiable;
import com.apiProject.exception.SystemException;
import com.apiProject.exception.code.ServiceErrorCode;
import com.apiProject.util.FinalizeWrapper;
import com.apiProject.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JacksonUtil {
    static final Logger LOGGER = Logger.getLogger(JacksonUtil.class.getName());
    private static final ObjectMapper objectMapper;
    private static final ObjectMapper objectMapperWithNaN;
    private static final ObjectMapper objectMapperWithTimestampDateFormat;
    private static final ObjectMapper objectMapperWithNonEmptyInclusions;
    private static final ObjectMapper objectMapperForEmptyBeans;
    private static final SimpleModule enumAlliasModule;
//    @SuppressWarnings("rawtypes")
//    private static final List<Class<? extends JsonEnumAliasIdentifiable>> enumClasses = Arrays.asList(UserProfileDataType.class, DistributionType.class, PortfolioFundType.class,
//            UserAssociationRole.class, Urgency.class, ProfileAnnouncementType.class, AccountAnnouncementType.class, TransactionRequestTransferType.class, UserAuditEventType.class,
//            UserProfileAction.class, PhnPasswordAction.class, TradingPasswordAuditAction.class);
//    static
    @SuppressWarnings("rawtypes")
    private static final List<Class<? extends JsonEnumAliasIdentifiable>> enumClasses = Arrays.asList();
    static {
        enumAlliasModule = new SimpleModule();
        objectMapperWithNonEmptyInclusions = new ObjectMapper();
        objectMapper = new ObjectMapper();
        objectMapperWithNaN = new ObjectMapper();
        objectMapperForEmptyBeans = new ObjectMapper();
        objectMapperWithTimestampDateFormat = new ObjectMapper();
        objectMapperWithTimestampDateFormat.registerModule(new JavaTimeModule());
        objectMapperWithTimestampDateFormat.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapperWithTimestampDateFormat.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapperWithTimestampDateFormat.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperWithTimestampDateFormat.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        objectMapperWithTimestampDateFormat.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        initAliasModule();
        initObjectMapper();
        initObjectMapperWithNaN();
        initObjectMapperWithNoEmpty();
        initObjectMapperForEmptyBeans();
        initObjectMapperWithTimestampDateFormat();
    }
    private JacksonUtil() {
    }
    private static void initAliasModule() {
        enumClasses.forEach(clazz -> {
            enumAlliasModule.addDeserializer(clazz, new JSONEnumDeserializer());
            enumAlliasModule.addSerializer(clazz, new JSONEnumSerializer());
        });
    }
    private static void initObjectMapperWithNoEmpty() {
        objectMapperWithNonEmptyInclusions.setSerializationInclusion(Include.NON_EMPTY);
        objectMapperWithNonEmptyInclusions.setSerializationInclusion(Include.NON_NULL);
        objectMapperWithNonEmptyInclusions.registerModule(enumAlliasModule);
    }
    private static void initObjectMapper() {
        objectMapper.registerModule(enumAlliasModule);
    }
    private static void initObjectMapperWithNaN() {
        objectMapperWithNaN.setSerializationInclusion(Include.NON_EMPTY);
        objectMapperWithNaN.setSerializationInclusion(Include.NON_NULL);
        objectMapperWithNaN.registerModule(enumAlliasModule);
        objectMapperWithNaN.enable(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS);
    }
    private static void initObjectMapperForEmptyBeans() {
        objectMapperForEmptyBeans.registerModule(enumAlliasModule);
        objectMapperForEmptyBeans.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    private static void initObjectMapperWithTimestampDateFormat() {
        objectMapperWithTimestampDateFormat.registerModule(new JavaTimeModule());
    }
    private static class JSONEnumDeserializer<E extends JsonEnumAliasIdentifiable<E>> extends JsonDeserializer<E> implements ContextualDeserializer {
        private Class<E> clazz;
        @SuppressWarnings("unchecked")
        @Override
        public JsonDeserializer<?> createContextual(final DeserializationContext ctxt, final BeanProperty property) throws JsonMappingException {
            clazz = (Class<E>) ctxt.getContextualType().getRawClass();
            return this;
        }
        @Override
        public E deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Util.determineEnumValue(clazz, p.getIntValue());
        }
    }
    private static class JSONEnumSerializer<E extends JsonEnumAliasIdentifiable<E>> extends JsonSerializer<JsonEnumAliasIdentifiable<E>> {
        @Override
        public void serialize(final JsonEnumAliasIdentifiable<E> value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
            jgen.writeObject(value.toDbValue());
        }
    }
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    public static ObjectMapper getObjectMapperWithNonEmptyInclusions() {
        return objectMapperWithNonEmptyInclusions;
    }
    public static ObjectMapper getObjectMapperForEmptyBeans() {
        return objectMapperForEmptyBeans;
    }
    public static String serialize(final Object object) {
        // if (object == null) {
        // return null;
        // }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Cannot serialize: ", e);
            throw new SystemException("Cannot serialize: ", ServiceErrorCode.GENERAL);
        }
    }
    public static <T> T deserializeWithNaN(final String string, final TypeReference<T> type) {
        try {
            return objectMapperWithNaN.readValue(string, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    public static <T> T deserialize(final String string, final TypeReference<T> type) {
        try {
            return objectMapper.readValue(string, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    public static <T> T deserialize(final String string, final Class<T> type) {
        try {
            return objectMapper.readValue(string, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    public static <T> T deserialize(final JsonNode node, final Class<T> type) {
        try {
            return objectMapper.treeToValue(node, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    public static <T> T deserialize(final ObjectNode node, final Class<T> type) {
        try {
            return objectMapper.treeToValue(node, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    /**
     * Used by integration tests for correct deserialize objects that contains date
     */
    public static <T> T deserializeWithDate(String json, final Class<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.readValue(json, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
    /**
     * Used by integration tests for correct deserialize objects that contains date
     */
    public static <T> T deserializeWithDate(String json, final TypeReference<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.readValue(json, type);
        } catch (final IOException e) {
            throw new SystemException("Cannot deserialize", ServiceErrorCode.GENERAL);
        }
    }
//    public static String serializeWithOutNullsAndEmptyObjects(final Object object) {
//        final ObjectNode sourceObject = objectMapperWithNonEmptyInclusions.valueToTree(object);
//        final ObjectNode resultObject = objectMapperWithNonEmptyInclusions.createObjectNode();
//        sourceObject.fields().forEachRemaining(field -> {
//            if (field.getValue() != null) {
//                final boolean empty = JacksonUtil.isEmptyNode(field.getValue(), objectMapperWithNonEmptyInclusions);
//                LOGGER.log(Level.FINER, "converter: " + field.getKey() + " : " + "is empty : " + empty);
//                LOGGER.log(Level.FINER, "converter: " + field.getValue().toString() + "\n ----------------");
//                if (!empty) {
//                    resultObject.set(field.getKey(), field.getValue());
//                }
//            }
//        });
//        try {
//            return objectMapperWithNonEmptyInclusions.writeValueAsString(resultObject);
//        } catch (final JsonProcessingException e) {
//            throw new SystemException(e, e, ServiceErrorCode.GENERAL);
//        }
//    }
    public static boolean isEmptyNode(final JsonNode node, final ObjectMapper objectMapper) {
        boolean result = true;
        if (node.isArray()) {
            result = JacksonUtil.isEmptyArrayField(node, objectMapper);
        } else if (node.isObject()) {
            result = JacksonUtil.isEmptyObjectField(node, objectMapper);
        } else if (node.isValueNode()) {
            result = node.isEmpty(objectMapper.getSerializerProvider());
        }
        return result;
    }
    public static boolean isEmptyObjectField(final JsonNode field, final ObjectMapper objectMapper) {
        final FinalizeWrapper<Integer> flag = new FinalizeWrapper<>(0);
        field.fields().forEachRemaining(node -> {
            if ((node.getValue() == null) || (node.getValue().isEmpty(objectMapper.getSerializerProvider()) || (isEmptyNode(node.getValue(), objectMapper)))) {
                JacksonUtil.increment(flag);
            } else {
                LOGGER.log(Level.FINE, "Increment not needed");
            }
        });
        return field.size() == flag.getValue();
    }
    public static boolean isEmptyArrayField(final JsonNode field, final ObjectMapper objectMapper) {
        final FinalizeWrapper<Integer> flag = new FinalizeWrapper<>(0);
        field.forEach(node -> {
            if ((node == null) || (node.isEmpty(objectMapper.getSerializerProvider()) || isEmptyNode(node, objectMapper))) {
                JacksonUtil.increment(flag);
            } else {
                LOGGER.log(Level.FINE, "Increment not needed");
            }
        });
        return field.size() == flag.getValue();
    }
    static void increment(final FinalizeWrapper<Integer> wrapper) {
        Util.assertNotNull(wrapper);
        Util.assertNotNull(wrapper.getValue());
        wrapper.setValue(wrapper.getValue() + 1);
    }
}