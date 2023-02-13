package edu.duke.zj82.battleship;

public interface AbstractShipFactory<T> {
    // functions to make different types of ships
    public Ship<T> makeSubmarine(Placement where);
    public Ship<T> makeBattleship(Placement where);
    public Ship<T> makeCarrier(Placement where);
    public Ship<T> makeDestroyer(Placement where);
}
