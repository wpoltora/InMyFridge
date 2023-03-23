package com.example.inmyfridge.data;

import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.Recipe;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**Temporary database*/

public class DataHolder {
    public final ArrayList<Product> productList = new ArrayList<>();
    public final ArrayList<Recipe> recipeList = new ArrayList<>();
    public final ArrayList<Product> shoppingList = new ArrayList<>();
    public final HashMap<LocalDate, DailyData> dailyDataList = new HashMap<>();
    private static DataHolder instance;


    private DataHolder() {
        /**This is temporary*/

    }

    public static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }


    /**RETURNS A PRODUCT BY IT'S ID*/
    public Product getFoodItemById(UUID id){
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

}