package edu.duke.zj82.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T>{
    private final int width;
    private final int height;
    // list of ship
    final ArrayList<Ship<T>> myShips;
    // placement validity checking rule
    private final PlacementRuleChecker<T> placementChecker;
    // enemy miss fire record
    public HashSet<Coordinate> enemyMisses;
    // enemy hit record, used for version 2
    public HashMap<Coordinate,T> enemyHits;
    final T missInfo;
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    @Override
    public ArrayList<Ship<T>> getMyShips() {
        return this.myShips;
    }

    public HashSet<Coordinate> getEnemyMisses(){
        return this.enemyMisses;
    }
    public PlacementRuleChecker<T> getPlacementChecker() {
        return this.placementChecker;
    }

    public BattleShipBoard(int w, int h, PlacementRuleChecker<T> prc, T missInfo){
        this.missInfo = missInfo;
        if (w <= 0) {
            throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
        }
        if (h <= 0) {
            throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
        }
        this.width = w;
        this.height = h;
        this.myShips = new ArrayList<Ship<T>>();
        this.placementChecker = prc;
        this.enemyMisses = new HashSet<Coordinate>();
        this.enemyHits = new HashMap<Coordinate,T>();
    }
    // simple constructor for testing
    public BattleShipBoard(int w, int h) {
        this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)), null);
    }
    //record hit if fireAt's input is on the ship, record miss if missed
    @Override
    public Ship<T> fireAt(Coordinate c){
        for(Ship<T> s: myShips){
            if(s.occupiesCoordinates(c)){
                s.recordHitAt(c);
                enemyHits.put(c,s.getDisplayInfoAt(c,false));
                return s;
            }
        }
        enemyMisses.add(c);
        return null;
    }
    // get a ship based on part of ship, used for version 2
    @Override
    public Ship<T> locateAt(Coordinate c){
        for(Ship<T> s: myShips){
            if(s.occupiesCoordinates(c)){
                return s;
            }
        }
        return null;
    }
    // add ship to list if it passes the checking rules
    @Override
    public String tryAddShip(Ship<T> toAdd){
        if(placementChecker.checkPlacement(toAdd,this)!=null){
            return placementChecker.checkPlacement(toAdd,this);
        }
        myShips.add(toAdd);
        return null;
    }

    // display functions from own view and enemy's view
    @Override
    public T whatIsAtForSelf(Coordinate where) {
        return whatIsAt(where, true);
    }
    @Override
    public T whatIsAtForEnemy(Coordinate where) {
        if(enemyMisses.contains(where)){
            return missInfo;
        }
        if(enemyHits.containsKey(where)){
            return enemyHits.get(where);
        }
        return null;
    }
    protected T whatIsAt(Coordinate where, boolean isSelf){
        for (Ship<T> s: myShips) {
            if (s.occupiesCoordinates(where)){
                return s.getDisplayInfoAt(where, isSelf);
            }
        }
        return null;
    }

    // check if the game is end based on the ships status
    @Override
    public boolean checkStatus() {//false: still have ship; true: lose, all ship sunk
        for(Ship s : myShips){
            if(!s.isSunk()){
                return false;
            }
        }
        return true;
    }

    // move function, for version 2
    @Override
    public String tryMoveShip(Ship<T> toMove, Ship<T> target) {
        myShips.remove(toMove);
        return tryAddShip(target);
    }

    @Override
    public HashMap<Coordinate, T> getEnemyHits() {
        return enemyHits;
    }
}
