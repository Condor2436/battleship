package edu.duke.zj82.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

// BasicPlayer class is the base of TextPlayer and ComputerPlayer
public abstract class BasicPlayer {
    public final Board<Character> theBoard;
    public final BoardTextView view;
    public final BufferedReader inputReader;
    public final PrintStream out;
    final AbstractShipFactory<Character> shipFactory;
    public String name;
    final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
    public BasicPlayer(Board<Character> theBoard, Reader inputSource, PrintStream out, String name){
        this.theBoard = theBoard;
        this.view = new BoardTextView(theBoard);
        this.inputReader = new BufferedReader(inputSource);
        this.out = out;
        this.shipFactory = new V2ShipFactory();
        this.name = name;
        this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
        setupShipCreationMap();
    }
    // shipFactory set up function
    protected void setupShipCreationMap(){
        this.shipCreationFns.put("Submarine", shipFactory::makeSubmarine);
        this.shipCreationFns.put("Destroyer", shipFactory::makeDestroyer);
        this.shipCreationFns.put("Battleship", shipFactory::makeBattleship);
        this.shipCreationFns.put("Carrier", shipFactory::makeCarrier);
    }
    // since in the program, either TextPlayer or ComputerPlayer need this function, it is stated in this class and override in TextPlayer and ComputerPlayer
    public boolean playOneTurn(BasicPlayer enemy) throws IOException{
        return false;
    }

    public AbstractShipFactory<Character> getShipFactory() {
        return shipFactory;
    }
    // override for testing
    public abstract Placement readPlacement(String prompt, String name) throws IOException;

    // override for testing
    public abstract void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException;

    public HashMap<String, Function<Placement, Ship<Character>>> getShipCreationFns() {
        return shipCreationFns;
    }
    // since in the program, either TextPlayer or ComputerPlayer need this function, it is stated in this class and override in TextPlayer and ComputerPlayer
    public abstract void doPlacementPhase() throws IOException;
}
