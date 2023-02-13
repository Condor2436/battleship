package edu.duke.zj82.battleship;

public interface ShipDisplayInfo<T> {
    // get corresponding display information
    public T getInfo(Coordinate where, boolean hit);
}
