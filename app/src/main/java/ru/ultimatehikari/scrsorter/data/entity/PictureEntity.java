package ru.ultimatehikari.scrsorter.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import ru.ultimatehikari.scrsorter.model.Picture;

@Getter
@Setter
@Entity(tableName = "pictures")
public class PictureEntity implements Picture {
    @PrimaryKey
    public Long id;
    public String name;
    public String url;
}
