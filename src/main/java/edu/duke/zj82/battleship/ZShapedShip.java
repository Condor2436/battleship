package edu.duke.zj82.battleship;

import java.util.ArrayList;
import java.util.HashSet;

// carrier for version 2
public class ZShapedShip<T> extends BasicShip<T>{
    private final String name;
    // record the type of ship
    private final char type;
    // common constructor
    public ZShapedShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo, char type) {
        super(makeCoords(upperLeft, width, height, type), myDisplayInfo,enemyDisplayInfo);
        this.name = name;
        this.type = type;
    }
    // constructor for move action
    public ZShapedShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo, char type, ArrayList<Boolean> hit) {
        super(makeCoords(upperLeft, width, height, type), myDisplayInfo,enemyDisplayInfo, hit);
        this.name = name;
        this.type = type;
    }
    //default constructors for this project
    public ZShapedShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit, char type) {
        this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
                new SimpleShipDisplayInfo<T>(null, data), type);
    }
    public ZShapedShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit, char type, ArrayList<Boolean> hit) {
        this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
                new SimpleShipDisplayInfo<T>(null, data), type, hit);
    }
    @Override
    public String getName() {
        return this.name;
    }
    // make the list of Coordinate with a fix order, same as hitted list order, help move action to record the ship status
    static ArrayList<Coordinate> makeCoords(Coordinate upperLeft, int width, int height, char type){
        ArrayList<Coordinate> ans = new ArrayList<Coordinate>();
        if(type=='U'){
            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++) {
                    if((i==0&&j==4)||(i==1&&j==0)||(i==1&&j==1)) continue;
                    Coordinate curr = new Coordinate(upperLeft.getRow()+j, upperLeft.getColumn()+i);
//                    System.out.println(curr.toString());
                    ans.add(curr);
                }
            }
        } else if(type=='R'){
            for(int j = 0; j < height; j++){
                for(int i = width-1; i >=0; i--) {
                    if((i==0&&j==0)||(i==3&&j==1)||(i==4&&j==1)) continue;
                    Coordinate curr = new Coordinate(upperLeft.getRow()+j, upperLeft.getColumn()+i);
//                    System.out.println(curr.toString());
                    ans.add(curr);
                }
            }
        } else if (type=='D'){
            for(int i = width-1; i >=0; i--){
                for(int j = height-1; j >=0; j--) {
                    if((i==0&&j==3)||(i==0&&j==4)||(i==1&&j==0)) continue;
                    Coordinate curr = new Coordinate(upperLeft.getRow()+j, upperLeft.getColumn()+i);
//                    System.out.println(curr.toString());
                    ans.add(curr);
                }
            }
        } else {
            for(int j = height-1; j >=0; j--){
                for(int i = 0; i < width; i++) {
                    if((i==0&&j==0)||(i==1&&j==0)||(i==4&&j==1)) continue;
                    Coordinate curr = new Coordinate(upperLeft.getRow()+j, upperLeft.getColumn()+i);
//                    System.out.println(curr.toString());
                    ans.add(curr);
                }
            }
        }
        return ans;
    }
}
