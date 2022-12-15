package ru.ultimatehikari.scrsorter.data;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

public class MockGenerator {
    private static final int MAGIC_COUNT = 10;

    public static List<PictureEntity> generatePictures(){
        Faker faker = new Faker();
        return IntStream.range(0,MAGIC_COUNT)
                .mapToObj(i -> {
                    PictureEntity picture = new PictureEntity();
                    picture.setPictureId((long) i);
                    picture.setName(faker.pokemon().name());
                    picture.setUrl("...");
                    return picture;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> generateCategories(){
        return Arrays.asList("Category-1", "Category-2", "Category-3");
    }

    public static List<CategoryEntity> generateCategoriesFromXML(String [] defaults){
        return Arrays.stream(defaults).map(s -> {
            var category = new CategoryEntity();
            category.setName(s);
            return category;
        }).collect(Collectors.toList());
    }
}
