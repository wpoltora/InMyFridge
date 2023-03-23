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

public class WeeklyViewModel extends AndroidViewModel {
    DailyDataRepository dailyDataRepository;
    ProductRepository productRepository;
    private LocalDate currentDate;
    private LocalDate displayedDate;

    public WeeklyViewModel(@NonNull Application application){
        super(application);
        this.dailyDataRepository = new DailyDataRepository(application);
        this.productRepository = new ProductRepository(application);
        currentDate = LocalDate.now();
        displayedDate = currentDate;
    }

    public DailyData getDailyDataByDate(LocalDate localDate){
        return dailyDataRepository.getDailyDataByDate(localDate);
    }

    public Product getProductByID(UUID id){
        return productRepository.getProductByID(id);
    }

    public void decreaseDateByWeeks(int weeks){
        displayedDate = displayedDate.minusWeeks(weeks);
    }

    public void increaseDateByWeeks(int weeks){
        displayedDate = displayedDate.plusWeeks(weeks);
    }

    public boolean displayedWeekIsCurrent(){
        return displayedDate.getDayOfMonth() == currentDate.getDayOfMonth() && displayedDate.getYear() == currentDate.getYear() && displayedDate.getMonth() == currentDate.getMonth();
    }

    public WeeklyMacros calculateMacrosForWeek(){
        double calories = 0;
        double protein = 0;
        double carbs = 0;
        double fats = 0;

        LocalDate tempDate = LocalDate.of(displayedDate.getYear(), displayedDate.getMonth(), displayedDate.getDayOfMonth());

        int entries = 0;
        while(tempDate.isAfter(displayedDate.minusWeeks(1))){
            DailyData dailyData = getDailyDataByDate(tempDate);
            if(dailyData != null) {
                entries++;
                HashMap<UUID, Integer> dailyValues = dailyData.getMeals();
                for (Map.Entry<UUID, Integer> entry : dailyValues.entrySet()) {
                    Product product = getProductByID(entry.getKey());
                    calories += (double) product.getKcal()/100 * entry.getValue();
                    protein += (double) product.getProtein()/100 * entry.getValue();
                    carbs += (double) product.getCarb()/100 * entry.getValue();
                    fats += (double) product.getFat()/100 * entry.getValue();
                }
            }
            tempDate = tempDate.minusDays(1);
        }

        return new WeeklyMacros(calories, protein, fats, carbs, entries);
    }

    public LocalDate getDisplayedDate() {
        return displayedDate;
    }

    public class WeeklyMacros{

        public WeeklyMacros(double totalCalories, double totalProtein, double totalFats, double totalCarbs, int totalDays) {
            this.totalCalories = totalCalories;
            this.totalProtein = totalProtein;
            this.totalFats = totalFats;
            this.totalCarbs = totalCarbs;
            this.totalDays = totalDays;
        }

        double totalCalories;
        double totalProtein;
        double totalFats;
        double totalCarbs;
        int totalDays;

        public double getAverageCalories(){
            return totalCalories/totalDays;
        }

        public double getProteinAverage(){
            return  totalProtein/totalDays;
        }

        public double getFatsAverage(){
            return totalFats/ totalDays;
        }

        public double getCarbAverage(){
            return totalCarbs/totalDays;
        }

        public double getProteinPercentage(){
            return totalProtein/(totalProtein + totalCarbs + totalFats)*100;
        }

        public double getFatsPercentage(){
            return totalFats/(totalProtein + totalCarbs + totalFats)*100;
        }

        public double getCarbsPercentage(){
            return totalCarbs/(totalProtein + totalCarbs + totalFats)*100;
        }

        public double getTotalCalories() {
            return totalCalories;
        }

        public double getTotalProtein() {
            return totalProtein;
        }

        public double getTotalFats() {
            return totalFats;
        }

        public double getTotalCarbs() {
            return totalCarbs;
        }

        public int getTotalDays() {
            return totalDays;
        }
    }
}
