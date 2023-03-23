package com.example.inmyfridge.recipes.objectadapters;

import java.util.UUID;

public class ProductRecipeAdapter {
    UUID productID;
    int requiredAmount;
    String type;

    public ProductRecipeAdapter(UUID productID, int requiredAmount){
        this.productID = productID;
        this.requiredAmount = requiredAmount;
    }

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

