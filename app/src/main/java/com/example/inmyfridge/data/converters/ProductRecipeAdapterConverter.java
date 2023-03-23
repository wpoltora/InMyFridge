package com.example.inmyfridge.data.converters;

import androidx.room.TypeConverter;

import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductRecipeAdapterConverter {
    @TypeConverter
    public static ArrayList<ProductRecipeAdapter> fromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductRecipeAdapter>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String toJson(List<ProductRecipeAdapter> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductRecipeAdapter>>() {}.getType();
        return gson.toJson(list, type);
    }
}
