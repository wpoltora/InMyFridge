package com.example.inmyfridge.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.inmyfridge.data.converters.BitmapConverter;
import com.example.inmyfridge.data.converters.LocalDateConverter;
import com.example.inmyfridge.data.converters.MealMapConverter;
import com.example.inmyfridge.data.converters.ProductRecipeAdapterConverter;
import com.example.inmyfridge.data.converters.ProductUnitsListConverter;
import com.example.inmyfridge.data.converters.UUIDConverter;
import com.example.inmyfridge.data.dao.DailyDataDAO;
import com.example.inmyfridge.data.dao.ProductDAO;
import com.example.inmyfridge.data.dao.ProductUnitDAO;
import com.example.inmyfridge.data.dao.RecipeDAO;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.data.model.Recipe;
import com.example.inmyfridge.data.model.DailyData;

@Database(entities = {Product.class, ProductUnit.class, DailyData.class, Recipe.class}, version = 1 )
@TypeConverters({UUIDConverter.class, BitmapConverter.class, ProductUnitsListConverter.class, LocalDateConverter.class, MealMapConverter.class, ProductRecipeAdapterConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract ProductDAO productDao();
    public abstract ProductUnitDAO productUnitDAO();
    public abstract DailyDataDAO dailyDataDAO();
    public abstract RecipeDAO recipeDAO();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database.db").allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
