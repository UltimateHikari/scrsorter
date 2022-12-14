package ru.ultimatehikari.scrsorter.model;

import java.util.List;


public interface Picture {
    Long getPictureId();
    String getName();
    String getUrl();
    void setPictureId(Long pictureId);
    void setName(String name);
    void setUrl(String url);
}
