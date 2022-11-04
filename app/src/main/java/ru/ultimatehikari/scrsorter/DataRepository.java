package ru.ultimatehikari.scrsorter;

import ru.ultimatehikari.scrsorter.data.AppDatabase;

public class DataRepository {
    private static DataRepository instance;

    public DataRepository(AppDatabase database) {

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
}
