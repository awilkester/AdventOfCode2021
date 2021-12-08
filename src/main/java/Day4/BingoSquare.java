package main.java.Day4;

public class BingoSquare {
    private boolean called = false;
    private int value;

    public BingoSquare(int value){
        this.value = value;
    }

    public void callSquare(){
        setCalled(true);
    }



    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
