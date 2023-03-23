package com.example.inmyfridge.data.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.UUID;

public class MealMapConverter {
    @TypeConverter
    public String fromList(HashMap<UUID, Integer> meals) {
        if (meals == null) {
            return (null);
        }
        Gson gson = new Gson();
        String json = gson.toJson(meals);
        return json;
    }

    @TypeConverter
    public HashMap<UUID, Integer> toList(String productString) {
        if (productString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type mapType = new TypeToken<HashMap<UUID, Integer>>() {
        }.getType();
        HashMap<UUID, Integer> meals = gson.fromJson(productString, mapType);
        return meals;
    }
}
