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

    public boolean search(String word) throws IOException {
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (line.equals(word)) {
                    return true;
                }
            }
            throw new IOException();
        }

    }

    public int getWordsCount() throws IOException {
        int count = 0;
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            throw new IOException("Can't get word count");
        }
        return count;
    }

    public String getWordById(int id) throws IOException {
        int position = 0;
        try (BufferedReader bw = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = bw.readLine()) != null) {
                if (id == position) {
                    return line;
                }
                position++;
            }
            throw new IOException("Can't get word from storage");
        }
    }

    public String getRandomWord() throws IOException {
        int index = random.nextInt(getWordsCount());
        return getWordById(index);
    }
}