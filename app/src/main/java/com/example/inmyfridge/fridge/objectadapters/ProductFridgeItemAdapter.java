package com.example.inmyfridge.fridge.objectadapters;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.fridge.models.FridgeItem;
import com.example.inmyfridge.foods.models.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductFridgeItemAdapter {
    Product product;
    ArrayList<FridgeItem> fridgeItems = new ArrayList<>();

    public ProductFridgeItemAdapter(Product product){
        this.product = product;

        DataHolder.getInstance().fridgeItemList.stream()
               .filter(fridgeItem -> fridgeItem.getProduct().equals(product))
               .collect(Collectors.toCollection(() -> fridgeItems));
    }

    public ArrayList<FridgeItem> getFridgeItems() {
        return fridgeItems;
    }

    public int getCombinedWeight(){
        int weight = 0;
        for (FridgeItem fridgeItem:fridgeItems) {
            weight += fridgeItem.getCount()*fridgeItem.getProductUnit().getWeight();
        }
        return weight;
    }

    public Product getProduct() {
        return product;
    }
}
