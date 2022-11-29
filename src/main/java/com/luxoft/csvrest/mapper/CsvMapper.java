package com.luxoft.csvrest.mapper;

import com.luxoft.csvrest.data.entity.CsvRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvMapper {
    public static final String PRIMARY_KEY = "PRIMARY_KEY";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP";

    private static void mapValue(CsvRecord.CsvRecordBuilder builder,
                                                String template, String value ) {
        switch (template) {
            case PRIMARY_KEY -> builder.primaryKey(value);
            case NAME -> builder.name(value);
            case DESCRIPTION -> builder.description(value);
            case UPDATED_TIMESTAMP -> builder.updatedTimestamp(LocalDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
            default -> throw new IllegalArgumentException("Wrong name of column");
        }
    }

    public static CsvRecord createRecord(String template, String value) {
        String[] templates = template.split(";");
        String[] values = value.split(";");
        if (templates.length > values.length)
            throw new IllegalArgumentException("Wrong numbers of values");
        CsvRecord.CsvRecordBuilder builder = CsvRecord.builder();
        for(int i = 0; i < templates.length; i++) {
            mapValue(builder, templates[i], values[i]);
        }
        return builder.build();
    }

    private CsvMapper() {
    }
}
