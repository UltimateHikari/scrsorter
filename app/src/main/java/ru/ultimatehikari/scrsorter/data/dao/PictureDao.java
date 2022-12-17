package ru.ultimatehikari.scrsorter.data.dao;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.CategoryPictureCrossRef;
import ru.ultimatehikari.scrsorter.data.entity.MinorCategoryPictureCrossRef;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;
import ru.ultimatehikari.scrsorter.model.Category;

@Dao
public interface PictureDao {

    @Query("SELECT * FROM pictures")
    LiveData<List<PictureEntity>> loadAllPictures();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PictureEntity> pictures);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllNew(List<PictureEntity> pictures);

    @Query("SELECT pictureId FROM pictures WHERE path = :url")
    Long findIdByUrl(String url);

    /*
     * foo is called mostly from markup screen
     * or from imagescanworker, but there we know about nocategory!
     * so all ids are known for sure
     *
     * utterly ineffective...
     */

    @Transaction
    default void updateWithCategories(
            List<PictureEntityWithCategories> pictureEntityWithCategories){
        for(PictureEntityWithCategories p: pictureEntityWithCategories){
            var cr = new CategoryPictureCrossRef();
            cr.pictureId = findIdByUrl(p.getUrl());
            cr.categoryId = p.getCategory().getCategoryId();
            insert_category_crossref(cr);
            Log.i("CAT", cr.categoryId + "  " + cr.pictureId);

            for(Category e: p.getMinorCategories()){
                var mcr = new MinorCategoryPictureCrossRef();
                mcr.pictureId = cr.pictureId;
                mcr.categoryId = e.getCategoryId();
                insert_minor_category_crossref(mcr);
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM pictures")
    LiveData<List<PictureEntityWithCategories>> loadAllPicturesWithCategories();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_category_crossref(CategoryPictureCrossRef categoryPictureCrossRef);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_minor_category_crossref(MinorCategoryPictureCrossRef categoryPictureCrossRef);

    @Query("select * from pictures where pictureId = :pictureId")
    LiveData<PictureEntity> loadPicture(int pictureId);

    @Query("select count(*) from pictures")
    int count();
}
