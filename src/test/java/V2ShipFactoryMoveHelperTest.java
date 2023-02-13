import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class V2ShipFactoryMoveHelperTest {
    @Test
    void test_creater(){
        V2ShipFactory sf0 = new V2ShipFactory();
        Ship<Character> ship = sf0.makeBattleship(new Placement("A0U"));
        ship.recordHitAt(new Coordinate("A1"));
        V2ShipFactoryMoveHelper sf = new V2ShipFactoryMoveHelper();
        Ship<Character> s2 = sf.createShip(new Placement("A0R"),ship);
        assertTrue(s2.wasHitAt(new Coordinate("B1")));
        Ship<Character> s3 = sf0.makeCarrier(new Placement("J0U"));
        s3.recordHitAt(new Coordinate("J0"));
        Ship<Character> s4 = sf.createShip(new Placement("j0r"),s3);
        assertTrue(s4.wasHitAt(new Coordinate("j4")));
    }
}
