package edu.duke.zj82.battleship;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T>{
    // store the coordinate with its hit status, this is useful in version 1, but in version 2, since there is move action
    // for the player, therefore, the way to display has to be changed
    protected HashMap<Coordinate, Boolean> myPieces;
    //This is the display information that show in the player's view, what does the board looks like
    protected ShipDisplayInfo<T> myDisplayInfo;
    //This is the display information that show in the enemy's view, what does the board looks like
    protected ShipDisplayInfo<T> enemyDisplayInfo;
    public HashMap<Coordinate, Boolean> getMyPieces() {
        return myPieces;
    }
    // this is the list to record the hit status in a fix order, this order is corresponding to list blocks
    // used in version 2
    protected ArrayList<Boolean> hitted;

    public ArrayList<Boolean> getHitted() {
        return hitted;
    }
    // this is the list to record the coordinate in a fix order, this order is corresponding to the list hitted
    // used in version 2
    protected ArrayList<Coordinate> blocks;

    public ArrayList<Coordinate> getBlocks() {
        return blocks;
    }

    public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
        myPieces = new HashMap<Coordinate, Boolean>();
        hitted = new ArrayList<Boolean>();
        blocks = new ArrayList<Coordinate>();
        // initialize the list to record coordinate and hit status
        for(Coordinate c : where){
            myPieces.put(c,false);
            hitted.add(false);
            blocks.add(c);
        }
        this.myDisplayInfo = myDisplayInfo;
        this.enemyDisplayInfo = enemyDisplayInfo;
    }
    // an override constructor that when the user try to move the ship, this constructor will make the copy of the moving ship
    // the hit status is copied to the new ship
    public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo, ArrayList<Boolean> hit) {
        myPieces = new HashMap<Coordinate, Boolean>();
        hitted = new ArrayList<Boolean>();
        blocks = new ArrayList<Coordinate>();
        int index = 0;
        // this is why hitted list and blocks list has a fix order
        // by using the fix order, the hit status can be copied correctly
        for(Coordinate c : where){
//            System.out.println(c.toString());
            myPieces.put(c,hit.get(index));
            hitted.add(hit.get(index));
            blocks.add(c);
            index++;
        }
//        for(Coordinate b: blocks){
//            System.out.println(b.toString());
//        }
        this.myDisplayInfo = myDisplayInfo;
        this.enemyDisplayInfo = enemyDisplayInfo;
    }
    protected void checkCoordinateInThisShip(Coordinate c) {
        if(!occupiesCoordinates(c)){
            throw new IllegalArgumentException("The input coordinate is not on the ship");
        }
    }
    // check if the input coordinate is on the ship
    @Override
    public boolean occupiesCoordinates(Coordinate where) {
        if(myPieces.containsKey(where)){
            return true;
        }
        return false;
    }

    // this is function isSunk() for version 2
    @Override
    public boolean isSunk() {
        return !hitted.contains(false);
    }

    // this is the function to record hit on ship
    @Override
    public void recordHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        myPieces.replace(where,false,true);
        hitted.set(blocks.indexOf(where), true);
    }
    //this is the function to check if the input coordinate is hit
    // this function is modified for version 2
    @Override
    public boolean wasHitAt(Coordinate where) {
        checkCoordinateInThisShip(where);
        return hitted.get(blocks.indexOf(where));
    }

    // this function return the display information based on who want to see it
    @Override
    public T getDisplayInfoAt(Coordinate where, boolean myShip) {
        if(myShip){
            checkCoordinateInThisShip(where);
            return myDisplayInfo.getInfo(where, wasHitAt(where));
        } else {
            checkCoordinateInThisShip(where);
            return enemyDisplayInfo.getInfo(where,wasHitAt(where));
        }
    }
    @Override
    public Iterable<Coordinate> getCoordinates(){
        return myPieces.keySet();
    }
}
