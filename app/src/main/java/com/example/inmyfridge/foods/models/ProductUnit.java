package com.example.inmyfridge.foods.models;

import android.graphics.Bitmap;

import java.util.UUID;

public class ProductUnit {
    private int weight;
    private Bitmap image;
    private Bitmap barcode;
    //store value after checking if the weight of a product is set or it's loose - for example weighed vegetables
    private boolean isLoose;
    private UUID id;
    private UUID parentId;

    public ProductUnit(int weight, Bitmap image, Bitmap barcode, boolean isLoose, UUID parentId) {
        this.id = UUID.randomUUID();
        this.weight = weight;
        this.image = image;
        this.barcode = barcode;
        this.isLoose = isLoose;
        this.parentId = parentId;
    }

    public ProductUnit(ProductUnitBuilder builder){
        this.id = UUID.randomUUID();
        this.weight = builder.weight;
        this.image = builder.image;
        this.barcode = builder.barcode;
        this.isLoose = builder.isLoose;
        this.parentId = builder.parentId;
    }

    public int getWeight() {
        return weight;
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

    public Bitmap getBarcode() {
        return barcode;
    }

    public void setBarcode(Bitmap barcode) {
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

    public UUID getParentId(){return this.parentId;}

    /**BUILDER*/
    public static class ProductUnitBuilder{
        private int weight;
        private Bitmap image;
        private Bitmap barcode;
        private boolean isLoose;
        private UUID parentId;

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
        public ProductUnitBuilder barcode(Bitmap barcode){
            this.barcode = barcode;
            return this;
        }
        public ProductUnitBuilder isLoose(boolean isLoose){
            this.isLoose = isLoose;
            return this;
        }

        public ProductUnitBuilder parentId(UUID parentId){
            this.parentId = parentId;
            return this;
        }

        public ProductUnit build(){
            ProductUnit productUnit = new ProductUnit(this);
            return productUnit;
        }

    }
    
}
