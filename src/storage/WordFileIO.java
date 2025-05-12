package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordFileIO implements WordIOInterface {

    private final String fileName;
    private final Random random;

    public WordFileIO() {
        this("resources/wordle.txt");
    }

    public WordFileIO(String fileName) {
        this.random = new Random();
        this.fileName = fileName;
    }

    public WordIOResult<Boolean> search(String word) {
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (line.equals(word)) {
                    return new WordIOResult(true, true);
                }
            }
            return new WordIOResult(false, true);
        } catch (IOException e) {
            return new WordIOResult(false, false);
        }

    }

    public int getWordsCount() {
        int count = 0;
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            return -1;
        }
        return count;
    }

    public WordIOResult<String> getWordById(int id) {
        int position = 0;
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (id == position) {
                    return new WordIOResult(line, true);
                }
                position++;
            }
            return new WordIOResult(null, false);
        } catch (IOException e) {
            return new WordIOResult(null, false);
        }
    }

    public WordIOResult<String> getRandomWord() {
        int index = random.nextInt(getWordsCount());
        return getWordById(index);
    }
}