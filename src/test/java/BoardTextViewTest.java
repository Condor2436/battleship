import static org.junit.jupiter.api.Assertions.*;

import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
    private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody){
        Board<Character> b1 = new BattleShipBoard<Character>(w, h);
        BoardTextView view = new BoardTextView(b1);
        assertEquals(expectedHeader, view.makeHeader());
        String expected = expectedHeader + expectedBody + expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
    }
    @Test
    public void test_display_empty_2by2() {
        emptyBoardHelper(2,2,"  0|1\n","A  |  A\n"+"B  |  B\n");
    }

    @Test
    public void test_invalid_board_size() {
        Board<Character> wideBoard = new BattleShipBoard<Character>(11,20);
        Board<Character> tallBoard = new BattleShipBoard<Character>(10,27);
        //you should write two assertThrows here
        assertThrows(IllegalArgumentException.class, ()->new BoardTextView(wideBoard));
        assertThrows(IllegalArgumentException.class, ()->new BoardTextView(tallBoard));
    }
    @Test
    public void test_display_empty_3by2(){
        emptyBoardHelper(2,3,"  0|1\n","A  |  A\n"+"B  |  B\n"+"C  |  C\n");
    }
    @Test
    public void test_display_empty_3by5(){
        emptyBoardHelper(5,3,"  0|1|2|3|4\n","A  | | | |  A\n"+"B  | | | |  B\n"+"C  | | | |  C\n");
    }
    @Test
    public void test_display_3by3(){
        Board<Character> b1 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        Ship<Character> s1 = new RectangleShip<Character>(new Coordinate("A0"),'s','*');
        Ship<Character> s2 = new RectangleShip<Character>(new Coordinate("B1"),'s','*');
        Ship<Character> s3 = new RectangleShip<Character>(new Coordinate("C2"),'s','*');
        b1.tryAddShip(s1);
        b1.tryAddShip(s2);
        b1.tryAddShip(s3);
        BoardTextView view = new BoardTextView(b1);
        String expectedHeader = "  0|1|2\n";
        assertEquals(expectedHeader, view.makeHeader());
        String expected=
                expectedHeader+
                        "A s| |  A\n"+
                        "B  |s|  B\n"+
                        "C  | |s C\n"+
                        expectedHeader;
        assertEquals(expected, view.displayMyOwnBoard());
        b1.fireAt(new Coordinate("B1"));
        expected=
                expectedHeader+
                        "A  | |  A\n"+
                        "B  |s|  B\n"+
                        "C  | |  C\n"+
                        expectedHeader;
        assertEquals(expected, view.displayEnemyBoard());
        b1.fireAt(new Coordinate("B0"));
        expected=
                expectedHeader+
                        "A  | |  A\n"+
                        "B X|s|  B\n"+
                        "C  | |  C\n"+
                        expectedHeader;
        assertEquals(expected, view.displayEnemyBoard());
    }

    @Test
    void displayMyBoardWithEnemyNextToIt() {
        Board<Character> b1 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        Board<Character> b2 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        b1.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("A0"),1,2,'s','*'));
        b2.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("C0"),2,1,'s','*'));
        BoardTextView v1 = new BoardTextView(b1);
        BoardTextView v2 = new BoardTextView(b2);
        String expected= "     Your Ocean             Player B's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A s| |  A                A  | |  A\n" +
                "B s| |  B                B  | |  B\n" +
                "C  | |  C                C  | |  C\n" +
                "  0|1|2                    0|1|2\n";
        String expected1= "     Your Ocean             Player A's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A  | |  A                A  | |  A\n" +
                "B  | |  B                B  | |  B\n" +
                "C s|s|  C                C  | |  C\n" +
                "  0|1|2                    0|1|2\n";
        assertEquals(expected, v1.displayMyBoardWithEnemyNextToIt(v2,"Your Ocean","Player B's Ocean"));
        assertEquals(expected1, v2.displayMyBoardWithEnemyNextToIt(v1,"Your Ocean","Player A's Ocean"));
        b1.fireAt(new Coordinate("C0"));
        b2.fireAt(new Coordinate("B0"));
        expected= "     Your Ocean             Player B's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A s| |  A                A  | |  A\n" +
                "B s| |  B                B X| |  B\n" +
                "C  | |  C                C  | |  C\n" +
                "  0|1|2                    0|1|2\n";
        expected1= "     Your Ocean             Player A's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A  | |  A                A  | |  A\n" +
                "B  | |  B                B  | |  B\n" +
                "C s|s|  C                C X| |  C\n" +
                "  0|1|2                    0|1|2\n";
        assertEquals(expected, v1.displayMyBoardWithEnemyNextToIt(v2,"Your Ocean","Player B's Ocean"));
        assertEquals(expected1, v2.displayMyBoardWithEnemyNextToIt(v1,"Your Ocean","Player A's Ocean"));
        b1.fireAt(new Coordinate("A0"));
        b2.fireAt(new Coordinate("c0"));
        expected= "     Your Ocean             Player B's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A *| |  A                A  | |  A\n" +
                "B s| |  B                B X| |  B\n" +
                "C  | |  C                C s| |  C\n" +
                "  0|1|2                    0|1|2\n";
        expected1= "     Your Ocean             Player A's Ocean\n" +
                "  0|1|2                    0|1|2\n"+
                "A  | |  A                A s| |  A\n" +
                "B  | |  B                B  | |  B\n" +
                "C *|s|  C                C X| |  C\n" +
                "  0|1|2                    0|1|2\n";
        assertEquals(expected, v1.displayMyBoardWithEnemyNextToIt(v2,"Your Ocean","Player B's Ocean"));
        assertEquals(expected1, v2.displayMyBoardWithEnemyNextToIt(v1,"Your Ocean","Player A's Ocean"));
    }
}
