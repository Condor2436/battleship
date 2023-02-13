package edu.duke.zj82.battleship;

import java.util.function.Function;

public class BoardTextView {
    private final Board<Character> toDisplay;
    public BoardTextView(Board<Character> toDisplay){
        this.toDisplay = toDisplay;
        if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
            throw new IllegalArgumentException(
                    "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
        }
    }
    // functions to get the board
    public String displayMyOwnBoard() {
        return  displayAnyBoard(toDisplay::whatIsAtForSelf);
    }
    public String displayEnemyBoard() {
        return  displayAnyBoard(toDisplay::whatIsAtForEnemy);
    }
    protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn){
        StringBuilder ans= new StringBuilder();
        ans.append(makeHeader());
        for(int i = 0; i < toDisplay.getHeight(); i++){
            ans.append(makeBody(i, getSquareFn));
        }
        ans.append(makeHeader());
        return ans.toString();
    }
    // make the head of the board
    public String makeHeader() {
        StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
        String sep=""; //start with nothing to separate, then switch to | to separate
        for (int i = 0; i < toDisplay.getWidth(); i++) {
            ans.append(sep);
            ans.append(i);
            sep = "|";
        }
        ans.append("\n");
        return ans.toString();
    }
    // make the body part of the board
    public String makeBody(int line, Function<Coordinate, Character> getSquareFn){
        char l = (char) ('A' + line);
        Character c = l;
        String letter = c.toString();
        String sep ="";
        StringBuilder ans = new StringBuilder(letter+" ");
        for(int i = 0; i < toDisplay.getWidth();i++){
            ans.append(sep);
            Coordinate temp = new Coordinate(line, i);
            if(getSquareFn.apply(temp)==null){
                ans.append(" ");
            } else {
                ans.append(getSquareFn.apply(temp));
            }
            sep = "|";
        }
        ans.append(" ").append(letter);
        ans.append("\n");
        return ans.toString();
    }
    // display the two board next to each other
    public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader){
        StringBuilder header = new StringBuilder(" ".repeat(5)).append(myHeader);
        int len = header.toString().length();
        header.append(" ".repeat(2*toDisplay.getWidth()+22-len));
        header.append(enemyHeader).append("\n");
        StringBuilder body = new StringBuilder();
        String[] myBoard = this.displayMyOwnBoard().split("\n");
        String[] yourBoard = enemyView.displayEnemyBoard().split("\n");
        for(int i = 0; i < myBoard.length; i++){
            if(i == 0 || i == myBoard.length-1){
                body.append(myBoard[i]).append(" ".repeat(18)).append(yourBoard[i]).append("\n");
            } else {
                body.append(myBoard[i]).append(" ".repeat(16)).append(yourBoard[i]).append("\n");
            }
        }
        return header.toString() + body.toString();
    }
}
