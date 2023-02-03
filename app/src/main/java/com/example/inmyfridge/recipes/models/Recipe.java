package com.example.inmyfridge.recipes.models;

import android.graphics.Bitmap;

import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String recipe;
    private ArrayList<ProductRecipeAdapter> products;
    private Bitmap image;

    public Recipe(String name, String recipe, Bitmap image, ArrayList<ProductRecipeAdapter> products){
        this.name = name;
        this.products = products;
        this.image = image;
        this.recipe = recipe;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void addProductToList(Product product, int requiredAmount){
        products.add(new ProductRecipeAdapter(product, requiredAmount));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ArrayList<ProductRecipeAdapter> getProducts() {
        return products;
    }

    public String getRecipe() {
        return recipe;
    }
}
