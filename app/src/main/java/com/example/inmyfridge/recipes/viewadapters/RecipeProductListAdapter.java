package com.example.inmyfridge.recipes.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.recipes.NewRecipeActivity;

import java.util.ArrayList;

public class RecipeProductListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> products;
    ArrayList<Product> selectedProducts = new ArrayList<>();

    public RecipeProductListAdapter (Activity context, ArrayList<Product> products){
        super(context, R.layout.list_item_new_recipe_products_select, products);
        this.context = context;
        this.products = products;
        selectedProducts = ((NewRecipeActivity)context).getSelectedProducts();
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_new_recipe_products_select, null, true);
        TextView nameText = rowView.findViewById(R.id.list_item_new_recipe_product_select_name);
        nameText.setText(products.get(position).getName());
        CheckBox checkBox = rowView.findViewById(R.id.list_item_new_recipe_product_select_checkbox);

        if(selectedProducts.contains(products.get(position))){
            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    selectedProducts.add(products.get(position));
                }
                else{
                    selectedProducts.remove(products.get(position));
                }
            }
        });
        return rowView;
    }



}
