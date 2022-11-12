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

    private DataRepository(AppDatabase database) {
        this.database = database;
        observablePictures = new MediatorLiveData<>();

        observablePictures.addSource(database.pictureDao().loadAllPictures(),
                pictureEntities -> {
                    if (database.getDatabaseCreated().getValue() != null) {
                        observablePictures.postValue(pictureEntities);
                    }
                });
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
