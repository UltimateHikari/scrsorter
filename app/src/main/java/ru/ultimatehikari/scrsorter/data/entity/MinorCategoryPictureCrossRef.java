package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"categoryId", "pictureId"})
public class MinorCategoryPictureCrossRef {
        public long categoryId;
        public long pictureId;
}
