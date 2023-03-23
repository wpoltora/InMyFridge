package com.example.inmyfridge.foods.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.repositories.ProductUnitRepository;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;

public class NewProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    private ProductUnitRepository productUnitRepository;

    public NewProductViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        productUnitRepository = new ProductUnitRepository(application);
    }

    public void insertProduct(Product product){
        productRepository.insert(product);
    }

    public void insertProductUnit(ProductUnit productUnit){
        productUnitRepository.insert(productUnit);
    }


}
