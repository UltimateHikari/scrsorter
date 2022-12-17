package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"minorCategoryId", "pictureId"})
public class MinorCategoryPictureCrossRef {
        public long minorCategoryId;
        public long pictureId;
}
