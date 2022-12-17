package ru.ultimatehikari.scrsorter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.data.ImageScanWorker;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;

public class DataRepository {
    private static DataRepository instance;
    private final AppDatabase database;

    private final MediatorLiveData<List<PictureEntityWithCategories>> observablePictures;
    private final MediatorLiveData<List<CategoryEntity>> observableCategories;
    private final MediatorLiveData<Boolean> databaseLatch;

    private DataRepository(AppDatabase database) {
        this.database = database;
        observablePictures = new MediatorLiveData<>();
        observableCategories = new MediatorLiveData<>();
        databaseLatch = new MediatorLiveData<>();

        databaseLatch.addSource(database.getDatabaseCreated(),
                isCreated -> {
                    if(isCreated){
                        observablePictures.postValue(database.pictureDao().loadAllPicturesWithCategories().getValue());
                        observableCategories.postValue(database.categoryDao().loadAllCategories().getValue());
                    }
                });

        observablePictures.addSource(database.pictureDao().loadAllPicturesWithCategories(),
                observablePictures::postValue);
        observableCategories.addSource(database.categoryDao().loadAllCategories(),
                observableCategories::postValue);
    }

    public static DataRepository getInstance(AppDatabase database) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = new DataRepository(database);
                }
            }
        }
        return instance;
    }

    public LiveData<List<PictureEntityWithCategories>> getPictures() {
        return observablePictures;
    }
    public LiveData<List<CategoryEntity>> getCategories() {
        return observableCategories;
    }

    public void startImageScan(@NonNull Context context){
        WorkManager.getInstance(context.getApplicationContext())
                .enqueue(OneTimeWorkRequest.from(ImageScanWorker.class));
    }
}
