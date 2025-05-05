package logic;

public class WordNotFoundException extends Exception {

    public WordNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
