package com.example.inmyfridge.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.data.dao.RecipeDAO;
import com.example.inmyfridge.data.model.Recipe;

import java.util.List;

public class RecipeRepository {
    private RecipeDAO recipeDAO;
    private List<Recipe> allRecipes;

    public RecipeRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        recipeDAO = database.recipeDAO();
        allRecipes = recipeDAO.getAll();
    }

    public List<Recipe> getAllRecipes(){
        return allRecipes;
    }

    public void insert(Recipe recipe){
        new RecipeRepository.InsertRecipeAsyncTask(recipeDAO).execute(recipe);
    }

    public void delete(Recipe recipe){
        new RecipeRepository.DeleteRecipeAsyncTask(recipeDAO).execute(recipe);
    }

    public void update(Recipe recipe){
        new RecipeRepository.UpdateRecipeAsyncTask(recipeDAO).execute(recipe);
    }

    public Recipe getRecipeByID(int id){
        return allRecipes.stream().filter(recipe -> recipe.getId() == id).findFirst().get();
    }

    private static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDAO recipeDAO;

        private InsertRecipeAsyncTask(RecipeDAO recipeDAO){
            this.recipeDAO = recipeDAO;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.insertAll(recipes);
            return null;
        }
    }

    private static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void>{
        private RecipeDAO recipeDAO;

        private DeleteRecipeAsyncTask(RecipeDAO recipeDAO){
            this.recipeDAO = recipeDAO;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.delete(recipes[0]);
            return null;
        }
    }

    private static class UpdateRecipeAsyncTask extends AsyncTask<Recipe, Void, Void>{
        private RecipeDAO recipeDAO;

        private UpdateRecipeAsyncTask(RecipeDAO recipeDAO){
            this.recipeDAO = recipeDAO;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDAO.updateRecipe(recipes[0]);
            return null;
        }
    }


}
