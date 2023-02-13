package edu.duke.zj82.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

// this is a computer player class for version 2
// the computer player can only attack
// the computer player's boards are same every single game
public class ComputerPlayer extends BasicPlayer {
    int row;
    int col;
    public ComputerPlayer(Board<Character> theBoard, Reader inputSource, PrintStream out, String name) {
        super(theBoard,inputSource,out,name);
        row = 0;
        col = 0;
    }
    // form the board with ships
    @Override
    public void doPlacementPhase(){
        Ship<Character> s1 = shipFactory.makeSubmarine(new Placement("A0H"));
        Ship<Character> s2 = shipFactory.makeSubmarine(new Placement("B0H"));
        Ship<Character> s3 = shipFactory.makeDestroyer(new Placement("C0V"));
        Ship<Character> s4 = shipFactory.makeDestroyer(new Placement("C1V"));
        Ship<Character> s5 = shipFactory.makeDestroyer(new Placement("A2H"));
        Ship<Character> s6 = shipFactory.makeBattleship(new Placement("L0u"));
        Ship<Character> s7 = shipFactory.makeBattleship(new Placement("l3u"));
        Ship<Character> s8 = shipFactory.makeBattleship(new Placement("n0u"));
        Ship<Character> s9 = shipFactory.makeCarrier(new Placement("j8u"));
        Ship<Character> s10 = shipFactory.makeCarrier(new Placement("s0r"));
        this.theBoard.tryAddShip(s1);
        this.theBoard.tryAddShip(s2);
        this.theBoard.tryAddShip(s3);
        this.theBoard.tryAddShip(s4);
        this.theBoard.tryAddShip(s5);
        this.theBoard.tryAddShip(s6);
        this.theBoard.tryAddShip(s7);
        this.theBoard.tryAddShip(s8);
        this.theBoard.tryAddShip(s9);
        this.theBoard.tryAddShip(s10);
    }
    // fire
    public void doAttack(BasicPlayer enemy, int r, int c){
        enemy.theBoard.fireAt(new Coordinate(r,c));
    }
    // one turn movement
    @Override
    public boolean playOneTurn(BasicPlayer enemy){
        doAttack(enemy, row, col);
        if(col == theBoard.getWidth()-1){
            col = 0;
            row++;
        } else {
            col++;
        }
        if(enemy.theBoard.checkStatus()){
            out.println("Player "+this.name+" wins.");
            return true;
        }
        return false;
    }

    // the following two functions won't be used by computer player but for test cases of BasicPlayer, these have to be
    // implemented
    @Override
    public Placement readPlacement(String prompt, String name) throws IOException {
        return null;
    }

    @Override
    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    }
}
