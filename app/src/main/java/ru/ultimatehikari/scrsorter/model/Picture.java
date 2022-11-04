package ru.ultimatehikari.scrsorter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


public interface Picture {
    Long getId();
    String getName();
    String getUrl();
}
