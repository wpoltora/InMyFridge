package com.example.inmyfridge.data;

import android.graphics.Bitmap;

import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.fridge.models.FridgeItem;
import com.example.inmyfridge.foods.models.ProductUnit;
import com.example.inmyfridge.recipes.models.Recipe;

import java.util.ArrayList;
import java.util.UUID;

/**Temporary database*/
public class DataHolder {
    public final ArrayList<Product> productList = new ArrayList<>();
    public final ArrayList<FridgeItem> fridgeItemList = new ArrayList<>();
    public final ArrayList<Recipe> recipeList = new ArrayList<>();
    public final ArrayList<Product> shoppingList = new ArrayList<>();
    private static DataHolder instance;

    private DataHolder() {
        /**This is temporary*/
        Product product1 = new Product.ProductBuilder("Potato")
                .carb(12)
                .fat(14)
                .kcal(70)
                .protein(5)
                .image(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .build();
        ProductUnit productUnit1 = new ProductUnit.ProductUnitBuilder().weight(50)
                .isLoose(false)
                .image(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .barcode(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .parentId(product1.getId())
                .build();
        product1.getFoodUnitItems().add(productUnit1);

        Product product2 = new Product.ProductBuilder("Dog")
                .carb(12)
                .fat(14)
                .kcal(70)
                .protein(5)
                .image(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .build();
        ProductUnit productUnit2 = new ProductUnit.ProductUnitBuilder().weight(50)
                .isLoose(false)
                .image(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .barcode(Bitmap.createBitmap(10,10, Bitmap.Config.ARGB_8888))
                .parentId(product1.getId())
                .build();

        productList.add(product1);
        productList.add(product2);
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


    /**RETURNS A FRIDGE ITEM BASED ON THE ID OF A PRODUCT UNIT IT CONTAINS*/
    public FridgeItem getFridgeItemByUnitId(UUID id){
        return fridgeItemList.stream()
                .filter(fridgeItem -> fridgeItem.getProductUnit().getId() == id)
                .findFirst()
                .orElse(null);
    }
}