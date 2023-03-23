package com.example.inmyfridge.recipes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmyfridge.data.repositories.RecipeRepository;
import com.example.inmyfridge.data.model.Recipe;

public class NewRecipeFragmentViewModel extends AndroidViewModel {
    RecipeRepository recipeRepository;

    public NewRecipeFragmentViewModel(@NonNull Application application){
        super(application);
        recipeRepository = new RecipeRepository(application);
    }

    public void insertRecipe(Recipe recipe){
        recipeRepository.insert(recipe);
    }
}
