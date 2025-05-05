package storage;

import java.io.IOException;

public interface WordIOInterface {

    /**
     * Этот метод ищет слово в хранилище
     * возвращает true если слово найдено, и false если не найдено
     */
    boolean search(String word) throws IOException;

    /**
     * Этот метод возвращает слово из хранилища по его id
     */
    String getWordById(int id) throws IOException;

    /**
     * Этот метод возвращает случайное слово из хранилища
     */
    String getRandomWord() throws IOException;

}