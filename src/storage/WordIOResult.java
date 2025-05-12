package storage;

public class WordIOResult<T> {
    T value;
    boolean status;

    public WordIOResult(T value, boolean status){
        this.value = value;
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public T getValue(){
        return this.value;
    }
}
