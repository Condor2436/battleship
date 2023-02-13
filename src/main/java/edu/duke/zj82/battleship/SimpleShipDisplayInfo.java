package edu.duke.zj82.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
    // display info for normal ship coordinate
    private T myData;
    // display info for hit ship coordinate
    private T onHit;
    public SimpleShipDisplayInfo(T myData, T onHit){
        this.myData = myData;
        this.onHit = onHit;
    }
    @Override
    public T getInfo(Coordinate where, boolean hit) {
        return hit?this.onHit:this.myData;
    }
}
