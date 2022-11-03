package ru.ultimatehikari.scrsorter;

import android.app.Application;

import ru.ultimatehikari.scrsorter.model.PictureService;

public class App extends Application {

    private PictureService service;

    @Override
    public void onCreate() {
        super.onCreate();

        service = new PictureService();
    }

    public PictureService getService() {
        return service;
    }
}
