import static org.junit.jupiter.api.Assertions.*;

import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
    private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
        for(int i = 0; i < b.getHeight(); i++){
            for(int j = 0; j< b.getWidth(); j++){
                Coordinate curr = new Coordinate(i,j);
                assertEquals(b.whatIsAtForSelf(curr),expected[i][j]);
            }
        }
    }

    @Test
    public void test_width_and_height() {
        Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
        assertEquals(10, b1.getWidth());
        assertEquals(20, b1.getHeight());
    }
    @Test
    public void test_invalid_dimensions() {
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
        assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
    }
    @Test
    public void test_empty_board(){
        BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10,20);
        Character[][] expected = new Character[b1.getHeight()][b1.getWidth()];
        checkWhatIsAtBoard(b1,expected);
    }

    @Test
    public void test_board_with_ships(){
        BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10,20);
        Character[][] expected = new Character[b1.getHeight()][b1.getWidth()];
        Ship<Character> s1 = new RectangleShip<Character>(new Coordinate("A0"),'s','*');
        Ship<Character> s2 = new RectangleShip<Character>(new Coordinate("B1"),'s','*');
        Ship<Character> s3 = new RectangleShip<Character>(new Coordinate("C2"),'s','*');
        b1.tryAddShip(s1);
        b1.tryAddShip(s2);
        b1.tryAddShip(s3);
        expected[0][0] = 's';
        expected[1][1] = 's';
        expected[2][2] = 's';
        checkWhatIsAtBoard(b1,expected);
    }

    @Test
    public void test_fireAt(){
        BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(10,20);
        Ship<Character> s1 = new RectangleShip<Character>(new Coordinate("A0"),'s','*');
        b1.tryAddShip(s1);
        assertSame(s1,b1.fireAt(new Coordinate("A0")));
        assertEquals('*',b1.whatIsAtForSelf(new Coordinate("A0")));
        assertEquals('s',b1.whatIsAtForEnemy(new Coordinate("A0")));
        assertTrue(s1.isSunk());
        assertNull(b1.fireAt(new Coordinate("A1")));
        assertTrue(b1.getEnemyMisses().contains(new Coordinate("A1")));
        assertEquals(null,b1.whatIsAtForEnemy(new Coordinate("A1")));
    }

    @Test
    void checkStatus() {
        Board<Character> b1 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        Board<Character> b2 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        b1.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("A0"),1,2,'s','*'));
        b2.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("C0"),2,1,'s','*'));
        b1.fireAt(new Coordinate("C0"));
        b2.fireAt(new Coordinate("B0"));
        assertFalse(((BattleShipBoard<Character>) b1).checkStatus());
        assertFalse(((BattleShipBoard<Character>) b2).checkStatus());
        b1.fireAt(new Coordinate("A0"));
        b2.fireAt(new Coordinate("c0"));
        assertFalse(((BattleShipBoard<Character>) b1).checkStatus());
        assertFalse(((BattleShipBoard<Character>) b2).checkStatus());
        b1.fireAt(new Coordinate("B0"));
        assertTrue(((BattleShipBoard<Character>) b1).checkStatus());
        b2.fireAt(new Coordinate("c1"));
        assertTrue(((BattleShipBoard<Character>) b2).checkStatus());
    }
    @Test
    void test_tryAddShip(){
        Board<Character> b1 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        assertEquals("That placement is invalid: the ship goes off the bottom of the board.",b1.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("D0"),1,2,'s','*')));
        assertEquals("That placement is invalid: the ship goes off the right of the board.",b1.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("A4"),1,2,'s','*')));
    }
    @Test
    void test_whatIsAtForEnemy(){
        Board<Character> b1 = new BattleShipBoard<Character>(3, 3,new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null)),'X');
        b1.tryAddShip(new RectangleShip<Character>("Submarine",new Coordinate("A0"),1,2,'s','*'));
        b1.fireAt(new Coordinate("A0"));
        assertTrue(b1.getEnemyHits().containsKey(new Coordinate("A0")));
        assertEquals('s',b1.getEnemyHits().get(new Coordinate("A0")));
        Ship<Character> toMove = b1.locateAt(new Coordinate("A0"));
        Ship<Character> target = new RectangleShip<Character>("Submarine",new Coordinate("A1"),2,1,'s','*', toMove.getHitted());
        b1.tryMoveShip(toMove,target);
        assertTrue(b1.getEnemyHits().containsKey(new Coordinate("A0")));
        assertEquals('s',b1.getEnemyHits().get(new Coordinate("A0")));
        assertEquals(1,b1.getMyShips().size());
        assertEquals('s',b1.whatIsAtForEnemy(new Coordinate("A0")));
        assertNull(b1.whatIsAtForEnemy(new Coordinate("A1")));
    }
}
