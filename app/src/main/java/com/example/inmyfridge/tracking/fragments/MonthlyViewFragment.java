package com.example.inmyfridge.tracking.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.data.model.Product;
import com.example.inmyfridge.data.model.DailyData;
import com.example.inmyfridge.tracking.viewmodels.MonthlyViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


public class MonthlyViewFragment extends Fragment {
    private MonthlyViewModel viewModel;

    public MonthlyViewFragment() {
        // Required empty public constructor
    }

    public static MonthlyViewFragment newInstance(String param1, String param2) {
        return new MonthlyViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MonthlyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_view, container, false);
        setMonthlyView(view);

        ImageButton previousDateButton = view.findViewById(R.id.tracker_date_back_button);
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);

        previousDateButton.setOnClickListener(view1 -> {
            viewModel.decreaseDisplayedMonths(1);
            setMonthlyView(view1.getRootView());
        });
        nextDateButton.setOnClickListener(view12 -> {
            viewModel.increaseDisplayedMonths(1);
            setMonthlyView(view12.getRootView());
        });
        return view;
    }

    private void setMonthlyView(View view){
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);

        if(viewModel.displayedMonthIsCurrent()){
            nextDateButton.setVisibility(View.GONE);
        }
        else
        {
            nextDateButton.setVisibility(View.VISIBLE);
        }

        MonthlyViewModel.MonthlyMacros monthlyMacros = viewModel.calculateMacrosForMonth();
        TextView caloriesText = view.findViewById(R.id.monthly_view_calories);
        caloriesText.setText((int) monthlyMacros.getAverageCalories() + "");

        TextView totalCaloriesText = view.findViewById(R.id.monthly_view_total_calories);
        totalCaloriesText.setText("Total: "+(int) monthlyMacros.getTotalCalories());

        TextView proteinText = view.findViewById(R.id.monthly_view_protein);
        proteinText.setText((int)monthlyMacros.getTotalProtein()  + "g");
        TextView carbText = view.findViewById(R.id.monthly_view_carb);
        carbText.setText((int)monthlyMacros.getTotalCarbs()  + "g");
        TextView fatText = view.findViewById(R.id.monthly_view_fat);
        fatText.setText((int)monthlyMacros.getTotalFats() + "g");

        TextView proteinAvgText = view.findViewById(R.id.monthly_view_protein_average);
        proteinAvgText.setText((int) monthlyMacros.getProteinAverage() + "g");
        TextView carbAvgText = view.findViewById(R.id.monthly_view_carbs_average);
        carbAvgText.setText((int) monthlyMacros.getCarbAverage() + "g");
        TextView fatAvgText = view.findViewById(R.id.monthly_view_fats_average);
        fatAvgText.setText((int) monthlyMacros.getFatsAverage() + "g");

        TextView proteinPercentageText = view.findViewById(R.id.monthly_view_protein_percentage);
        proteinPercentageText.setText((int)monthlyMacros.getProteinPercentage()+ "%");
        TextView carbPercentageText = view.findViewById(R.id.monthly_view_carb_percentage);
        carbPercentageText.setText((int)monthlyMacros.getCarbsPercentage() + "%");
        TextView fatPercentageText = view.findViewById(R.id.monthly_view_fat_percentage);
        fatPercentageText.setText((int)monthlyMacros.getFatsPercentage() + "%");
        TextView dateText = view.findViewById(R.id.tracker_date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yy", new Locale("en", "EN"));
        dateText.setText(viewModel.getDisplayedDate().minusMonths(1).format(formatter) + " - " + viewModel.getDisplayedDate().format(formatter));
    }

}