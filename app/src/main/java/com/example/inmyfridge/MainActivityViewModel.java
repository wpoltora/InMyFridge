package com.example.inmyfridge;

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

public class MainActivityViewModel extends AndroidViewModel {
    ProductRepository productRepository;
    ProductUnitRepository productUnitRepository;
    private List<Product> allProducts;
    private List<ProductUnit> allProductUnits;


    public MainActivityViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        productUnitRepository = new ProductUnitRepository(application);
        allProducts = productRepository.getAllProducts();
    }

    public List<ProductProductUnitAdapter> getFridgeItemsWithProducts(){
        final List<ProductProductUnitAdapter> itemList = new ArrayList<>();
        for (Product product: allProducts) {
            ProductProductUnitAdapter adapter = new ProductProductUnitAdapter(product, allProductUnits);
            if(!adapter.getProductUnits().isEmpty()) {
                itemList.add(adapter);
            }
        }
        return itemList;
    }

    public void updateProductUnit(ProductUnit productUnit){
        productUnitRepository.update(productUnit);
    }

    public ProductUnit getProductUnitByBarcode(String barcode){
        return productUnitRepository.getProductUnitByBarcode(barcode);
    }

    public Product getProductById(UUID id){
        return productRepository.getProductByID(id);
    }
}
