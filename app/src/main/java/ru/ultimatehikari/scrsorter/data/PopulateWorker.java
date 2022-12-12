package ru.ultimatehikari.scrsorter.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

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
        if(count == 0) {
            Log.i("DBINIT", "generating..");

            database.runInTransaction(() -> {
                database.pictureDao().insertAll(MockGenerator.generatePictures());
            });

            Log.i("DBINIT", "generated");
        }
        database.setDatabaseCreated();
        return Result.success();
    }
}