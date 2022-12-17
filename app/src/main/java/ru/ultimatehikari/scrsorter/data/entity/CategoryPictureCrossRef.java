package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"pictureId"})
public class CategoryPictureCrossRef {
        public long categoryId;
        public long pictureId;
}
