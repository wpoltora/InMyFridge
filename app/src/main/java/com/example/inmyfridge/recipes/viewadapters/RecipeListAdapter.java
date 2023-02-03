package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.recipes.models.Recipe;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {
    private final Activity context;
    ArrayList<Recipe> recipes;

    public RecipeListAdapter (Activity context, ArrayList<Recipe> recipes){
        super(context, R.layout.list_item_recipes, recipes);
        this.context = context;
        this.recipes = recipes;
    }

    public View getView(int position, View view, ViewGroup parent){
        Recipe recipe = recipes.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_recipes, null, true);

        ImageView image = rowView.findViewById(R.id.list_item_recipe_image);
        image.setImageBitmap(recipe.getImage());

        TextView name = rowView.findViewById(R.id.list_item_recipe_name);
        name.setText(recipe.getName());

        return rowView;
    }
}
