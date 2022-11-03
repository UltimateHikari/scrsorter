package ru.ultimatehikari.scrsorter.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.ultimatehikari.scrsorter.model.Picture;

public class RecyclerModel extends ViewModel {
    private final MutableLiveData<Picture> pictureLiveData = new MutableLiveData<>();

    public LiveData<Picture> getPicture() {
        return pictureLiveData;
    }

    public RecyclerModel() {
        // trigger user load.
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // picturesLiveData.
    }

}
