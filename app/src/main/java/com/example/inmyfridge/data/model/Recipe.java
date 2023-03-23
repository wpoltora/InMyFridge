package com.example.inmyfridge.data.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setProducts(ArrayList<ProductRecipeAdapter> products) {
        this.products = products;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void addProductToList(UUID productID, int requiredAmount){
        products.add(new ProductRecipeAdapter(productID, requiredAmount));
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
