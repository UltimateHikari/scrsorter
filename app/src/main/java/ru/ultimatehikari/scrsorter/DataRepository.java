package ru.ultimatehikari.scrsorter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

public class DataRepository {
    private static DataRepository instance;
    private final AppDatabase database;

    private final MediatorLiveData<List<PictureEntity>> observablePictures;
    private final MediatorLiveData<Boolean> databaseLatch;

    private DataRepository(AppDatabase database) {
        this.database = database;
        observablePictures = new MediatorLiveData<>();
        databaseLatch = new MediatorLiveData<>();

        databaseLatch.addSource(database.getDatabaseCreated(),
                isCreated -> {
                    if(isCreated){
                        observablePictures.postValue(database.pictureDao().loadAllPictures().getValue());
                    }
                });

        observablePictures.addSource(database.pictureDao().loadAllPictures(),
                observablePictures::postValue);
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

    public LiveData<List<PictureEntity>> getPictures() {
        return observablePictures;
    }
}
