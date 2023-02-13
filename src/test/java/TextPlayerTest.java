import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextPlayerTest {
    private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
        BufferedReader input = new BufferedReader(new StringReader(inputData));
        PrintStream output = new PrintStream(bytes, true);
        Board<Character> board = new BattleShipBoard<Character>(w, h);
        return new TextPlayer(board, input, output, "A");
    }
    @Test
    void test_read_placement() throws IOException {
        StringReader sr = new StringReader("B2V\nC8H\na4v\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
        Board<Character> b2 = new BattleShipBoard<Character>(10, 20);
        App app = new App(b1,b2, sr, ps,0);
        String prompt = "Player " + app.player1.name + " where do you want to place a Destroyer?";
        Placement[] expected = new Placement[3];
        expected[0] = new Placement(new Coordinate(1, 2), 'V');
        expected[1] = new Placement(new Coordinate(2, 8), 'H');
        expected[2] = new Placement(new Coordinate(0, 4), 'V');
        for (int i = 0; i < expected.length; i++) {
            Placement p = app.player1.readPlacement(prompt, "Destroyer");
            assertEquals(p, expected[i]); //did we get the right Placement back
            assertEquals(prompt + System.lineSeparator(), bytes.toString()); //should have printed prompt and newline
            bytes.reset(); //clear out bytes for next time around
        }
    }

    @Test
    void test_onePlace() throws IOException{
        StringReader sr = new StringReader("B2V\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
        Board<Character> b2 = new BattleShipBoard<Character>(10, 20);
        App app = new App(b1,b2, sr, ps,0);
        app.player1.doOnePlacement("Destroyer", app.player1.getShipCreationFns().get("Destroyer"));
        String expected="Player "+app.player1.name+" where do you want to place a " + "Destroyer" + "?"+System.lineSeparator()+
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A  | | | | | | | | |  A\n" +
                "B  | |d| | | | | | |  B\n" +
                "C  | |d| | | | | | |  C\n" +
                "D  | |d| | | | | | |  D\n" +
                "E  | | | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n"  ;
        assertEquals(expected, bytes.toString());
    }
    @Test
    public void test_doPlacementPhase() throws IOException {
        StringReader sr = new StringReader("A0H\n" +
                "B0H\n" +
                "C0V\n" +
                "C1V\n" +
                "A2H\n" +
                "L0u\n" +
                "l3u\n" +
                "n0u\n" +
                "j8u\n" +
                "s0r\n");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bytes, true);
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
        Board<Character> b2 = new BattleShipBoard<Character>(10, 20);
        App app = new App(b1,b2, sr, ps,0);
        app.player1.doPlacementPhase();
        String expected = "  0|1|2|3|4|5|6|7|8|9\n" +
                "A  | | | | | | | | |  A\n" +
                "B  | | | | | | | | |  B\n" +
                "C  | | | | | | | | |  C\n" +
                "D  | | | | | | | | |  D\n" +
                "E  | | | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                System.lineSeparator() +
                "Player A: you are going to place the following ships (which are all\n" +
                "rectangular). For each ship, type the coordinate of the upper left\n" +
                "side of the ship, followed by either H (for horizontal) or V (for\n" +
                "vertical).  For example M4H would place a ship horizontally starting\n" +
                "at M4 and going to the right.  You have\n" +
                "\n" +
                "2 \"Submarines\" ships that are 1x2 \n" +
                "3 \"Destroyers\" that are 1x3\n" +
                "3 \"Battleships\" that are 2x3\n" +
                "2 \"Carriers\" that are 2x5"+System.lineSeparator() +
                "Player A where do you want to place a Submarine?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s| | | | | | | |  A\n" +
                "B  | | | | | | | | |  B\n" +
                "C  | | | | | | | | |  C\n" +
                "D  | | | | | | | | |  D\n" +
                "E  | | | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Submarine?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s| | | | | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C  | | | | | | | | |  C\n" +
                "D  | | | | | | | | |  D\n" +
                "E  | | | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Destroyer?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s| | | | | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d| | | | | | | | |  C\n" +
                "D d| | | | | | | | |  D\n" +
                "E d| | | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Destroyer?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s| | | | | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Destroyer?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  | | | | | | | | |  L\n" +
                "M  | | | | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Battleship?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  |b| | | | | | | |  L\n" +
                "M b|b|b| | | | | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Battleship?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  |b| | |b| | | | |  L\n" +
                "M b|b|b|b|b|b| | | |  M\n" +
                "N  | | | | | | | | |  N\n" +
                "O  | | | | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Battleship?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | | |  J\n" +
                "K  | | | | | | | | |  K\n" +
                "L  |b| | |b| | | | |  L\n" +
                "M b|b|b|b|b|b| | | |  M\n" +
                "N  |b| | | | | | | |  N\n" +
                "O b|b|b| | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Carrier?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | |c|  J\n" +
                "K  | | | | | | | |c|  K\n" +
                "L  |b| | |b| | | |c|c L\n" +
                "M b|b|b|b|b|b| | |c|c M\n" +
                "N  |b| | | | | | | |c N\n" +
                "O b|b|b| | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  | | | | | | | | |  S\n" +
                "T  | | | | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "Player A where do you want to place a Carrier?"+System.lineSeparator() +
                "  0|1|2|3|4|5|6|7|8|9\n" +
                "A s|s|d|d|d| | | | |  A\n" +
                "B s|s| | | | | | | |  B\n" +
                "C d|d| | | | | | | |  C\n" +
                "D d|d| | | | | | | |  D\n" +
                "E d|d| | | | | | | |  E\n" +
                "F  | | | | | | | | |  F\n" +
                "G  | | | | | | | | |  G\n" +
                "H  | | | | | | | | |  H\n" +
                "I  | | | | | | | | |  I\n" +
                "J  | | | | | | | |c|  J\n" +
                "K  | | | | | | | |c|  K\n" +
                "L  |b| | |b| | | |c|c L\n" +
                "M b|b|b|b|b|b| | |c|c M\n" +
                "N  |b| | | | | | | |c N\n" +
                "O b|b|b| | | | | | |  O\n" +
                "P  | | | | | | | | |  P\n" +
                "Q  | | | | | | | | |  Q\n" +
                "R  | | | | | | | | |  R\n" +
                "S  |c|c|c|c| | | | |  S\n" +
                "T c|c|c| | | | | | |  T\n" +
                "  0|1|2|3|4|5|6|7|8|9\n";
        assertEquals(expected, bytes.toString());
    }
}
