package ru.ultimatehikari.scrsorter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.DataRepository;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

public class PictureListViewModel extends AndroidViewModel {
    private final LiveData<List<PictureEntity>> listLiveData;

    private final DataRepository repository;

    public LiveData<List<PictureEntity>> getPictures() {
        return listLiveData;
    }

    public PictureListViewModel(@NonNull Application app) {
        super(app);

        repository = ((App) app).getRepository();

        listLiveData = repository.getPictures();
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // picturesLiveData.
    }

}
