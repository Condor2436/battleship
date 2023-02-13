package edu.duke.zj82.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Board<T> {
    public int getWidth();
    public int getHeight();
    // function to add ship to the board, if return is not null, means add fail
    public String tryAddShip(Ship<T> toAdd);
    public T whatIsAtForSelf(Coordinate where);

    ArrayList<Ship<T>> getMyShips();

    public Ship<T> fireAt(Coordinate c);
    public Ship<T> locateAt(Coordinate c);
    T whatIsAtForEnemy(Coordinate where);

    public boolean checkStatus();
    public String tryMoveShip(Ship<T> toMove, Ship<T> target);

    HashMap<Coordinate, T> getEnemyHits();
}
