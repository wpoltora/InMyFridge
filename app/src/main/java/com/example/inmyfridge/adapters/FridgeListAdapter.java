package com.example.inmyfridge.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inmyfridge.models.FridgeItem;
import com.example.inmyfridge.R;

import java.util.ArrayList;

/**Used for displaying fridge items list from database*/
public class FridgeListAdapter extends ArrayAdapter<FridgeItem> {
private final Activity context;
ArrayList<FridgeItem> fridgeItems;

public FridgeListAdapter(Activity context, ArrayList<FridgeItem> fridgeItems){
    super(context, R.layout.list_item_fridge, fridgeItems);
    this.context = context;
    this.fridgeItems = fridgeItems;
}

public View getView(int position, View view, ViewGroup parent){
    LayoutInflater inflater = context.getLayoutInflater();
    View rowView = inflater.inflate(R.layout.list_item_fridge, null, true);
    TextView nameText = (TextView) rowView.findViewById(R.id.list_item_fridge_name);
    TextView quantityText = (TextView) rowView.findViewById(R.id.quantity);
    nameText.setText(fridgeItems.get(position).getFoodItem().getName());
    //quantityText.setText(fridgeItems.get(position).getWeight().toString());

    return rowView;
}

}
