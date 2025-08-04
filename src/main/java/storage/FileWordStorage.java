package storage;

import logic.model.ResultContainer;
import logic.storage.WordStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class FileWordStorage implements WordStorage {

    private final String fileName;
    private final Random random;

    public FileWordStorage() {
        this("/wordle.txt");
    }

    public FileWordStorage(String fileName) {
        this.random = new Random();
        this.fileName = fileName;
    }

    public ResultContainer<Boolean> search(String word) {
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(FileWordStorage.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (line.equals(word)) {
                    return new ResultContainer(true, true);
                }
            }
            return new ResultContainer(false, true);
        } catch (IOException e) {
            return new ResultContainer(false, false);
        }

    }

    public int getWordsCount() {
        int count = 0;
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(FileWordStorage.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = bw.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            return -1;
        }
        return count;
    }

    public ResultContainer<String> getWordById(int id) {
        int position = 0;
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(FileWordStorage.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (id == position) {
                    return new ResultContainer(line, true);
                }
                position++;
            }
            return new ResultContainer(null, false);
        } catch (IOException e) {
            return new ResultContainer(null, false);
        }
    }

    public ResultContainer<String> getRandomWord() {
        int index = random.nextInt(getWordsCount());
        return getWordById(index);
    }
}