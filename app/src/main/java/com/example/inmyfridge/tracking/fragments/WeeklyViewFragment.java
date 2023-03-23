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
import com.example.inmyfridge.tracking.viewmodels.WeeklyViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyViewFragment extends Fragment {
    private WeeklyViewModel viewModel;

    public WeeklyViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(WeeklyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly_view, container, false);
        setWeeklyView(view);
        ImageButton previousDateButton = view.findViewById(R.id.tracker_date_back_button);
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);

        previousDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.decreaseDateByWeeks(1);
                setWeeklyView(view.getRootView());
            }
        });
        nextDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.increaseDateByWeeks(1);
                setWeeklyView(view.getRootView());
            }
        });
        return view;
    }

    private void setWeeklyView(View view){
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);
        if(viewModel.displayedWeekIsCurrent()){
            nextDateButton.setVisibility(View.GONE);
        }
        else {
            nextDateButton.setVisibility(View.VISIBLE);
        }

        WeeklyViewModel.WeeklyMacros weeklyMacros = viewModel.calculateMacrosForWeek();

        TextView caloriesText = view.findViewById(R.id.weekly_view_calories);
        caloriesText.setText((int)weeklyMacros.getAverageCalories()  + "");

        TextView totalCaloriesText = view.findViewById(R.id.weekly_view_total_calories);
        totalCaloriesText.setText("Total: "+(int) weeklyMacros.getTotalCalories());

        TextView proteinText = view.findViewById(R.id.weekly_view_protein);
        proteinText.setText((int)weeklyMacros.getTotalProtein()  + "g");
        TextView carbText = view.findViewById(R.id.weekly_view_carbs);
        carbText.setText((int)weeklyMacros.getTotalCarbs()  + "g");
        TextView fatText = view.findViewById(R.id.weekly_view_fats);
        fatText.setText((int)weeklyMacros.getTotalFats() + "g");
        TextView proteinAvgText = view.findViewById(R.id.weekly_view_protein_average);
        proteinAvgText.setText((int) weeklyMacros.getProteinAverage() + "g");
        TextView carbAvgText = view.findViewById(R.id.weekly_view_carbs_average);
        carbAvgText.setText((int) weeklyMacros.getCarbAverage() + "g");
        TextView fatAvgText = view.findViewById(R.id.weekly_view_fats_average);
        fatAvgText.setText((int) weeklyMacros.getFatsAverage() + "g");

        TextView proteinPercentageText = view.findViewById(R.id.weekly_view_protein_percentage);
        proteinPercentageText.setText((int)weeklyMacros.getProteinPercentage() + "%");
        TextView carbPercentageText = view.findViewById(R.id.weekly_view_carb_percentage);
        carbPercentageText.setText((int)weeklyMacros.getCarbsPercentage() + "%");
        TextView fatPercentageText = view.findViewById(R.id.weekly_view_fat_percentage);
        fatPercentageText.setText((int)weeklyMacros.getFatsPercentage() + "%");

        LocalDate weekBefore = viewModel.getDisplayedDate().minusDays(7);
        TextView dateText = view.findViewById(R.id.tracker_date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
        dateText.setText(weekBefore.format(formatter)+ " - " + viewModel.getDisplayedDate().format(formatter));
    }
}