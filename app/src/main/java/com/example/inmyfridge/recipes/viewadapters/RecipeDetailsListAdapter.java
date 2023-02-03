package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.foods.models.Product;
import com.example.inmyfridge.recipes.models.Recipe;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;

public class RecipeDetailsListAdapter extends ArrayAdapter<ProductRecipeAdapter> {

    private final Activity context;
    ArrayList<ProductRecipeAdapter> products;

    public RecipeDetailsListAdapter (Activity context, ArrayList<ProductRecipeAdapter> products){
        super(context, R.layout.list_item_recipe_details, products);
        this.context = context;
        this.products = products;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_recipe_details, null, true);
        TextView nameText = rowView.findViewById(R.id.list_item_recipe_products_name);
        nameText.setText(products.get(position).getProduct().getName());
        ImageView foodImage = rowView.findViewById(R.id.list_item_recipe_products_image);
        foodImage.setImageBitmap(products.get(position).getProduct().getImage());
        TextView amountText = rowView.findViewById(R.id.list_item_recipe_products_amount);
        amountText.setText(Integer.toString(products.get(position).getRequiredAmount()));

        return rowView;
    }

}
