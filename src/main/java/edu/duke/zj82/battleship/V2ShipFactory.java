package edu.duke.zj82.battleship;

import java.util.Objects;
// version 2 create ship functions
public class V2ShipFactory implements AbstractShipFactory<Character>{
    // change way to create ship based on the type of ship
    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
        if(Objects.equals(name, "Submarine") || Objects.equals(name, "Destroyer")){
            if(where.getOrientation()=='H'){
                return new RectangleShip<Character>(name,where.getWhere(),h,w,letter,'*');
            } else {
                return new RectangleShip<Character>(name,where.getWhere(),w,h,letter,'*');
            }
        } else if(Objects.equals(name, "Battleship")){
            if(where.getOrientation()=='U'||where.getOrientation()=='D'){
                return new TShapedShip<Character>(name,where.getWhere(),h,w,letter,'*', where.getOrientation());
            } else {
                return new TShapedShip<Character>(name,where.getWhere(),w,h,letter,'*', where.getOrientation());
            }
        } else {
            if(where.getOrientation()=='U'||where.getOrientation()=='D'){
                return new ZShapedShip<Character>(name,where.getWhere(),w,h,letter,'*', where.getOrientation());
            } else {
                return new ZShapedShip<Character>(name,where.getWhere(),h,w,letter,'*', where.getOrientation());
            }
        }
    }
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "Submarine");
    }

    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 2, 3, 'b', "Battleship");
    }

    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 2, 5, 'c', "Carrier");
    }

    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 3, 'd', "Destroyer");
    }
}
