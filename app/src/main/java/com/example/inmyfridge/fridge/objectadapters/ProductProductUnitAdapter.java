package com.example.inmyfridge.fridge.objectadapters;

import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.data.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductProductUnitAdapter {
    Product product;
    ArrayList<ProductUnit> productUnits = new ArrayList<>();
    boolean visible = false;

    public ProductProductUnitAdapter(Product product, List<ProductUnit> productUnits){
        this.product = product;
        productUnits.stream()
               .filter(productUnit -> productUnit.getProductId().equals(product.getId()))
               .collect(Collectors.toCollection(() -> this.productUnits));
        visible = this.productUnits.stream().anyMatch(productUnit -> productUnit.getCount() > 0);
    }

    public ArrayList<ProductUnit> getProductUnits() {
        return productUnits;
    }

    public int getCombinedWeight(){
        int weight = 0;
        for (ProductUnit productUnit: productUnits) {
            //weight += fridgeItem.getCount()*MyApp.getInstance().getDatabase().productUnitDAO().getDataByID(fridgeItem.getProductUnitID()).getWeight();
            weight += productUnit.getCount()*productUnit.getWeight();
        }
        return weight;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isVisible(){
        return visible;
    }
}
