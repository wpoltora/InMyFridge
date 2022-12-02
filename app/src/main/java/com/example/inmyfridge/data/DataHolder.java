package com.example.inmyfridge.data;

import android.graphics.Bitmap;

import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.models.FridgeItem;
import com.example.inmyfridge.models.ProductUnit;
import com.example.inmyfridge.models.Recipe;

import java.util.ArrayList;
import java.util.UUID;


/**Temporary database*/
public class DataHolder {
    public final ArrayList<Product> productList = new ArrayList<>();
    public final ArrayList<FridgeItem> fridgeItemList = new ArrayList<>();
    public final ArrayList<Recipe> recipeList = new ArrayList<>();
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
        productList.add(product1);
    }

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

    public FridgeItem getFridgeItemById(UUID id){
        for (FridgeItem arrayFridgeItem : fridgeItemList) {
            if (arrayFridgeItem.getProductUnit().getId() == id){
                return arrayFridgeItem;
            }
        }
        return null;
    }
}