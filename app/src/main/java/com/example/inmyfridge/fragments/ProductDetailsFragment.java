package com.example.inmyfridge.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.inmyfridge.models.Product;
import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.adapters.ProductUnitsListAdapter;


public class ProductDetailsFragment extends Fragment {


    private Product product;
    //stored value of foodItem position in array
    private int position;

    private MainActivity mainActivity;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public ProductDetailsFragment(Product product, int position){
        this.product = product;
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        ListView listView = (ListView) view.findViewById(R.id.product_details_unit_list);
        ProductUnitsListAdapter adapter = new ProductUnitsListAdapter(this.getActivity(), this.product.getFoodUnitItems());
        listView.setAdapter(adapter);

        setTextViews(view);

        Button addButton = (Button) view.findViewById(R.id.product_details_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceFragment(new NewProductUnitFragment(position), true);
            }
        });

        return view;
    }

    private void setTextViews(View view){
        ((TextView)view.findViewById(R.id.product_details_name)).setText(product.getName());
        ((TextView)view.findViewById(R.id.product_details_kcal)).setText(Integer.toString(product.getKcal()));
        ((TextView)view.findViewById(R.id.product_details_protein)).setText(Integer.toString(product.getProtein()));
        ((TextView)view.findViewById(R.id.product_details_fat)).setText(Integer.toString(product.getFat()));
        ((TextView)view.findViewById(R.id.product_details_carb)).setText(Integer.toString(product.getCarb()));
    }


}