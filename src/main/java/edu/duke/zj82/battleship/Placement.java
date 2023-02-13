package edu.duke.zj82.battleship;

import static java.lang.Character.toUpperCase;

public class Placement {
    private final Coordinate where;
    private final char orientation;
    public Coordinate getWhere(){
        return this.where;
    }

    public char getOrientation() {
        return orientation;
    }

    // construct with a coordinate and direction
    public Placement(Coordinate coor, char dir){
        this.where = coor;
        this.orientation = toUpperCase(dir);
    }
    // construct with input string
    public Placement(String descr){
        Coordinate coor = new Coordinate(descr.substring(0,2));
        char dir = descr.charAt(2);
        this.where = coor;
        this.orientation = toUpperCase(dir);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Placement c = (Placement) o;
            return this.where.equals(c.where) && this.orientation == c.orientation;
        }
        return false;
    }

    @Override
    public String toString() {
        return "("+this.where.getRow()+", " + this.where.getColumn()+", "+this.orientation+")";
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
