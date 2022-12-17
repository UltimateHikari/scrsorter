package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ru.ultimatehikari.scrsorter.model.Category;
import ru.ultimatehikari.scrsorter.model.Picture;

@Getter
@Setter
@Entity(tableName = "pictures", indices = {@Index(value = {"path"},
        unique = true)})
public class PictureEntity implements Picture {
    @PrimaryKey
    public Long pictureId;
    public String name;
    @ColumnInfo(name = "path")
    public String url;
}
