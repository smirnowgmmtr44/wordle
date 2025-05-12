package logic;

public class NullTargetWordException extends RuntimeException {
    public NullTargetWordException(String errorMessage) {
        super(errorMessage);
    }
    public NullTargetWordException() {
        super();
    }
}
