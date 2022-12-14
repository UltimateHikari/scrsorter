package ru.ultimatehikari.scrsorter.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

@Dao
public interface PictureDao {

    @Transaction
    @Query("SELECT * FROM pictures")
    LiveData<List<PictureEntity>> loadAllPictures();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PictureEntity> pictures);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllNew(List<PictureEntity> pictures);

    @Transaction
    @Query("select * from pictures where pictureId = :pictureId")
    LiveData<PictureEntity> loadPicture(int pictureId);

    @Transaction
    @Query("select count(*) from pictures")
    int count();
}
