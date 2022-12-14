package ru.ultimatehikari.scrsorter.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;

public class AddCategoryWorker extends Worker {

    public AddCategoryWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        var categoryInput = getInputData().getString("CATEGORY_NAME");
        if(categoryInput == null) {
            return Result.failure();
        }
        var category = new CategoryEntity();
        category.setName(categoryInput);

        AppDatabase.getInstance(getApplicationContext())
                .categoryDao().addCategory(category);

        return Result.success();
    }
}
