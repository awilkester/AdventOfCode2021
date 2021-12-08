package main.java.Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BingoBoard {
    private BingoSquare[][] board;
    private boolean bingo = false;
    private int total = 0;

    public BingoBoard(BingoSquare[][] board) {
        this.board = board;
        this.total = calcTotal();

    }
    public BingoBoard(){
    }

    public int getUncalledTotal(){
        int total = 0;
        for(BingoSquare[] x : getBoard()){
            for(BingoSquare y : x){
                if(!y.isCalled()){
                    total += y.getValue();
                }
            }
        }
        return total;
    }

    public int calcTotal(){
        int total = 0;
        for(BingoSquare[] x : getBoard()){
            for(BingoSquare y : x){
                total += y.getValue();
            }
        }
        return total;
    }

    public void callNumber(int call){
        for(BingoSquare[] x : getBoard()){
            for(BingoSquare y : x){
                if(y.getValue() == call){
                    y.callSquare();
                    setTotal(getTotal() - call);
                }
//                if(checkBingo()){
//                    setBingo(true);
//                }
            }
        }
    }

    public boolean checkBingo(){
        return (checkBingoColumns(getColumns(getBoard())) || checkBingoRows(getRows(getBoard())));
    }

    public boolean checkBingoColumns(List<BingoSquare[]> columns){
        return checkBingoRows(columns);
    }

    public List<BingoSquare[]> getColumns(BingoSquare[][] board){
        List<BingoSquare[]> result = new ArrayList<>(5);
        BingoSquare[] column = new BingoSquare[5];
        for(int j = 0; j < 5; j++){
            for(int i=0; i<column.length; i++){
                column[i] = board[i][j];
            }
            result.add(column);
        }
        return result;
    }

    public List<BingoSquare[]> getRows(BingoSquare[][] board){
        List<BingoSquare[]> result = null;
        for(BingoSquare[] row : board){
            result.add(row);
        }
        return result;
    }

    public boolean checkBingoRows(List<BingoSquare[]> rows){
        boolean bingo = false;
        for(BingoSquare[] x : rows){
            if(Arrays.stream(x).allMatch(BingoSquare::isCalled)){
                bingo = true;
            }
        }
        return bingo;
    }

//    public boolean checkBingoRows(){
//        boolean bingo = false;
//        for(BingoSquare[] x : getBoard()){
//            if(Arrays.stream(x).allMatch(BingoSquare::isCalled)){
//                bingo = true;
//            }
//        }
//        return bingo;
//    }

    public BingoSquare[][] getBoard() {
        return board;
    }

    public boolean isBingo() {
        return bingo;
    }

    public void setBingo(boolean bingo) {
        this.bingo = bingo;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
