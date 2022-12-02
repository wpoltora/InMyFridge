package com.example.inmyfridge.adapters;

import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.models.FridgeItem;
import com.example.inmyfridge.models.Product;

import java.util.ArrayList;

public class ProductFridgeItemAdapter {
    Product product;
    ArrayList<FridgeItem> fridgeItems = new ArrayList<>();

    public ProductFridgeItemAdapter(Product product){
        this.product = product;
        for (FridgeItem fridgeItem:DataHolder.getInstance().fridgeItemList
             ) {
            if(fridgeItem.getProductUnit().getParentId() == product.getId()){
                fridgeItems.add(fridgeItem);
            }
        }
    }

    public ArrayList<FridgeItem> getFridgeItems() {
        return fridgeItems;
    }

    public int getCombinedWeight(){
        int weight = 0;
        for (FridgeItem fridgeItem:fridgeItems
             ) {
            weight += fridgeItem.getCount()*fridgeItem.getProductUnit().getWeight();
        }
        return weight;
    }
}
