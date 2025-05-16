package logic.model;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import logic.enums.LetterStatus;
import logic.exception.NullTargetWordException;
//import storage.FileWordStorage;

public class Game {

    private final WordStorage storage;
    private final int countOfTry;
    private List<Word> attempts = new ArrayList<Word>();

    private final String targetWord;
    private final static int WORLD_LENGTH = 5;
    private final Pattern pattern = Pattern.compile("[a-zA-Z]{" + WORLD_LENGTH + "}", Pattern.CASE_INSENSITIVE);
    private final static int DEFAULT_COUNT_OF_TRY = 6;

    public Game(WordStorage storage) {
        this(storage, DEFAULT_COUNT_OF_TRY);
    }

    public Game(WordStorage storage,int rounds)  {
        this.storage = storage;
        targetWord = storage.getRandomWord().getValue();
        countOfTry = rounds;
        if(targetWord == null){
            throw new NullTargetWordException();
        }
    }

    public int getCountOfTry() {
        return countOfTry;
    }

    public List<Word> getAttempts() {
        return attempts;
    }

    public int getAttemptsLeft() {
        return countOfTry - attempts.size();
    }

    public String getTargetWord() {
        return targetWord;
    }

    /**
     * Метод для проверки на слово из 5 латинских букв
     */
    public boolean isWord(String word) {
        return word.length() == WORLD_LENGTH && pattern.matcher(word).find();
    }

    /**
     * Метод для проверки существует ли в хранилище слово
     */
    public boolean isWordExist(String word) {
        return isWord(word) && storage.search(word).getValue();
    }

    public boolean isWordNotExist(String word) {
        return !isWordExist(word);
    }

    /**
     * Метод для анализа букв в слове
     *
     * @param word Введенное пользователем слово
     * @return Метод возвращает true если слово совпадает с загаданным, иначе возвращает false
     */
    public List<Letter> wordAnalysis(String word) {
        Word result = new Word();
        if (targetWord.equals(word)) {
            attempts.add(Word.getWordWithAllLettersInStatus(word, LetterStatus.IN_POSITION));
        } else {
            for (int i = 0; i < word.length(); i++) {
                if (targetWord.indexOf(word.charAt(i)) != -1) {

                    if (word.charAt(i) == targetWord.charAt(i)) {
                        result.add(new Letter(word.charAt(i), LetterStatus.IN_POSITION));
                    } else {
                        result.add(new Letter(word.charAt(i), LetterStatus.USED));
                    }

                } else {

                    result.add(new Letter(word.charAt(i), LetterStatus.NOT_USED));

                }
            }
            attempts.add(result);
        }
        return attempts.get(attempts.size() - 1).getLetters();
    }

    public boolean isTargetWordInAttempts() {
        for (Word w : attempts) {
            if (w.getWordString().equals(targetWord)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTargetWordNotInAttempts() {
        return !isTargetWordInAttempts();
    }

    public boolean isGameActive() {
        return countOfTry > attempts.size() && isTargetWordNotInAttempts();
    }

}