package edu.duke.zj82.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
    // check each coordinate in the input ship whether all points are inside the board
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        for(Coordinate c: theShip.getCoordinates()){
            if(c.getColumn()<0||c.getColumn()>=theBoard.getWidth()||c.getRow()<0||c.getRow()>=theBoard.getHeight()){
                if(c.getColumn()<0){
                    return new String("That placement is invalid: the ship goes off the left of the board.");
                } else if(c.getColumn()>=theBoard.getWidth()){
                    return new String("That placement is invalid: the ship goes off the right of the board.");
                } else if(c.getRow()<0){
                    return new String("That placement is invalid: the ship goes off the top of the board.");
                } else {
                    return new String("That placement is invalid: the ship goes off the bottom of the board.");
                }
            }
        }
        return null;
    }
    public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
}
