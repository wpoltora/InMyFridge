package com.example.inmyfridge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.inmyfridge.databinding.ActivityMainBinding;
import com.example.inmyfridge.fragments.NewFridgeItemFragment;
import com.example.inmyfridge.fragments.NewRecipeFragment;
import com.example.inmyfridge.fragments.NewRecipeProductSelectFragment;
import com.example.inmyfridge.fragments.main.FoodsFragment;
import com.example.inmyfridge.fragments.main.FridgeFragment;
import com.example.inmyfridge.fragments.NewProductFragment;
import com.example.inmyfridge.fragments.main.RecipeFragment;
import com.example.inmyfridge.fragments.main.ShoppingFragment;

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
            ImageView imageView = findViewById(R.id.new_product_image_input);
            imageView.setImageBitmap(photo);
        }

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap photo = (Bitmap) bundle.get("data");
            ImageView imageView = findViewById(R.id.new_unit_image_input);
            imageView.setImageBitmap(photo);
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

    public void addRecipePage(View view){replaceFragment(new NewRecipeFragment(), true);}

    public void selectProductsForRecipePage(View view){replaceFragment(new NewRecipeProductSelectFragment(), true);}

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