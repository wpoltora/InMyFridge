package com.example.inmyfridge.foods.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.R;
import com.example.inmyfridge.foods.viewmodels.NewProductUnitViewModel;

/**This fragment is used for creating a new product unit(for example a can of food weighing 100g)*/
public class NewProductUnitFragment extends Fragment {
    NewProductUnitViewModel viewModel;

    private Product product;
    private View view;
    public NewProductUnitFragment() {
        // Required empty public constructor
    }

    public NewProductUnitFragment(Product product){
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NewProductUnitViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_new_unit, container, false);
        Button finishButton =  view.findViewById(R.id.new_unit_add_button);
        //CheckBox looseCheckBox = view.findViewById(R.id.new_unit_loose_checkbox);
        LinearLayout weightInputWrapper = view.findViewById(R.id.new_unit_weight_input_wrapper);

        finishButton.setOnClickListener(this::addNewUnitToProduct);
       // looseCheckBox.setOnClickListener(view -> weightInputWrapper.setVisibility(looseCheckBox.isChecked() ? View.INVISIBLE : View.VISIBLE));

        return view;
    }

    private void addNewUnitToProduct(View view){
        int weight = parseProductInteger(R.id.new_unit_weight_input);
        //boolean isLoose = ((CheckBox)this.view.findViewById(R.id.new_unit_loose_checkbox)).isChecked();

       // if(isLoose){
       //     weight = 1;
       // }

        ProductUnit productUnit = new ProductUnit.ProductUnitBuilder()
                .weight(weight)
                .image(parseProductImage(R.id.new_unit_image_input))
                .barcode(null)
                .isLoose(false)
                .parentId(product.getId())
                .build();

        viewModel.insertProductUnit(productUnit);
        //MyApp.getInstance().getDatabase().productDao().updateProduct(product);
        getParentFragmentManager().popBackStack();
    }

    /**The functions below are used to get data from user input*/
    public int parseProductInteger(int id){
        return Integer.parseInt(((EditText)view.findViewById(id)).getText().toString());
    }

    public String parseProductString(int id){
        return ((EditText)view.findViewById(id)).getText().toString();
    }

    public Bitmap parseProductImage(int id){
        ImageView imageView = view.findViewById(id);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        return drawable.getBitmap();
    }
}