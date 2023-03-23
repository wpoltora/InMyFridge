package com.example.inmyfridge.tracking.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.inmyfridge.R;
import com.example.inmyfridge.tracking.viewmodels.DailyViewModel;

import java.time.format.DateTimeFormatter;


public class DailyViewFragment extends Fragment {
    private DailyViewModel viewModel;
    public DailyViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(DailyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_daily_view, container, false);
        setDailyView(view);
        ImageButton previousDateButton = view.findViewById(R.id.tracker_date_back_button);
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);

        previousDateButton.setOnClickListener(view12 -> {
            viewModel.decreaseDisplayedDays(1);
            setDailyView(view12.getRootView());
        });
        nextDateButton.setOnClickListener(view1 -> {
            viewModel.increaseDisplayedDays(1);
            setDailyView(view1.getRootView());
        });
        return view;
    }

    private void setDailyView(View view){
        ImageButton nextDateButton = view.findViewById(R.id.tracker_date_next_button);
        if(viewModel.displayedDateIsToday()){
            nextDateButton.setVisibility(View.GONE);
        }
        else {
            nextDateButton.setVisibility(View.VISIBLE);
        }

        double calories = viewModel.getCalories();
        double protein = viewModel.getProtein();
        double carbs = viewModel.getCarbs();
        double fats = viewModel.getFats();
        double totalWeight = protein + carbs + fats;
        double proteinPercentage = protein/totalWeight*100;
        double carbPercentage = carbs/totalWeight*100;
        double fatPercentage = fats/totalWeight*100;

        TextView caloriesText = view.findViewById(R.id.daily_view_calories);
        caloriesText.setText((int)calories  + "");
        TextView proteinText = view.findViewById(R.id.daily_view_protein);
        proteinText.setText((int)protein  + "g");
        TextView carbText = view.findViewById(R.id.daily_view_carbs);
        carbText.setText((int)carbs  + "g");
        TextView fatText = view.findViewById(R.id.daily_view_fats);
        fatText.setText((int)fats + "g");
        TextView proteinPercentageText = view.findViewById(R.id.daily_view_protein_percentage);
        proteinPercentageText.setText((int)proteinPercentage + "%");
        TextView carbPercentageText = view.findViewById(R.id.daily_view_carb_percentage);
        carbPercentageText.setText((int)carbPercentage + "%");
        TextView fatPercentageText = view.findViewById(R.id.daily_view_fat_percentage);
        fatPercentageText.setText((int)fatPercentage + "%");

        TextView dateText = view.findViewById(R.id.tracker_date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
        dateText.setText(viewModel.getDisplayedDate().format(formatter));
    }
}