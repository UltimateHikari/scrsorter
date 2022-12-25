package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"pictureId"}, tableName = "picture_categories")
public class CategoryPictureCrossRef {
        public long categoryId;
        public long pictureId;
}
