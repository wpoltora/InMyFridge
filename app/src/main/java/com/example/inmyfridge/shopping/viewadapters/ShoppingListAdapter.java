package com.example.inmyfridge.shopping.viewadapters;

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

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> shoppingList;

    public ShoppingListAdapter(Activity context, ArrayList<Product> products){
        super(context, R.layout.list_item_shopping, products);
        this.context = context;
        this.shoppingList = products;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_shopping, null, true);
        TextView nameText = rowView.findViewById(R.id.list_item_shopping_name);
        nameText.setText(shoppingList.get(position).getName());
        ImageView foodImage = rowView.findViewById(R.id.list_item_shopping_image);
        foodImage.setImageBitmap(shoppingList.get(position).getImage());
        ImageButton removeButton = rowView.findViewById(R.id.list_item_shopping_remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingList.remove(position);
                notifyDataSetChanged();
            }
        });
        return rowView;
    }

}