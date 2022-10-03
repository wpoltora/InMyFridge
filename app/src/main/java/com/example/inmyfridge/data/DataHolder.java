package com.example.inmyfridge.data;

import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.models.FridgeItem;

import java.util.ArrayList;
import java.util.UUID;


/**Temporary database*/
public class DataHolder {
    public final ArrayList<Product> productList = new ArrayList<>();
    public final ArrayList<FridgeItem> fridgeItemList = new ArrayList<>();

    private static DataHolder instance;

    private DataHolder() {}

    public static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    public Product getFoodItemById(UUID id){
        for (Product arrayProduct : productList) {
            if (arrayProduct.getId() == id){
                return arrayProduct;
            }
        }
        return null;
    }


}