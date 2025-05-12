package storage;

import java.io.IOException;

public interface WordIOInterface {

    /**
     * Этот метод ищет слово в хранилище
     * возвращает true если слово найдено, и false если не найдено
     */
    WordIOResult<Boolean> search(String word);

    /**
     * Этот метод возвращает слово из хранилища по его id
     */
    WordIOResult<String> getWordById(int id);

    /**
     * Этот метод возвращает случайное слово из хранилища
     */
    WordIOResult<String> getRandomWord();

}