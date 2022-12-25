package ru.ultimatehikari.scrsorter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.stream.Collectors;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.DataRepository;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;

public class PictureListViewModel extends AndroidViewModel {
    private final LiveData<List<PictureEntityWithCategories>> listLiveData;

    private final DataRepository repository;

    public LiveData<List<PictureEntityWithCategories>> getPictures() {
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

    public LiveData<List<PictureEntityWithCategories>> getPicturesById(Long category_id) {
        return Transformations.map(listLiveData, list ->{
            return list.stream().filter(e -> {
                return e.category.categoryId == category_id;
            }).collect(Collectors.toList());
        });
    }
}
