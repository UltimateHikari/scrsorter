package ru.ultimatehikari.scrsorter.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Arrays;

import ru.ultimatehikari.scrsorter.R;

public class PopulateWorker extends Worker {
    public PopulateWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    @NonNull
    public Result doWork() {

        AppDatabase database = AppDatabase.getInstance(getApplicationContext());
        var count = database.pictureDao().count();
        Log.i("DBINIT", "Populating db..." + count);

//        if(count == 0) {
//            database.runInTransaction(() -> {
//                database.pictureDao().insertAll(MockGenerator.generatePictures());
//            });
//        }

        database.runInTransaction(() -> {
            database.categoryDao().insertAll(
                    MockGenerator.generateCategoriesFromXML(
                            getApplicationContext().getResources()
                            .getStringArray(R.array.default_categories)
                    )
            );
        });

        database.setDatabaseCreated();
        return Result.success();
    }


}