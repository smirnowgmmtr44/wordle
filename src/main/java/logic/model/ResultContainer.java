package main.java.logic.model;

public class ResultContainer<T> {
    T value;
    boolean status;

    public ResultContainer(T value, boolean status){
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
