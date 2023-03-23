package com.example.inmyfridge.recipes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.RecipeRepository;
import com.example.inmyfridge.data.model.Recipe;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    RecipeRepository recipeRepository;

    public RecipeViewModel(@NonNull Application application){
        super(application);
        recipeRepository = new RecipeRepository(application);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.getAllRecipes();
    }
}
