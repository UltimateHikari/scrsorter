package ru.ultimatehikari.scrsorter.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;

public class UpdateTagsWorker extends Worker {

    public UpdateTagsWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        var gsonnedKey = getInputData().getString("GsonnedImage");
        if(gsonnedKey == null) {
            return Result.failure();
        }
        var gson = new Gson();
        PictureEntityWithCategories picture = gson.fromJson(gsonnedKey, PictureEntityWithCategories.class);

        Log.i("HSON", picture.toString());

        //TODO updateTags in PictureDao
        AppDatabase.getInstance(getApplicationContext())
                .pictureDao().updatePicture(picture);

        return Result.success();
    }
}
