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

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.ProductUnit;
import com.example.inmyfridge.foods.viewmodels.NewProductViewModel;

/**This is a fragment used for adding a new product to database*/
public class NewProductFragment extends Fragment {
    NewProductViewModel viewModel;
    private View view;

    public NewProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NewProductViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_new_product, container, false);
        Button buttonNewProductComplete = view.findViewById(R.id.new_product_add_button);
        buttonNewProductComplete.setOnClickListener(this::addNewProductToList);
        return view;
    }

    public void addNewProductToList(View view){
         String name = parseProductString(R.id.new_product_name_input);
         int kcal = parseProductInteger(R.id.new_product_kcal_input);
         int protein = parseProductInteger(R.id.new_product_protein_input);
         int carb = parseProductInteger(R.id.new_product_carb_input);
         int fat = parseProductInteger(R.id.new_product_fat_input);
         Bitmap image = parseProductImage(R.id.new_product_image_input);

         if(name.isEmpty()){
             return;
         }

        Product product = new Product.ProductBuilder(name)
                .kcal(kcal)
                .protein(protein)
                .carb(carb)
                .fat(fat)
                .image(image)
                .build();
       // DataHolder.getInstance().productList.add(product);
        viewModel.insertProduct(product);
        ProductUnit productUnit = new ProductUnit.ProductUnitBuilder()
                .weight(1)
                .isLoose(true)
                .image(null)
                .barcode(null)
                .parentId(product.getId())
                .build();
        viewModel.insertProductUnit(productUnit);
        getParentFragmentManager().popBackStack();
    }



    /**The functions below are used to get data from user input*/
    public int parseProductInteger(int id){
        String fieldValue = ((EditText)view.findViewById(id)).getText().toString();
        if(fieldValue.isEmpty()){
            return 0;
        }
        return Integer.parseInt(fieldValue);
    }

    public String parseProductString(int id){
        return ((EditText)view.findViewById(id)).getText().toString();
    }

    public Bitmap parseProductImage(int id){
        ImageView imageView = view.findViewById(id);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        if(drawable != null) {
            return drawable.getBitmap();
        }
        return null;
    }
}