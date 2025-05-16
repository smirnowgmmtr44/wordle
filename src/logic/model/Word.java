package logic.model;

import logic.enums.LetterStatus;

import java.util.List;
import java.util.ArrayList;

public class Word {

    private final List<Letter> letters;

    public Word() {
        letters = new ArrayList<>();
    }

    /**
     * Возващает слово у которого все буквы в определенном статусе
     *
     * @param word   Слово
     * @param status Статус который будет указан у всех букв в слове
     * @return Возвращает список букв в определенном статусе
     */
    public static Word getWordWithAllLettersInStatus(String word, LetterStatus status) {
        Word result = new Word();
        //List<Letter> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            result.add(new Letter(c, status));
        }
        return result;
    }

    public void add(Letter letter) {
        letters.add(letter);
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public String getWordString() {
        StringBuilder result = new StringBuilder();
        for (Letter l : letters) {
            result.append(l.getLetter());
        }
        return result.toString();
    }
}