package com.example.inmyfridge.models;

import com.example.inmyfridge.data.DataHolder;

import java.util.UUID;

public class FridgeItem {
    ProductUnit productUnit;
    int count;
    public FridgeItem(ProductUnit productUnit) {
        this.productUnit = productUnit;
        this.count = 1;
    }
    public ProductUnit getProductUnit(){
        return this.productUnit;
    }

    public void increaseCount() {
        this.count +=1;
    }

    public int getCount(){
        return this.count;
    }
}
