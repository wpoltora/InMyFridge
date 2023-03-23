package com.example.inmyfridge.foods.viewadapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.R;

import java.util.ArrayList;

/**Used for displaying food unit list from database for each food product*/
public class ProductUnitListAdapter extends ArrayAdapter<ProductUnit> {
    private final Activity context;
    ArrayList<ProductUnit> productUnits;

    public ProductUnitListAdapter(Activity context, ArrayList<ProductUnit> productUnits){
        super(context, R.layout.list_item_product_units, productUnits);
        this.context = context;
        this.productUnits = productUnits;
    }

    public View getView(int position, View view, ViewGroup parent){
        ProductUnit productUnit = productUnits.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_product_units, null, true);
        boolean isLoose = productUnit.isLoose();
        TextView weightText =  rowView.findViewById(R.id.list_item_product_units_weight);
        Button barcodeButton = rowView.findViewById(R.id.list_item_product_units_barcode);
        ImageButton removeButton = rowView.findViewById(R.id.list_item_product_units_remove);

        if (isLoose){
            weightText.setText("Loose");
            barcodeButton.setVisibility(View.GONE);
            removeButton.setVisibility(View.GONE);
        }
        else{
            weightText.setText(productUnits.get(position).getWeight() +"g");
        }

        ImageView foodUnitImage = rowView.findViewById(R.id.list_item_product_units_image);
        if(productUnit.getImage() == null){
            foodUnitImage.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
            //foodUnitImage.setBackgroundColor(R.color.yellow);
        }
        else {
            foodUnitImage.setImageBitmap(productUnit.getImage());
        }


        barcodeButton.setOnClickListener(view1 -> {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.scanBarcode(productUnit);
        });

        return rowView;
    }

    private void scanCode(View view) {

    }

}
