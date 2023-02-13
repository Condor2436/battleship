import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InBoundsRuleCheckerTest {
    @Test
    public void test_true(){
        InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<Character>(null);
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        Board<Character> b = new BattleShipBoard<Character>(10,20);
        assertEquals(null,c.checkPlacement(s,b));
    }

    @Test
    public void test_false(){
        InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<Character>(null);
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        Board<Character> b = new BattleShipBoard<Character>(1,1);
        assertEquals("That placement is invalid: the ship goes off the right of the board.",c.checkPlacement(s,b));
    }

    @Test
    public void test_BattleShipBoard_constructor(){
        InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<Character>(null);
        BattleShipBoard<Character> b = new BattleShipBoard<Character>(10,20,c, 'X');
        V1ShipFactory shipFactory = new V1ShipFactory();
        Ship<Character> s1 = shipFactory.makeDestroyer(new Placement(new Coordinate(1,-1),'H'));
        Ship<Character> s2 = shipFactory.makeDestroyer(new Placement(new Coordinate(1,1),'H'));
        assertEquals("That placement is invalid: the ship goes off the left of the board.",c.checkPlacement(s1,b));
        assertEquals(null,c.checkPlacement(s2,b));
    }
}
