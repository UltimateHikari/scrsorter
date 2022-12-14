package ru.ultimatehikari.scrsorter.model;

import java.util.List;


public interface PictureWithCategories extends Picture {
    Category getCategory();
    List<? extends Category> getMinorCategories();
    void setCategory(Category category);
    void setMinorCategories(List<? extends Category> minorCategories);
}
