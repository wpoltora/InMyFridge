package com.example.inmyfridge.foods.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductUnitRepository;
import com.example.inmyfridge.data.model.ProductUnit;

import java.util.List;
import java.util.UUID;

public class ProductDetailsViewModel extends AndroidViewModel {
    private ProductUnitRepository productUnitRepository;
    public ProductDetailsViewModel(@NonNull Application application){
        super(application);
        productUnitRepository = new ProductUnitRepository(application);
    }

    public List<ProductUnit> getProductUnitsByProductID(UUID id){
        return productUnitRepository.getAllProductUnitsByProductID(id);
    }
}
