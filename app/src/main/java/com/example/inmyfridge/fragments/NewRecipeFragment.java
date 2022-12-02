package com.example.inmyfridge.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.inmyfridge.MainActivity;
import com.example.inmyfridge.R;
import com.example.inmyfridge.adapters.ExpandableListViewAdapter;
import com.example.inmyfridge.data.DataHolder;
import com.example.inmyfridge.models.Recipe;

import java.util.ArrayList;

public class NewRecipeFragment extends Fragment {

    public NewRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_recipe
                , container, false);
        Recipe recipe = new Recipe("empty", null, new ArrayList<>());


        return view;
    }
}
