package com.example.inmyfridge.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.inmyfridge.data.model.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@Entity
public class DailyData {
    @PrimaryKey
    @NonNull
    private LocalDate date;
    private HashMap<UUID, Integer> meals;

    public DailyData(LocalDate date) {
        this.date = date;
        this.meals = new HashMap<>();
    }

    public void addMeal(Product product, int amount) {
        Integer oldValue = meals.get(product.getId());
        if (oldValue == null) {
            meals.put(product.getId(), amount);
        } else {
            meals.put(product.getId(), oldValue + amount);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<UUID, Integer> getMeals() {
        return meals;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    public void setMeals(HashMap<UUID, Integer> meals) {
        this.meals = meals;
    }
}
