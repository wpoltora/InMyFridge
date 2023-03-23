package com.example.inmyfridge.fridge.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.repositories.ProductUnitRepository;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.fridge.objectadapters.ProductProductUnitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FridgeViewModel extends AndroidViewModel {
    //FridgeItemRepository fridgeItemRepository;
    ProductRepository productRepository;
    ProductUnitRepository productUnitRepository;
    private List<Product> allProducts;
    //private List<FridgeItem> allFridgeItems;
    private List<ProductUnit> allProductUnits;

    public FridgeViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        //fridgeItemRepository = new FridgeItemRepository(application);
        productUnitRepository = new ProductUnitRepository(application);
        allProducts = productRepository.getAllProducts();
       // allFridgeItems = fridgeItemRepository.getAllFridgeItems();
        allProductUnits = productUnitRepository.getAllProductUnits();
    }

     public List<ProductProductUnitAdapter> getFridgeItemsWithProducts(){
         final List<ProductProductUnitAdapter> itemList = new ArrayList<>();
         for (Product product: allProducts) {
             ProductProductUnitAdapter adapter = new ProductProductUnitAdapter(product, allProductUnits);
             if(adapter.isVisible()) {
                 itemList.add(adapter);
             }
         }
         return itemList;
     }
    


    public ProductUnit getProductUnitById(UUID id){
        return productUnitRepository.getProductUnitByID(id);
    }

    public void update(ProductUnit productUnit){
        productUnitRepository.update(productUnit);
    }
}
