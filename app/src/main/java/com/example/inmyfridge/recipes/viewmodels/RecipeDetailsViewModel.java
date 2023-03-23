package com.example.inmyfridge.recipes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.model.Product;

import java.util.UUID;

public class RecipeDetailsViewModel extends AndroidViewModel {
    ProductRepository productRepository;

    public RecipeDetailsViewModel(@NonNull Application application){
        super(application);
        this.productRepository = new ProductRepository(application);
    }

    public Product getProductByID(UUID id){
        return productRepository.getProductByID(id);
    }
}
