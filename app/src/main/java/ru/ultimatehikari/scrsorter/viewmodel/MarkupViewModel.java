package ru.ultimatehikari.scrsorter.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.DataRepository;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;

public class MarkupViewModel extends AndroidViewModel {
    private final LiveData<List<CategoryEntity>> listLiveData;
    private LiveData<List<PictureEntityWithCategories>> listPicturesLiveData;

    private final DataRepository repository;

    public LiveData<List<CategoryEntity>> getCategories() {
        return listLiveData;
    }
    public LiveData<List<PictureEntityWithCategories>> getPictures() {

        return listPicturesLiveData;
    }

    public MarkupViewModel(@NonNull Application app) {
        super(app);

        repository = ((App) app).getRepository();

        listLiveData = repository.getCategories();
        listPicturesLiveData = repository.getPictures();

    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // picturesLiveData.
    }

    public void updateTags(Context context, Data formData, LifecycleOwner owner) {
        var request = new OneTimeWorkRequest.Builder(UpdateTagsWorker.class)
                .setInputData(formData)
                .build();
        fixateLiveData();
        WorkManager.getInstance(context).enqueue(request);
    }

    private void fixateLiveData() {
        var fix = new MutableLiveData<List<PictureEntityWithCategories>>();
        fix.postValue(listPicturesLiveData.getValue());
        listPicturesLiveData = fix;
    }
}
