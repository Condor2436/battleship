package edu.duke.zj82.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T>{
    public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
        super(next);
    }
    //check if the input ship overlay on other ships
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        for(Coordinate c : theShip.getCoordinates()){
            if(theBoard.whatIsAtForSelf(c)!=null){
                return new String("That placement is invalid: the ship overlaps another ship.");
            }
        }
        return null;
    }
}
