import edu.duke.zj82.battleship.BattleShipBoard;
import edu.duke.zj82.battleship.Coordinate;
import edu.duke.zj82.battleship.RectangleShip;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleShipTest {
    @Test
    public void test_makeCoor(){
        RectangleShip<Character> s = new RectangleShip<Character>("Destroyer",new Coordinate(1,2),1,3,'d','*');
        assertEquals(3,s.getMyPieces().size());
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(3, 2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(3,2)));
    }

    @Test
    public void test_record_was_isSunk_getDisplayInfoAt(){
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        assertEquals("Submarine",s.getName());
        assertEquals('s',s.getDisplayInfoAt(new Coordinate(1,2),true));
        assertEquals('s',s.getDisplayInfoAt(new Coordinate(2,2),true));
        assertFalse(s.wasHitAt(new Coordinate(1, 2)));
        assertFalse(s.wasHitAt(new Coordinate(2, 2)));
        assertFalse(s.isSunk());
        s.recordHitAt(new Coordinate(1,2));
        assertEquals('*',s.getDisplayInfoAt(new Coordinate(1,2),true));
        assertEquals('s',s.getDisplayInfoAt(new Coordinate(2,2),true));
        assertTrue(s.wasHitAt(new Coordinate(1, 2)));
        assertFalse(s.wasHitAt(new Coordinate(2, 2)));
        assertFalse(s.isSunk());
        s.recordHitAt(new Coordinate(2,2));
        assertEquals('*',s.getDisplayInfoAt(new Coordinate(1,2),true));
        assertEquals('*',s.getDisplayInfoAt(new Coordinate(2,2),true));
        assertTrue(s.wasHitAt(new Coordinate(1, 2)));
        assertTrue(s.wasHitAt(new Coordinate(2, 2)));
        assertTrue(s.isSunk());
    }

    @Test
    public void test_invalid_input(){
        assertThrows(IllegalArgumentException.class, ()->new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*').wasHitAt(new Coordinate(3,2)));
        assertThrows(IllegalArgumentException.class, ()->new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*').recordHitAt(new Coordinate(3,2)));
        assertThrows(IllegalArgumentException.class, ()->new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*').getDisplayInfoAt(new Coordinate(3,2),true));
    }

    @Test
    public void test_getCoor(){
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        for(Coordinate c : s.getCoordinates()){
            assertTrue(s.occupiesCoordinates(c));
        }
    }
}
