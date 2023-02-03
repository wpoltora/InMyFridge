package com.example.inmyfridge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.inmyfridge.databinding.ActivityMainBinding;
import com.example.inmyfridge.fridge.fragments.NewFridgeItemFragment;
import com.example.inmyfridge.recipes.NewRecipeActivity;
import com.example.inmyfridge.recipes.fragments.NewRecipeFragment;
import com.example.inmyfridge.recipes.fragments.NewRecipeProductSelectFragment;
import com.example.inmyfridge.foods.fragments.FoodsFragment;
import com.example.inmyfridge.fridge.fragments.FridgeFragment;
import com.example.inmyfridge.foods.fragments.NewProductFragment;
import com.example.inmyfridge.recipes.fragments.RecipeFragment;
import com.example.inmyfridge.shopping.ShoppingFragment;
import com.example.inmyfridge.tracking.fragments.TrackerFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    /**ACTIVITY METHODS*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.fridge:
                    replaceFragment(new FridgeFragment(), false);
                    break;
                case R.id.recipes:
                    replaceFragment(new RecipeFragment(), false);
                    break;
                case R.id.foods:
                    replaceFragment(new FoodsFragment(), false);
                    break;
                case R.id.tracker:
                    replaceFragment(new TrackerFragment(), false);
                    break;
                case R.id.shopping:
                    replaceFragment(new ShoppingFragment(), false);
                    break;

            }
        return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            int width = photo.getWidth();
            int height = photo.getHeight();
            int newDimension = Math.min(width, height);
            Bitmap square = Bitmap.createBitmap(photo, (width - newDimension) / 2, (height - newDimension) / 2, newDimension, newDimension);

            ImageView imageView = findViewById(R.id.new_product_image_input);
            imageView.setImageBitmap(square);
        }

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            int width = photo.getWidth();
            int height = photo.getHeight();
            int newDimension = Math.min(width, height);
            Bitmap square = Bitmap.createBitmap(photo, (width - newDimension) / 2, (height - newDimension) / 2, newDimension, newDimension);
            ImageView imageView = findViewById(R.id.new_unit_image_input);
            imageView.setImageBitmap(square);
        }

        if(requestCode==10){
            replaceFragment(new RecipeFragment(), false);
        }
    }
    /**------------------------------------------------------------------*/

    /**FRAGMENT MANAGEMENT*/
    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void addProductPage(View view){
        replaceFragment(new NewProductFragment(), true);
    }

    public void addToFridge(View view){replaceFragment(new NewFridgeItemFragment(), true);}

    public void addRecipePage(View view){
        Intent switchActivityIntent = new Intent(this, NewRecipeActivity.class);
        startActivityForResult(switchActivityIntent,10);}



    public void returnToPrev(View view){
        onBackPressed();
    }
    /**----------------------------------------------------------------*/

    /**PRODUCT MANAGEMENT*/
    public void selectProductImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    public void selectUnitImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
    }

    /**--------------------------------------------------------------*/








}