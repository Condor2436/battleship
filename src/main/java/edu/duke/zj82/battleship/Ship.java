package edu.duke.zj82.battleship;

import java.util.ArrayList;

public interface Ship<T> {
    // check if the input is a part of ship
    public boolean occupiesCoordinates(Coordinate where);
    // check ship's coordinate. whether all hit or at least one still alive
    public boolean isSunk();
    public void recordHitAt(Coordinate where);
    // check if the input coordinate is hit
    public boolean wasHitAt(Coordinate where);
    // get the display for print out use
    public T getDisplayInfoAt(Coordinate where, boolean myShip);
    public String getName();
    // get all coordinate with status of the ship
    public Iterable<Coordinate> getCoordinates();

    ArrayList<Boolean> getHitted();
    ArrayList<Coordinate> getBlocks();
}
