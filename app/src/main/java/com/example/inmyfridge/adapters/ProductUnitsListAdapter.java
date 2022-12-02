package com.example.inmyfridge.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.models.ProductUnit;
import com.example.inmyfridge.R;

import java.util.ArrayList;

/**Used for displaying food unit list from database for each food product*/
public class ProductUnitsListAdapter extends ArrayAdapter<ProductUnit> {
    private final Activity context;
    ArrayList<ProductUnit> productUnits;

    public ProductUnitsListAdapter(Activity context, ArrayList<ProductUnit> productUnits){
        super(context, R.layout.list_item_product_units, productUnits);
        this.context = context;
        this.productUnits = productUnits;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_product_units, null, true);
        boolean isLoose = productUnits.get(position).isLoose();
        TextView weightText =  rowView.findViewById(R.id.list_item_product_units_weight);
        if (isLoose){
            weightText.setText("Loose");
        }
        else{
            weightText.setText(Integer.toString(productUnits.get(position).getWeight()));
        }

        ImageView foodUnitImage = rowView.findViewById(R.id.list_item_product_units_image);
        foodUnitImage.setImageBitmap(productUnits.get(position).getImage());
        return rowView;
    }
}
