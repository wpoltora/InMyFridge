package com.example.inmyfridge.tracking.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.DailyDataRepository;
import com.example.inmyfridge.data.repositories.ProductRepository;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class DailyViewModel extends AndroidViewModel {
    DailyDataRepository dailyDataRepository;
    ProductRepository productRepository;

    private LocalDate currentDate;
    private LocalDate displayedDate;

    private double calories = 0;
    private double protein = 0;
    private double carbs = 0;
    private double fats = 0;

    public DailyViewModel(@NonNull Application application){
        super(application);
        this.dailyDataRepository = new DailyDataRepository(application);
        this.productRepository = new ProductRepository(application);
        currentDate = LocalDate.now();
        displayedDate = currentDate;
        setMacros();
    }

    public DailyData getDailyDataByDate(LocalDate localDate){
        return dailyDataRepository.getDailyDataByDate(localDate);
    }

    public Product getProductByID(UUID id){
        return productRepository.getProductByID(id);
    }

    public void decreaseDisplayedDays(int days){
        displayedDate = displayedDate.minusDays(days);
        setMacros();
    }

    public void increaseDisplayedDays(int days){
        displayedDate = displayedDate.plusDays(days);
        setMacros();
    }

    public boolean displayedDateIsToday(){
        return displayedDate.getDayOfMonth() == currentDate.getDayOfMonth() && displayedDate.getYear() == currentDate.getYear();
    }

    private void setMacros(){
        calories = 0;
        protein = 0;
        carbs = 0;
        fats = 0;
        DailyData dailyData = getDailyDataByDate(displayedDate);
        if(dailyData != null) {
            HashMap<UUID, Integer> dailyValues = dailyData.getMeals();
            for (Map.Entry<UUID, Integer> entry : dailyValues.entrySet()) {
                Product product = getProductByID(entry.getKey());
                calories += (double) product.getKcal()/100 * entry.getValue();
                protein += (double) product.getProtein()/100 * entry.getValue();
                carbs += (double) product.getCarb()/100 * entry.getValue();
                fats += (double) product.getFat()/100 * entry.getValue();
            }
        }
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public LocalDate getDisplayedDate() {
        return displayedDate;
    }
}
