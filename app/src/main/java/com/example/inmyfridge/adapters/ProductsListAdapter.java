package com.example.inmyfridge.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.R;

import java.util.ArrayList;


/**Used for displaying food items list from database*/
public class ProductsListAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    ArrayList<Product> products;

    public ProductsListAdapter(Activity context, ArrayList<Product> products){
        super(context, R.layout.list_item_products, products);
        this.context = context;
        this.products = products;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_products, null, true);
        TextView nameText = rowView.findViewById(R.id.list_item_products_name);
        nameText.setText(products.get(position).getName());
        ImageView foodImage = rowView.findViewById(R.id.list_item_products_image);
        foodImage.setImageBitmap(products.get(position).getImage());
        return rowView;
    }

}