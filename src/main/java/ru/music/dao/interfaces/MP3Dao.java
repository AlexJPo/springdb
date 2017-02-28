package ru.music.dao.interfaces;

import ru.music.dao.model.MP3;
import java.util.List;

public interface MP3Dao {
    void insert(MP3 mp3);

    void delete(MP3 mp3);

    MP3 getById(int id);

    List<MP3> getMp3ListByName(String name);

    List<MP3> getAllMp3List();

    List<MP3> getMp3ListByAuthor(String author);
}
