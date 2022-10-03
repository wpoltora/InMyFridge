package com.example.inmyfridge.models;

import com.example.inmyfridge.data.DataHolder;

import java.util.UUID;

public class FridgeItem {
    UUID productId;

    public FridgeItem(UUID productId) {
        this.productId = productId;
    }

    public Product getFoodItem(){
        return DataHolder.getInstance().getFoodItemById(productId);
    }

}
