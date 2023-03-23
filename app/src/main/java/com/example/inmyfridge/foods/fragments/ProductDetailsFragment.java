package com.example.inmyfridge.foods.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.inmyfridge.foods.viewadapters.ProductUnitListAdapter;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.foods.viewmodels.ProductDetailsViewModel;

import java.util.ArrayList;


public class ProductDetailsFragment extends Fragment {
    ProductDetailsViewModel viewModel;

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
        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        ListView listView = view.findViewById(R.id.product_details_unit_list);
        ProductUnitListAdapter adapter = new ProductUnitListAdapter(this.getActivity(), new ArrayList<>(viewModel.getProductUnitsByProductID(product.getId())));
        listView.setAdapter(adapter);

        setTextViews(view);

        Button addButton = view.findViewById(R.id.product_details_add_button);
        addButton.setOnClickListener(view1 -> mainActivity.replaceFragment(new NewProductUnitFragment(product), true));

        return view;
    }

    private void setTextViews(View view){
        ((TextView)view.findViewById(R.id.product_details_name)).setText(product.getName());
        ((TextView)view.findViewById(R.id.product_details_kcal)).setText(Integer.toString(product.getKcal()));
        ((TextView)view.findViewById(R.id.product_details_protein)).setText(product.getProtein() + "g");
        ((TextView)view.findViewById(R.id.product_details_fat)).setText(product.getFat() + "g");
        ((TextView)view.findViewById(R.id.product_details_carb)).setText(product.getCarb() + "g");
    }


}