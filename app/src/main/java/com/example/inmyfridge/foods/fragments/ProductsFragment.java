package com.example.inmyfridge.foods.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.foods.viewadapters.ProductListAdapter;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.foods.viewmodels.ProductsViewModel;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**This is the main fragment for displaying foods saved in a database*/
public class ProductsFragment extends Fragment implements ProductListAdapter.ItemCallback{
    Context context;
    private MainActivity mainActivity;
    ProductsViewModel viewModel;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);
        context = getContext();
        EditText searchBar = view.findViewById(R.id.foods_search_bar);
        ListView listView = view.findViewById(R.id.foods_list);

        ProductListAdapter adapter = new ProductListAdapter(this.getActivity(),
                new ArrayList<>(viewModel.getAllProducts().stream().filter(Product::isVisible).collect(Collectors.toList())),this);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Product product = (Product)adapterView.getItemAtPosition(i);
            ConstraintLayout buttonBar = view1.findViewById(R.id.list_item_products_button_panel);
            boolean isVisible = buttonBar.getVisibility() == View.VISIBLE;

            for(int y=0; y<adapterView.getCount(); y++){
                adapterView.getChildAt(y).findViewById(R.id.list_item_products_button_panel).setVisibility(View.GONE);
            }

            if(!isVisible){
                buttonBar.setVisibility(View.VISIBLE);
            }
            //mainActivity.replaceFragment(new ProductDetailsFragment(product, i), true);
        });


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }


    @Override
    public void onProductUpdated(Product product) {
        viewModel.updateProduct(product);
    }

    @Override
    public DailyData getDailyDataByDate(LocalDate date) {
        return viewModel.getDailyDataByDate(date);
    }

    @Override
    public void onDailyDataInserted(DailyData dailyData) {
        viewModel.insertDailyData(dailyData);
    }

    @Override
    public void onDailyDataUpdated(DailyData dailyData) {
        viewModel.updateDailyData(dailyData);
    }

    @Override
    public List<ProductUnit> getProductUnitsByProductID(UUID id) {
        return viewModel.getProductUnitsByProductID(id);
    }

    @Override
    public void onProductUnitUpdated(ProductUnit productUnit) {
        viewModel.updateProductUnit(productUnit);
    }
}