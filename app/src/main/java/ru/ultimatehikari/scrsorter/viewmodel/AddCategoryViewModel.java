package ru.ultimatehikari.scrsorter.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.DataRepository;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;

public class AddCategoryViewModel extends AndroidViewModel {
    private final LiveData<List<CategoryEntity>> listLiveData;

    private final DataRepository repository;

    public AddCategoryViewModel(@NonNull Application app) {
        super(app);

        repository = ((App) app).getRepository();

        listLiveData = repository.getCategories();
    }

    public void addCategory(Context context, Data category){
        var request = new OneTimeWorkRequest.Builder(AddCategoryWorker.class)
                .setInputData(category)
                .build();
        WorkManager.getInstance(context).enqueue(request);
    }
}
