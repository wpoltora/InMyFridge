package com.example.inmyfridge.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.inmyfridge.data.AppDatabase;
import com.example.inmyfridge.data.dao.DailyDataDAO;
import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.List;

public class DailyDataRepository {
    private DailyDataDAO dailyDataDAO;
    private List<DailyData> allDailyDatas;

    public DailyDataRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        dailyDataDAO = database.dailyDataDAO();
        allDailyDatas = dailyDataDAO.getAll();
    }
    
    public List<DailyData> getAllDailyDatas(){
        return allDailyDatas;
    }

    public void insert(DailyData dailyData){
        new DailyDataRepository.InsertDailyDataAsyncTask(dailyDataDAO).execute(dailyData);
    }

    public void delete(DailyData dailyData){
        new DailyDataRepository.DeleteDailyDataAsyncTask(dailyDataDAO).execute(dailyData);
    }

    public void update(DailyData dailyData){
        new DailyDataRepository.UpdateDailyDataAsyncTask(dailyDataDAO).execute(dailyData);
    }

    public DailyData getDailyDataByDate(LocalDate localDate){
        return allDailyDatas.stream().filter(dailyData -> dailyData.getDate().equals(localDate)).findFirst().orElse(null);
    }

    private static class InsertDailyDataAsyncTask extends AsyncTask<DailyData, Void, Void> {
        private DailyDataDAO dailyDataDAO;

        private InsertDailyDataAsyncTask(DailyDataDAO dailyDataDAO){
            this.dailyDataDAO = dailyDataDAO;
        }

        @Override
        protected Void doInBackground(DailyData... dailyDatas) {
            dailyDataDAO.insertAll(dailyDatas);
            return null;
        }
    }

    private static class DeleteDailyDataAsyncTask extends AsyncTask<DailyData, Void, Void>{
        private DailyDataDAO dailyDataDAO;

        private DeleteDailyDataAsyncTask(DailyDataDAO dailyDataDAO){
            this.dailyDataDAO = dailyDataDAO;
        }

        @Override
        protected Void doInBackground(DailyData... dailyDatas) {
            dailyDataDAO.delete(dailyDatas[0]);
            return null;
        }
    }

    private static class UpdateDailyDataAsyncTask extends AsyncTask<DailyData, Void, Void>{
        private DailyDataDAO dailyDataDAO;

        private UpdateDailyDataAsyncTask(DailyDataDAO dailyDataDAO){
            this.dailyDataDAO = dailyDataDAO;
        }

        @Override
        protected Void doInBackground(DailyData... dailyDatas) {
            dailyDataDAO.update(dailyDatas[0]);
            return null;
        }
    }
}
