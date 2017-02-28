package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import ru.music.dao.interfaces.MP3Dao;
import ru.music.dao.model.MP3;

public class MusicMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MP3Dao sqlServerDao = (MP3Dao) context.getBean("sqlServerDao");

        System.out.println(sqlServerDao.getById(2));
    }
}
