package edu.duke.zj82.battleship;

import static java.lang.Character.toUpperCase;

public class Coordinate {
    private final int row;
    private final int column;
    public int getRow(){
        return  this.row;
    }
    public int getColumn(){
        return this.column;
    }
    // construct with row and column number
    public Coordinate(int r, int c){
        this.row=r;
        this.column =c;
    }
    // construct with input string
    public Coordinate(String descr){
        char l = toUpperCase(descr.charAt(0));
        this.row = l - 'A';
        this.column = descr.charAt(1) - '0';
    }
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Coordinate c = (Coordinate) o;
            return row == c.row && column == c.column;
        }
        return false;
    }

    @Override
    public String toString() {
        return "("+row+", " + column+")";
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
