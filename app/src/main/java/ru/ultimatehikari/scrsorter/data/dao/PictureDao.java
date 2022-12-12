package ru.ultimatehikari.scrsorter.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

@Dao
public interface PictureDao {
    @Query("SELECT * FROM pictures")
    LiveData<List<PictureEntity>> loadAllPictures();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PictureEntity> pictures);

    @Query("select * from pictures where id = :productId")
    LiveData<PictureEntity> loadPicture(int productId);

    @Query("select count(*) from pictures")
    int count();
}
