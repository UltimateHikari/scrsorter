package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import ru.ultimatehikari.scrsorter.model.Category;
import ru.ultimatehikari.scrsorter.model.Picture;
import ru.ultimatehikari.scrsorter.model.PictureWithCategories;

public class PictureEntityWithCategories implements PictureWithCategories {
    @Embedded public PictureEntity picture;
    @Relation(
            parentColumn = "pictureId",
            entityColumn = "categoryId",
            associateBy = @Junction(CategoryPictureCrossRef.class)
    )
    public CategoryEntity category;
    @Relation(
            parentColumn = "pictureId",
            entityColumn = "categoryId",
            associateBy = @Junction(MinorCategoryPictureCrossRef.class)
    )
    public List<CategoryEntity> minorCategories;

    @Override
    public Long getPictureId() {
        return picture.pictureId;
    }

    @Override
    public String getName() {
        return picture.name;
    }

    @Override
    public String getUrl() {
        return picture.url;
    }

    @Override
    public void setPictureId(Long pictureId) {
        picture.setPictureId(pictureId);
    }

    @Override
    public void setName(String name) {
        picture.setName(name);
    }

    @Override
    public void setUrl(String url) {
        picture.setUrl(url);
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public List<? extends Category> getMinorCategories() {
        return minorCategories;
    }

    @Override
    public void setCategory(Category category) {
        this.category = (CategoryEntity) category;
    }

    @Override
    public void setMinorCategories(List<? extends Category> minorCategories) {
        this.minorCategories = minorCategories.stream()
                .filter(CategoryEntity.class::isInstance)
                .map(CategoryEntity.class::cast)
                .collect(Collectors.toList());;
    }
}
