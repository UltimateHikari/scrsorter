package ru.ultimatehikari.scrsorter.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;
import ru.ultimatehikari.scrsorter.AppExecutorsPool;
import ru.ultimatehikari.scrsorter.data.dao.PictureDao;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

@Database(entities = {PictureEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "pictures_database";
    private static final int MAGIC_COUNT = 100;
    private static AppDatabase instance;

    public abstract PictureDao pictureDao();

    @Getter
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutorsPool pool) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = populateDatabase(context.getApplicationContext(), pool);
                }
            }
        }
        return instance;
    }

    private static AppDatabase populateDatabase(Context applicationContext, AppExecutorsPool pool) {
        return Room.databaseBuilder(applicationContext, AppDatabase.class,DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        pool.getPoolDiskIO().execute(() -> {
                            //TODO: move to Generator/Loader class
                            Faker faker = new Faker();
                            AppDatabase database = AppDatabase.getInstance(applicationContext, pool);
                            List<PictureEntity> pictures = IntStream.range(0,MAGIC_COUNT)
                                    .mapToObj(i -> {
                                        PictureEntity picture = new PictureEntity();
                                        picture.setId((long) i);
                                        picture.setName(faker.name().name());
                                        picture.setUrl("...");
                                        return picture;
                                    })
                                    .collect(Collectors.toCollection(ArrayList::new));
                            database.runInTransaction(() -> {database.pictureDao().insertAll(pictures);});
                            database.setDatabaseCreated();
                        });
                    }
                })
                .build();
    }

    private void setDatabaseCreated() {
        isDatabaseCreated.postValue(true);
    }
}
