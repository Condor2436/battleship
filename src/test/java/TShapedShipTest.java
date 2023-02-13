import edu.duke.zj82.battleship.Coordinate;
import edu.duke.zj82.battleship.TShapedShip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TShapedShipTest {
    @Test
    public void test_makeCoorU(){
        TShapedShip<Character> s = new TShapedShip<Character>("BattleShip",new Coordinate(1,2),3,2,'b','*','U');
        assertEquals(4,s.getMyPieces().size());
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 4)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,4)));
    }
    @Test
    public void test_makeCoorD(){
        TShapedShip<Character> s = new TShapedShip<Character>("BattleShip",new Coordinate(1,2),3,2,'b','*','D');
        assertEquals(4,s.getMyPieces().size());
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 4)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,4)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,3)));
    }
    @Test
    public void test_makeCoorR(){
        TShapedShip<Character> s1 = new TShapedShip<Character>("BattleShip",new Coordinate(1,2),2,3,'b','*','R');
        assertEquals(4,s1.getMyPieces().size());
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 2)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(3, 2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(3,2)));
    }
    @Test
    public void test_makeCoorL(){
        TShapedShip<Character> s1 = new TShapedShip<Character>("BattleShip",new Coordinate(1,2),2,3,'b','*','L');
        assertEquals(4,s1.getMyPieces().size());
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(3, 3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(3,3)));
    }
}
