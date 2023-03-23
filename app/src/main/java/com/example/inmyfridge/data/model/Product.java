package com.example.inmyfridge.data.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Product {
    @NonNull
    @PrimaryKey
    private UUID id;
    private String name;
    private int kcal;
    private int protein;
    private int carb;
    private int fat;
    private Bitmap image;
    private boolean visible;

    public Product(String name, int kcal, int protein, int carb, int fat, Bitmap image) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
        this.image = image;
        this.visible = true;
    }

    private Product(ProductBuilder builder){
        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.kcal = builder.kcal;
        this.protein = builder.protein;
        this.carb = builder.carb;
        this.fat = builder.fat;
        this.image = builder.image;
        this.visible = true;
    }

    public String getName() {
        return name;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setProductUnits(ArrayList<ProductUnit> productUnits) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarb() {
        return carb;
    }

    public int getFat() {
        return fat;
    }

    @NonNull
    public UUID getId(){
        return this.id;
    }


    public Bitmap getImage(){return image;}
    /**---------------------------------------------------------*/


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**BUILDER*/
    public static class ProductBuilder{
        private String name;
        private int kcal;
        private int protein;
        private int carb;
        private int fat;
        private Bitmap image;

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder kcal(int kcal){
            this.kcal = kcal;
            return this;
        }

        public ProductBuilder protein(int protein){
            this.protein = protein;
            return this;
        }
        public ProductBuilder carb(int carb){
            this.carb = carb;
            return this;
        }
        public ProductBuilder fat(int fat){
            this.fat = fat;
            return this;
        }

        public ProductBuilder image(Bitmap image){
            this.image = image;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}
