package edu.duke.zj82.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character>{
    // version 1 create ship functions
    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
        if(where.getOrientation()=='H'){
            return new RectangleShip<Character>(name,where.getWhere(),h,w,letter,'*');
        } else {
            return new RectangleShip<Character>(name,where.getWhere(),w,h,letter,'*');
        }
    }
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "Submarine");
    }

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 1, 4, 'b', "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 1, 6, 'c', "Carrier");
    }

    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 3, 'd', "Destroyer");
    }
}
