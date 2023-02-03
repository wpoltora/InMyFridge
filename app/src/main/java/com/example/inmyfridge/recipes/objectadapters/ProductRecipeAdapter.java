package com.example.inmyfridge.recipes.objectadapters;

import com.example.inmyfridge.foods.models.Product;

import java.util.UUID;

public class ProductRecipeAdapter {
    Product product;
    int requiredAmount;
    String type;
    private enum type{
        GRAMS, UNITS
    }

    public ProductRecipeAdapter(Product product, int requiredAmount){
        this.product = product;
        this.requiredAmount = requiredAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}

