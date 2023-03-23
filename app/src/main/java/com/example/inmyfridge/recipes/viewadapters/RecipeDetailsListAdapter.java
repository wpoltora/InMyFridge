package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.recipes.objectadapters.ProductRecipeAdapter;

import java.util.ArrayList;
import java.util.UUID;

public class RecipeDetailsListAdapter extends ArrayAdapter<ProductRecipeAdapter> {

    private final Activity context;
    ArrayList<ProductRecipeAdapter> products;
    private ItemCallback callback;

    public RecipeDetailsListAdapter (Activity context, ArrayList<ProductRecipeAdapter> products, ItemCallback callback){
        super(context, R.layout.list_item_recipe_details, products);
        this.context = context;
        this.products = products;
        this.callback = callback;
    }

    public View getView(int position, View view, ViewGroup parent){
        ProductRecipeAdapter adapter = products.get(position);
        Product product = callback.getProductByID(adapter.getProductID());
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_recipe_details, null, true);
        TextView nameText = rowView.findViewById(R.id.list_item_recipe_products_name);
        nameText.setText(product.getName());
        ImageView foodImage = rowView.findViewById(R.id.list_item_recipe_products_image);
        foodImage.setImageBitmap(product.getImage());
        TextView amountText = rowView.findViewById(R.id.list_item_recipe_products_amount);
        amountText.setText(adapter.getRequiredAmount() + " " + adapter.getType());

        return rowView;
    }

    public interface ItemCallback{
        Product getProductByID(UUID id);
    }

}
