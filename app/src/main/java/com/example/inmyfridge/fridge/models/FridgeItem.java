package com.example.inmyfridge.fridge.models;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.foods.models.ProductUnit;

public class FridgeItem {
    Product product;
    ProductUnit productUnit;
    int count;

    public FridgeItem(ProductUnit productUnit) {
        this.productUnit = productUnit;
        this.count = 1;
        this.product = DataHolder.getInstance().getFoodItemById(productUnit.getParentId());
    }
    public ProductUnit getProductUnit(){
        return this.productUnit;
    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public int getCount(){
        return this.count;
    }

    public Product getProduct() {
        return product;
    }
}
