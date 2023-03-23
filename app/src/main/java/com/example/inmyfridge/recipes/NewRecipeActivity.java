package com.example.inmyfridge.recipes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.recipes.fragments.NewRecipeFragment;
import com.example.inmyfridge.recipes.fragments.NewRecipeProductSelectFragment;
import com.example.inmyfridge.recipes.viewmodels.NewRecipeViewModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NewRecipeActivity extends AppCompatActivity {
    NewRecipeViewModel viewModel;
    private ArrayList<Product> selectedProducts = new ArrayList<>();
    private ArrayList<Product> products;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NewRecipeViewModel.class);
        products = viewModel.getAllProducts().stream().filter(Product::isVisible).collect(Collectors.toCollection(ArrayList::new));
        setContentView(R.layout.activity_new_recipe);
        replaceFragment(new NewRecipeFragment(), false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.new_recipe_layout, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void selectProductsForRecipePage(View view){replaceFragment(new NewRecipeProductSelectFragment(), true);}

    public void returnToPrev(View view){
        onBackPressed();
    }

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            int width = photo.getWidth();
            int height = photo.getHeight();
            int newDimension = Math.min(width, height);
            Bitmap square = Bitmap.createBitmap(photo, (width - newDimension) / 2, (height - newDimension) / 2, newDimension, newDimension);

            ImageView imageView = findViewById(R.id.new_recipe_image_input);
            imageView.setImageTintList(null);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = imageView.getHeight() * 2;
            params.width = imageView.getWidth() *2;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(square);
        }
    }

    public void selectRecipeImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 3);
        }
    }
}

