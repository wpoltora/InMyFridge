package com.example.inmyfridge;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.data.model.Product;

import java.util.List;

public class MyApp extends Application {
    private AppDatabase db;
    LiveData<List<Product>> products;

    private static MyApp singleton;

    public static MyApp getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
       // db = Room.databaseBuilder(getApplicationContext(),
       //         AppDatabase.class, "database.db").allowMainThreadQueries()
      //          .build();
    }

    public AppDatabase getDatabase() {
        return db;
    }

}