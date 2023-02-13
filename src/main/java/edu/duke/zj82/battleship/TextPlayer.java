package edu.duke.zj82.battleship;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

import static java.lang.Character.toUpperCase;

// human player
public class TextPlayer extends BasicPlayer {
    // count for move action, version 2
    public int moveCount;
    // count for scan action, version 2
    public int scanCount;
    final ArrayList<String> shipsToPlace;

    public TextPlayer(Board<Character> theBoard, Reader inputSource, PrintStream out, String name) {
        super(theBoard,inputSource,out,name);
        this.shipsToPlace = new ArrayList<String>();
        setupShipCreationList();
        moveCount = 3;
        scanCount = 3;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public int getScanCount() {
        return this.scanCount;
    }

    public HashMap<String, Function<Placement, Ship<Character>>> getShipCreationFns() {
        return this.shipCreationFns;
    }

    // ships need to place list set up
    protected void setupShipCreationList(){
        shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
        shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
        shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
        shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
    }
    public PrintStream getOut() {
        return this.out;
    }

    public String getName() {
        return this.name;
    }
    public boolean isValidPlace(String s){//check the basic format for the placement for V1 and V2 sub and dd
        if(s.length()!=3){
            return false;
        }
        if(toUpperCase(s.charAt(0))<'A'||toUpperCase(s.charAt(0))>'Z'){
            return false;
        }
        if(s.charAt(1)<'0'||s.charAt(1)>'9'){
            return false;
        }
        if(toUpperCase(s.charAt(2))!='H'&&toUpperCase(s.charAt(2))!='V'){
            return false;
        }
        return true;
    }
    public boolean isValidPlaceForV2(String s){//check the basic format for the placement for V1 and V2 bb and cv
        if(s.length()!=3){
            return false;
        }
        if(toUpperCase(s.charAt(0))<'A'||toUpperCase(s.charAt(0))>'Z'){
            return false;
        }
        if(s.charAt(1)<'0'||s.charAt(1)>'9'){
            return false;
        }
        if(toUpperCase(s.charAt(2))!='U'&&toUpperCase(s.charAt(2))!='D'&&toUpperCase(s.charAt(2))!='R'&&toUpperCase(s.charAt(2))!='L'){
            return false;
        }
        return true;
    }
    public boolean isValidCoor(String s){//check the basic format for the coordinate
        if(s.length()!=2){
            return false;
        }
        if(toUpperCase(s.charAt(0))<'A'||toUpperCase(s.charAt(0))>'Z'){
            return false;
        }
        if(s.charAt(1)<'0'||s.charAt(1)>'9'){
            return false;
        }
        return true;
    }
    public String outOfBoundCoor(String s){// check if the fireAt parameter out of bound
        if(s.charAt(1)<'0'){
            return "That coordinate/placement is invalid: the coordinate goes off the left of the board.";
        } else if(s.charAt(1)>'0'+this.theBoard.getWidth()-1){
            return "That coordinate/placement is invalid: the coordinate goes off the right of the board.";
        } else if(toUpperCase(s.charAt(0))<'A'){
            return "That coordinate/placement is invalid: the coordinate goes off the top of the board.";
        } else if (toUpperCase(s.charAt(0))>'A'+this.theBoard.getHeight()-1){
            return "That coordinate/placement is invalid: the coordinate goes off the bottom of the board.";
        }
        return null;
    }
    // check if the input is valid for select actions
    public String validAction(String s){
        if(s.length()!=1){
            return "The input should be one character.";
        }
        if(moveCount!=0&&scanCount!=0){
            if(toUpperCase(s.charAt(0))!='F'&&toUpperCase(s.charAt(0))!='M'&&toUpperCase(s.charAt(0))!='S'){
                return "The input should be one of following: F, M, S";
            }
        } else if(moveCount!=0&&scanCount==0){
            if(toUpperCase(s.charAt(0))!='F'&&toUpperCase(s.charAt(0))!='M'){
                return "The input should be one of following: F, M";
            }
        } else if (moveCount==0&&scanCount!=0){
            if(toUpperCase(s.charAt(0))!='F'&&toUpperCase(s.charAt(0))!='S'){
                return "The input should be one of following: F, S";
            }
        }
        return null;
    }
    // read the user's input to create a placement object
    @Override
    public Placement readPlacement(String prompt, String name) throws IOException {
        out.println(prompt);
        String s = inputReader.readLine();
        if(Objects.equals(name, "Submarine") || Objects.equals(name, "Destroyer")){
            while(!isValidPlace(s)){
                out.println("That placement is invalid: it does not have the correct format.");
                out.println(prompt);
                s = inputReader.readLine();
            }
        } else {
            while(!isValidPlaceForV2(s)){
                out.println("That placement is invalid: it does not have the correct format.");
                out.println(prompt);
                s = inputReader.readLine();
            }
        }

        return new Placement(s);
    }
    // use the user's placement to try to add a ship to the board and display the board if success
    @Override
    public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
        Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?", shipName);
        Ship<Character> s = createFn.apply(p);
        while(theBoard.tryAddShip(s)!=null){
            out.println(theBoard.tryAddShip(s));
            p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?", shipName);
            s = createFn.apply(p);
        }
        out.print(view.displayMyOwnBoard());
    }
    // the whole process to place the ships on the board
    @Override
    public void doPlacementPhase() throws IOException{
        out.println(view.displayMyOwnBoard());
        out.println("Player "+name+": you are going to place the following ships (which are all\n" +
                "rectangular). For each ship, type the coordinate of the upper left\n" +
                "side of the ship, followed by either H (for horizontal) or V (for\n" +
                "vertical).  For example M4H would place a ship horizontally starting\n" +
                "at M4 and going to the right.  You have\n" +
                "\n" +
                "2 \"Submarines\" ships that are 1x2 \n" +
                "3 \"Destroyers\" that are 1x3\n" +
                "3 \"Battleships\" that are 2x3\n" +
                "2 \"Carriers\" that are 2x5");
        for(String s: this.shipsToPlace){
            doOnePlacement(s,this.shipCreationFns.get(s));
        }
    }
    // action function - fire
    public void doAttack(BasicPlayer enemy) throws IOException {
        out.println("Player "+name+": where do you want to attack?");
        String s = inputReader.readLine();
        while(!isValidCoor(s)){
            out.println("That coordinate is invalid: it does not have the correct format.");
            out.println("Player "+name+": where do you want to attack?");
            s = inputReader.readLine();
        }
        while(outOfBoundCoor(s)!=null){
            out.println(outOfBoundCoor(s));
            out.println("Player "+name+": where do you want to attack?");
            s = inputReader.readLine();
        }
        enemy.theBoard.fireAt(new Coordinate(s));
        out.println(this.view.displayMyBoardWithEnemyNextToIt(enemy.view, "Your Ocean", "Player "+enemy.name+ "'s Ocean"));
    }
    //get input from user for move (select toMove ship) and scan function
    public String getValidInputForMove(String s, String prompt) throws IOException {
        while(!isValidCoor(s)){
            out.println("That coordinate is invalid: it does not have the correct format.");
            out.println(prompt);
            s = inputReader.readLine();
        }
        while(outOfBoundCoor(s)!=null){
            out.println(outOfBoundCoor(s));
            out.println(prompt);
            s = inputReader.readLine();
        }
        return s;
    }
    // get input from user for move a ship (the place user want to move to)
    public String getValidInputForMovePlacement(String s, String prompt, boolean isRec) throws IOException {
        if(isRec){
            while(!isValidPlace(s)){
                out.println("That placement is invalid: it does not have the correct format.");
                out.println(prompt);
                s = inputReader.readLine();
            }
        } else{
            while(!isValidPlaceForV2(s)){
                out.println("That placement is invalid: it does not have the correct format.");
                out.println(prompt);
                s = inputReader.readLine();
            }
        }
        while(outOfBoundCoor(s)!=null){
            out.println(outOfBoundCoor(s));
            out.println(prompt);
            s = inputReader.readLine();
        }
        return s;
    }
    // action function - move
    public void moveShip(BasicPlayer enemy) throws IOException {
        V2ShipFactoryMoveHelper sf = new V2ShipFactoryMoveHelper();
        out.println("Player "+name+", which ship you want to move?");
        String s = inputReader.readLine();
        s = getValidInputForMove(s,"Player "+name+", which ship you want to move?");
        while(theBoard.locateAt(new Coordinate(s))==null){
            out.println("Current coordinate is not on a ship.");
            out.println("Player "+name+", which ship you want to move?");
            s = inputReader.readLine();
        }
        Ship<Character> toMove = theBoard.locateAt(new Coordinate(s));
        out.println("Player "+name+", where you want to move to?");
        String s1 = inputReader.readLine();
        s1 = getValidInputForMovePlacement(s1,"Player "+name+", where you want to move to?", toMove.getName().equals("Submarine") || toMove.getName().equals("Destroyer"));
        Ship<Character> target = sf.createShip(new Placement(s1),toMove);
        while(theBoard.tryMoveShip(toMove,target)!=null){
            out.println(theBoard.tryMoveShip(toMove,target));
            out.println("Player "+name+", where you want to move to?");
            s1 = inputReader.readLine();
            s1 = getValidInputForMovePlacement(s1,"Player "+name+", where you want to move to?", toMove.getName().equals("Submarine") || toMove.getName().equals("Destroyer"));
            target = sf.createShip(new Placement(s1),toMove);
        }
        out.println(this.view.displayMyBoardWithEnemyNextToIt(enemy.view, "Your Ocean", "Player "+enemy.name+ "'s Ocean"));
        moveCount--;
    }
    // count in the area, each ship's occupations
    public void tryScan(Coordinate c, BasicPlayer enemy){
        int leftBound = 0;
        int rightBound = 0;
        int sub = 0;
        int dd = 0;
        int bb = 0;
        int cv = 0;
        for(int begin = -3;begin<4;begin++){
            for(int i = leftBound; i <= rightBound; i++){
                char x = (char) ('A'+c.getRow()+begin);
//                System.out.println("("+(c.getColumn()+i)+", "+x+")");
                if(c.getRow()+begin<0||c.getRow()+begin>=enemy.theBoard.getHeight()) continue;
                if(c.getColumn()+i<0||c.getColumn()+i>=enemy.theBoard.getWidth()) continue;
                Coordinate curr = new Coordinate(c.getRow()+begin, c.getColumn()+i);
                if(enemy.theBoard.whatIsAtForSelf(curr)==null) continue;
                else if(enemy.theBoard.whatIsAtForSelf(curr)=='s') sub++;
                else if (enemy.theBoard.whatIsAtForSelf(curr)=='d') dd++;
                else if (enemy.theBoard.whatIsAtForSelf(curr)=='b') bb++;
                else if (enemy.theBoard.whatIsAtForSelf(curr)=='c') cv++;
            }
            if(begin<0){
                leftBound--;
                rightBound++;
            } else {
                leftBound++;
                rightBound--;
            }
        }
        out.println("Submarines occupy "+sub+" squares");
        out.println("Destroyers occupy "+dd+" squares");
        out.println("Battleships occupy "+bb+" squares");
        out.println("Carriers occupy "+cv+" squares");
    }
    // action function - scan
    public void doScan(BasicPlayer enemy) throws IOException {
        out.println("Player "+name+", where you want to scan?");
        String s = inputReader.readLine();
        s = getValidInputForMove(s,"Player "+name+", where you want to scan?");
        tryScan(new Coordinate(s), enemy);
        scanCount--;
    }
    // player's turn main function, can do fire, move, scan if the count for move and scan are not 0
    // if both counts are 0, the player can only attack
    @Override
    public boolean playOneTurn(BasicPlayer enemy) throws IOException {
        out.println(this.view.displayMyBoardWithEnemyNextToIt(enemy.view, "Your Ocean", "Player "+enemy.name+ "'s Ocean"));
        if(moveCount!=0||scanCount!=0){
            out.println("Possible actions for Player "+name+":\n" +
                    "\n" +
                    " F Fire at a square\n" +
                    " M Move a ship to another square ("+moveCount+" remaining)\n" +
                    " S Sonar scan ("+scanCount+" remaining)\n" +
                    "\n" +
                    "Player "+name+", what would you like to do?");
            String choice = inputReader.readLine();
            while(validAction(choice)!=null){
                out.println(validAction(choice));
                out.println("Possible actions for Player "+name+":\n" +
                        "\n" +
                        " F Fire at a square\n" +
                        " M Move a ship to another square ("+moveCount+" remaining)\n" +
                        " S Sonar scan ("+scanCount+" remaining)\n" +
                        "\n" +
                        "Player "+name+", what would you like to do?");
                choice = inputReader.readLine();
            }
            if(toUpperCase(choice.charAt(0))=='F'){
                doAttack(enemy);
            } else if(toUpperCase(choice.charAt(0))=='M'){
                moveShip(enemy);
            } else {
                doScan(enemy);
            }
        } else {
            doAttack(enemy);
        }
        if(enemy.theBoard.checkStatus()){
            out.println("Player "+this.name+" wins.");
            return true;
        }
        return false;
    }
}
