package com.example.inmyfridge.models;

import android.graphics.Bitmap;

import com.example.inmyfridge.adapters.ProductToRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private List<ProductToRecipeAdapter> products;
    private Bitmap image;

    public Recipe(String name, Bitmap image, ArrayList<ProductToRecipeAdapter> products){
        this.name = name;
        this.products = products;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void addProductToList(Product product, int requiredAmount){
        products.add(new ProductToRecipeAdapter(product, requiredAmount));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
