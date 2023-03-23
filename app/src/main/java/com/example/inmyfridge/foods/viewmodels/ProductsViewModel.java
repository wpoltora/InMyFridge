package com.example.inmyfridge.foods.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.DailyDataRepository;
import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.repositories.ProductUnitRepository;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProductsViewModel extends AndroidViewModel{
    private ProductRepository productRepository;
    private DailyDataRepository dailyDataRepository;
    private ProductUnitRepository productUnitRepository;
    private List<Product> allProducts;

    public ProductsViewModel(@NonNull Application application){
        super(application);
        productRepository = new ProductRepository(application);
        dailyDataRepository = new DailyDataRepository(application);
        productUnitRepository = new ProductUnitRepository(application);
        allProducts = productRepository.getAllProducts();
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void updateProduct(Product product){
        productRepository.update(product);
    }

    public void updateProductUnit(ProductUnit productUnit){
        productUnitRepository.update(productUnit);
    }

    public void updateDailyData(DailyData dailyData){
        dailyDataRepository.update(dailyData);
    }

    public DailyData getDailyDataByDate(LocalDate date){
        return dailyDataRepository.getDailyDataByDate(date);
    }

    public void insertDailyData(DailyData dailyData){
        dailyDataRepository.insert(dailyData);
    }

    public List<ProductUnit> getProductUnitsByProductID(UUID id){
        return productUnitRepository.getAllProductUnitsByProductID(id);
    }

}
