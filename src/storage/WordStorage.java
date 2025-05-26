package storage;

import logic.model.ResultContainer;

public interface WordStorage {

    /**
     * Этот метод ищет слово в хранилище
     * возвращает true если слово найдено, и false если не найдено
     */
    ResultContainer<Boolean> search(String word);

    /**
     * Этот метод возвращает слово из хранилища по его id
     */
    ResultContainer<String> getWordById(int id);

    /**
     * Этот метод возвращает случайное слово из хранилища
     */
    ResultContainer<String> getRandomWord();

}