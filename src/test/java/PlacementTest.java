import edu.duke.zj82.battleship.Coordinate;
import edu.duke.zj82.battleship.Placement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlacementTest {
    @Test
    public void test_equals() {
        Coordinate c1 = new Coordinate(1, 2);
        Coordinate c2 = new Coordinate(1, 2);
        Coordinate c3 = new Coordinate(1, 3);
        Placement p1 = new Placement(c1,'H');
        Placement p2 = new Placement(c1,'h');
        Placement p3 = new Placement(c2,'h');
        Placement p4 = new Placement(c2,'v');
        Placement p5 = new Placement(c3,'h');
        assertEquals(p1, p1);   //equals should be reflexsive
        assertEquals(p1, p2);   //different objects but same contents
        assertEquals(p1, p3);  //different objects but same contents
        assertNotEquals(p1, p4);
        assertNotEquals(p1, p5);
        assertNotEquals(p1, "(1, 2, H)"); //different types
    }

    @Test
    public void test_hashCode() {
        Coordinate c1 = new Coordinate(1, 2);
        Coordinate c2 = new Coordinate(1, 2);
        Coordinate c3 = new Coordinate(1, 3);
        Placement p1 = new Placement(c1,'H');
        Placement p2 = new Placement(c1,'h');
        Placement p3 = new Placement(c2,'h');
        Placement p4 = new Placement(c2,'v');
        Placement p5 = new Placement(c3,'h');
        assertEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p1.hashCode(), p3.hashCode());
        assertNotEquals(p1.hashCode(), p4.hashCode());
        assertNotEquals(p1.hashCode(), p5.hashCode());
    }

    @Test
    void test_string_constructor_valid_cases() {
        Placement p1 = new Placement("B3H");
        assertEquals(1, p1.getWhere().getRow());
        assertEquals(3, p1.getWhere().getColumn());
        assertEquals('H', p1.getOrientation());
        Placement p2 = new Placement("D5v");
        assertEquals(3, p2.getWhere().getRow());
        assertEquals(5, p2.getWhere().getColumn());
        assertEquals('V', p2.getOrientation());
        Placement p3 = new Placement("a9h");
        assertEquals(0, p3.getWhere().getRow());
        assertEquals(9, p3.getWhere().getColumn());
        assertEquals('H', p3.getOrientation());
        Placement p4 = new Placement("z0V");
        assertEquals(25, p4.getWhere().getRow());
        assertEquals(0, p4.getWhere().getColumn());
        assertEquals('V', p4.getOrientation());
    }
    @Disabled
    @Test
    public void test_string_constructor_error_cases() {
        assertThrows(IllegalArgumentException.class, () -> new Placement("00A"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("AAA"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("@0%"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("[0H"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("A/V"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("A:v"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("A"));
        assertThrows(IllegalArgumentException.class, () -> new Placement("A12"));
        assertThrows(IllegalArgumentException.class, () -> new Placement(new Coordinate("A1"), 'X'));
    }
}
