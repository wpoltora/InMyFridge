package com.example.inmyfridge.data.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class ProductUnit {
    @NonNull
    @PrimaryKey
    private UUID id;
    private UUID productId;
    private int weight;
    @Nullable
    private Bitmap image;
    @Nullable
    private String barcode;
    //store value after checking if the weight of a product is set or it's loose - for example weighed vegetables
    private boolean isLoose;
    int count = 0;

    public ProductUnit(int weight, Bitmap image, String barcode, boolean isLoose, UUID productId) {
        this.id = UUID.randomUUID();
        this.weight = weight;
        this.image = image;
        this.barcode = barcode;
        this.isLoose = isLoose;
        this.productId = productId;
    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public void decreaseCount(int count){this.count -= count;}

    public int getCount(){
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductUnit(ProductUnitBuilder builder){
        this.id = UUID.randomUUID();
        this.weight = builder.weight;
        this.image = builder.image;
        this.barcode = builder.barcode;
        this.isLoose = builder.isLoose;
        this.productId = builder.productId;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setParentId(UUID parentId) {
        this.productId = parentId;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isLoose() {
        return isLoose;
    }

    public void setLoose(boolean loose) {
        isLoose = loose;
    }

    public UUID getId(){
        return this.id;
    }


    public UUID getProductId(){return this.productId;}

    /**BUILDER*/
    public static class ProductUnitBuilder{
        private int weight;
        private Bitmap image;
        private String barcode;
        private boolean isLoose;
        private UUID productId;

        public ProductUnitBuilder() {
        }

        public ProductUnitBuilder weight(int weight){
            this.weight = weight;
            return this;
        }

        public ProductUnitBuilder image(Bitmap image){
            this.image = image;
            return this;
        }
        public ProductUnitBuilder barcode(String barcode){
            this.barcode = barcode;
            return this;
        }
        public ProductUnitBuilder isLoose(boolean isLoose){
            this.isLoose = isLoose;
            return this;
        }

        public ProductUnitBuilder parentId(UUID parentId){
            this.productId = parentId;
            return this;
        }

        public ProductUnit build(){
            ProductUnit productUnit = new ProductUnit(this);
            return productUnit;
        }

    }
    
}
