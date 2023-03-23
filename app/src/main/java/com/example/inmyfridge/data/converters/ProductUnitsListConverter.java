package com.example.inmyfridge.data.converters;

import androidx.room.TypeConverter;

import com.example.inmyfridge.data.model.ProductUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductUnitsListConverter {

    @TypeConverter
    public String fromList(ArrayList<ProductUnit> productUnits) {
        if (productUnits == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductUnit>>() {}.getType();
        String json = gson.toJson(productUnits, type);
        return json;
    }

    @TypeConverter
    public ArrayList<ProductUnit> toList(String productString) {
        if (productString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ProductUnit>>() {}.getType();
        ArrayList<ProductUnit> productUnits = gson.fromJson(productString, type);
        return productUnits;
    }


}