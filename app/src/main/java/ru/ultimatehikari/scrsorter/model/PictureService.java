package ru.ultimatehikari.scrsorter.model;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;

/*
 * Deprecated in favor of ViewModel
 */
public class PictureService {
    private final ArrayList<Picture> pictures;
    private final ArrayList<Consumer<List<Picture>>> listeners = new ArrayList<>();
    private static final Integer MAGIC_COUNT = 100;

    public PictureService(){
        // Deprecated: moved to Room ORM
        Faker faker = new Faker();
        pictures = IntStream.range(0,MAGIC_COUNT)
                .mapToObj(i -> {
                    PictureEntity picture = new PictureEntity();
                    picture.setId((long) i);
                    picture.setName(faker.name().name());
                    picture.setUrl("...");
                    return picture;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void deletePicture(Picture picture){
        OptionalInt result = IntStream.range(0, pictures.size())
                .filter(x -> picture.getId().equals(pictures.get(x).getId()))
                .findFirst();

        if (result.isPresent())
        {
            pictures.remove(result);
        }
        notifyListeners();
    }

    public void moveUp(Picture picture){
        OptionalInt result = IntStream.range(0, pictures.size())
                .filter(x -> picture.getId().equals(pictures.get(x).getId()))
                .findFirst();
        if (result.isPresent() && result.getAsInt() > 1)
        {
            Collections.swap(pictures, result.getAsInt(), result.getAsInt() - 1);
        }
        notifyListeners();
    }

    public void addListener(Consumer<List<Picture>> listener){
        listeners.add(listener);
        notifyListeners();
    }

    public void removeListener(Consumer<List<Picture>> listener){
        listeners.remove(listener);
        notifyListeners();
    }

    private void notifyListeners(){
        listeners.forEach(listener -> listener.accept(pictures));
    }
}
