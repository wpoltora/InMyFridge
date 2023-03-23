package com.example.inmyfridge.data.converters;

import androidx.room.TypeConverter;

import java.util.UUID;

public class UUIDConverter {

    @TypeConverter
    public static UUID fromString(String value) {
        return UUID.fromString(value);
    }

    @TypeConverter
    public static String uuidToString(UUID uuid) {
        return uuid.toString();
    }
}
