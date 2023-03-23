package com.example.inmyfridge.foods.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductUnitRepository;
import com.example.inmyfridge.data.model.ProductUnit;

public class NewProductUnitViewModel extends AndroidViewModel {
    private ProductUnitRepository productUnitRepository;
    public NewProductUnitViewModel(@NonNull Application application){
        super(application);
        productUnitRepository = new ProductUnitRepository(application);
    }

    public void insertProductUnit(ProductUnit productUnit){
        productUnitRepository.insert(productUnit);
    }
}
