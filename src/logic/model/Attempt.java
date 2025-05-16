package logic.model;

import logic.enums.LetterStatus;

import java.util.List;
import java.util.ArrayList;

public class Attempt {

    private final List<Letter> letters;

    public Attempt() {
        letters = new ArrayList<>();
    }

    /**
     * Создает попытку у которой все буквы в определенном статусе
     *
     * @param word   Слово
     * @param status Статус который будет указан у всех букв в попытке
     */
    public Attempt(String word, LetterStatus status) {
        this();
        for (char c : word.toCharArray()) {
            letters.add(new Letter(c, status));
        }
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