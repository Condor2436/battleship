package edu.duke.zj82.battleship;

import java.util.Objects;

import static java.lang.Character.toLowerCase;

// move action - special functions to create copy ship
public class V2ShipFactoryMoveHelper{
    public Ship<Character> createShip(Placement where, Ship<Character> toMove){
        int w = 0;
        int h = 0;
        if(toMove.getName().equals("Submarine")){
            w=1;
            h=2;
        } else if(toMove.getName().equals("Destroyer")){
            w=1;
            h=3;
        } else if(toMove.getName().equals("Battleship")){
            w=2;
            h=3;
        } else{
            w=2;
            h=5;
        }
        if(toMove.getName().equals("Submarine") || toMove.getName().equals("Destroyer")){
            if(where.getOrientation()=='H'){
                return new RectangleShip<Character>(toMove.getName(),where.getWhere(),h,w,toLowerCase(toMove.getName().charAt(0)),'*',toMove.getHitted());
            } else {
                return new RectangleShip<Character>(toMove.getName(),where.getWhere(),w,h,toLowerCase(toMove.getName().charAt(0)),'*',toMove.getHitted());
            }
        } else if(toMove.getName().equals("Battleship")){
            if(where.getOrientation()=='U'||where.getOrientation()=='D'){
                return new TShapedShip<Character>(toMove.getName(),where.getWhere(),h,w,toLowerCase(toMove.getName().charAt(0)),'*', where.getOrientation(),toMove.getHitted());
            } else {
                return new TShapedShip<Character>(toMove.getName(),where.getWhere(),w,h,toLowerCase(toMove.getName().charAt(0)),'*', where.getOrientation(),toMove.getHitted());
            }
        } else {
            if(where.getOrientation()=='U'||where.getOrientation()=='D'){
                return new ZShapedShip<Character>(toMove.getName(),where.getWhere(),w,h,toLowerCase(toMove.getName().charAt(0)),'*', where.getOrientation(),toMove.getHitted());
            } else {
                return new ZShapedShip<Character>(toMove.getName(),where.getWhere(),h,w,toLowerCase(toMove.getName().charAt(0)),'*', where.getOrientation(),toMove.getHitted());
            }
        }
    }
}
