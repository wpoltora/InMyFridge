package com.example.inmyfridge.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inmyfridge.data.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Query("SELECT * FROM RECIPE")
    public List<Recipe> getAll();

    @Insert
    void insertAll(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Query("SELECT * FROM RECIPE WHERE ID = :id")
    Recipe getDataByID(int id);
}
