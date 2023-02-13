package edu.duke.zj82.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// this is the ship type for version 1 and sub or dd for version 2
public class RectangleShip<T> extends BasicShip<T>{
    private final String name;
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo) {
        super(makeCoords(upperLeft, width, height), myDisplayInfo,enemyDisplayInfo);
        this.name = name;
    }
    // this is the constructor for moving action, it will copy the information of hit on the original ship
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo, ArrayList<Boolean> hit) {
        super(makeCoords(upperLeft, width, height), myDisplayInfo,enemyDisplayInfo, hit);
        this.name = name;
    }
    // the following two constructors is the default format for version 1 and 2
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
        this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
                new SimpleShipDisplayInfo<T>(null, data));
    }
    public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit, ArrayList<Boolean> hit) {
        this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
                new SimpleShipDisplayInfo<T>(null, data), hit);
    }
    // this is a 1*1 ship for testing
    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this("testship", upperLeft, 1, 1, data, onHit);
    }
    //this is the function to make a hashset to store all coordinates of the ship
    static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
        HashSet<Coordinate> ans = new HashSet<Coordinate>();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Coordinate curr = new Coordinate(upperLeft.getRow()+j, upperLeft.getColumn()+i);
                ans.add(curr);
            }
        }
        return ans;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Coordinate> getBlocks() {
        return super.getBlocks();
    }
}
