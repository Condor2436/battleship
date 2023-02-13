package edu.duke.zj82.battleship;


import org.w3c.dom.Text;

import java.io.*;

public class App {
    public BasicPlayer player1;
    public BasicPlayer player2;
    public boolean end;

    // program constructor
    public App(Board<Character> b1, Board<Character> b2, Reader inputSource, PrintStream out, int type) {
        // type 0: player vs player
        // type 1: player vs computer
        // type 2: computer vs player
        // type 3: computer vs computer
        if(type == 0){
            this.player1 = new TextPlayer(b1,inputSource,out,"A");
            this.player2 = new TextPlayer(b2,inputSource,out,"B");
        } else if (type == 1){
            this.player1 = new TextPlayer(b1,inputSource,out,"A");;
            this.player2 = new ComputerPlayer(b2,inputSource,out,"B");
        } else if (type == 2){
            this.player1 = new ComputerPlayer(b1,inputSource,out,"A");
            this.player2 = new TextPlayer(b2,inputSource,out,"B");
        } else {
            this.player1 = new ComputerPlayer(b1,inputSource,out,"A");
            this.player2 = new ComputerPlayer(b2,inputSource,out,"B");
        }

        end = false;
    }

    // make the board
    public void doPlacementPhase() throws IOException {
        player1.doPlacementPhase();
        player2.doPlacementPhase();
    }
    // play the turn
    public void doAttackingPhase() throws IOException{
        while(!end){
            end = player1.playOneTurn(player2);
            if(end) break;
            end = player2.playOneTurn(player1);
        }
    }


    public static void main(String[] args) throws IOException{
        Board<Character> b1 = new BattleShipBoard<Character>(10,20,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        Board<Character> b2 = new BattleShipBoard<Character>(10,20,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        InputStreamReader sr = new InputStreamReader(System.in);
        System.out.println("Which model you want to play?\n" +
                "Model 1: Player vs Player\n" +
                "Model 2: Player vs Computer\n" +
                "Model 3: Computer vs Player\n" +
                "Model 4: Computer vs Computer");
        BufferedReader temp = new BufferedReader(sr);
        String s = temp.readLine();
        while(s.length()!=1||(s.charAt(0)!='1'&&s.charAt(0)!='2'&&s.charAt(0)!='3'&&s.charAt(0)!='4')){
            System.out.println("Your input is not in correct format, try again");
            System.out.println("Which model you want to play?\n" +
                    "Model 1: Player vs Player\n" +
                    "Model 2: Player vs Computer\n" +
                    "Model 3: Computer vs Player\n" +
                    "Model 4: Computer vs Computer");
            s= temp.readLine();
        }
        int type = s.charAt(0)-'1';
        App app = new App(b1,b2,sr,System.out,type);
        app.doPlacementPhase();
        app.doAttackingPhase();
    }
}

