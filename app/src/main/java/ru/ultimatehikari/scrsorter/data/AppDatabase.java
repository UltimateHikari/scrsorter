package ru.ultimatehikari.scrsorter.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;
import ru.ultimatehikari.scrsorter.AppExecutorsPool;
import ru.ultimatehikari.scrsorter.data.dao.PictureDao;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;



@Database(entities = {PictureEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "pictures_database";
    private static AppDatabase instance;

    public abstract PictureDao pictureDao();

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room
                            .databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME)
                            .build();
                    instance.populateInitialData(context.getApplicationContext()/*, pool*/);
                }
            }
        }
        return instance;
    }

    private void populateInitialData(Context applicationContext) {
        WorkManager.getInstance(applicationContext)
                .enqueue(OneTimeWorkRequest.from(PopulateWorker.class));

    }

    public void setDatabaseCreated() {
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }
}
