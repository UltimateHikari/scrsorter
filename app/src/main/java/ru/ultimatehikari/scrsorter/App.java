package ru.ultimatehikari.scrsorter;

import android.app.Application;

import ru.ultimatehikari.scrsorter.data.AppDatabase;
import ru.ultimatehikari.scrsorter.model.PictureService;

public class App extends Application {

    /*
     * However, you should never store mutable instance data inside the Application object
     * because if you assume that your data will stay there, your application will
     * inevitably crash at some point with a NullPointerException.
     * The application object is not guaranteed to stay in memory forever, it will get killed.
     */

    private AppExecutorsPool appExecutorsPool;

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutorsPool = new AppExecutorsPool();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, appExecutorsPool);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
