package ru.ultimatehikari.scrsorter.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.model.Category;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CategoryEntity> categoryEntities);

    @Query("SELECT * FROM categories")
    LiveData<List<CategoryEntity>> loadAllCategories();

    @Query("SELECT categoryId FROM categories WHERE name = :categoryName")
    Long getCategoryId(String categoryName);

    @Query("select count(*) from categories")
    int count();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addCategory(CategoryEntity categoryEntity);
}
