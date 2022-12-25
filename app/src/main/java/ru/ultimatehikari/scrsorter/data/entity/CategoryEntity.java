package ru.ultimatehikari.scrsorter.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.concurrent.Callable;

import lombok.Getter;
import lombok.Setter;
import ru.ultimatehikari.scrsorter.model.Category;
import ru.ultimatehikari.scrsorter.model.Picture;

@Getter
@Setter
@Entity(tableName = "categories", indices = {@Index(value = {"name"}, unique = true)})
public class CategoryEntity implements Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    public Long categoryId;
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
