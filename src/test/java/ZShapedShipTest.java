import edu.duke.zj82.battleship.Coordinate;
import edu.duke.zj82.battleship.ZShapedShip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZShapedShipTest {
    @Test
    public void test_makeCoorU(){
        ZShapedShip<Character> s = new ZShapedShip<Character>("Carrier",new Coordinate(1,2),2,5,'c','*','U');
        assertEquals(7,s.getMyPieces().size());
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(3, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(4, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(3, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(4, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(5, 3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(3,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(4,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(3,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(4,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(5,3)));
    }
    @Test
    public void test_makeCoorD(){
        ZShapedShip<Character> s = new ZShapedShip<Character>("Carrier",new Coordinate(1,2),2,5,'c','*','D');
        assertEquals(7,s.getMyPieces().size());
        assertTrue(s.getMyPieces().containsKey(new Coordinate(1, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(3, 2)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(3, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(4, 3)));
        assertTrue(s.getMyPieces().containsKey(new Coordinate(5, 3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(1,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(3,2)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(3,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(4,3)));
        assertEquals(false,s.getMyPieces().get(new Coordinate(5,3)));
    }
    @Test
    public void test_makeCoorR(){
        ZShapedShip<Character> s1 = new ZShapedShip<Character>("Carrier",new Coordinate(1,2),5,2,'c','*','R');
        assertEquals(7,s1.getMyPieces().size());
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 4)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 5)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 6)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 4)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,4)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,5)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,6)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,4)));
    }
    @Test
    public void test_makeCoorL(){
        ZShapedShip<Character> s1 = new ZShapedShip<Character>("Carrier",new Coordinate(1,2),5,2,'c','*','L');
        assertEquals(7,s1.getMyPieces().size());
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 4)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 5)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(1, 6)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 2)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 3)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 4)));
        assertTrue(s1.getMyPieces().containsKey(new Coordinate(2, 5)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,5)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,4)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,5)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(1,6)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,2)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,3)));
        assertEquals(false,s1.getMyPieces().get(new Coordinate(2,4)));
    }
}
