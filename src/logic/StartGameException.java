package logic;

public class StartGameException extends Exception {
    public StartGameException(String errorMessage) {
        super(errorMessage);
    }
    public StartGameException() {
        super();
    }
}
