package com.example.inmyfridge.adapters;

import com.example.inmyfridge.models.Product;

import java.util.UUID;

public class ProductToRecipeAdapter {
    UUID id;
    int requiredAmount;
    public ProductToRecipeAdapter(Product product, int requiredAmount){
        this.id = product.getId();
        this.requiredAmount = requiredAmount;
    }
}
