package com.example.inmyfridge.recipes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.model.Product;

import java.util.List;

public class NewRecipeViewModel extends AndroidViewModel {
    ProductRepository productRepository;
    List<Product> allProducts;
    public NewRecipeViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        allProducts = productRepository.getAllProducts();
    }

    public List<Product> getAllProducts(){
        return allProducts;
    }
}
