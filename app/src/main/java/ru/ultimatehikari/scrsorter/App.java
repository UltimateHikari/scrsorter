package ru.ultimatehikari.scrsorter;

import android.app.Application;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.model.PictureService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
