import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoCollisionRuleCheckerTest {
    @Test
    public void test_true(){
        NoCollisionRuleChecker<Character> c = new NoCollisionRuleChecker<Character>(null);
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        Board<Character> b = new BattleShipBoard<Character>(10,20);
        assertEquals(null,c.checkPlacement(s,b));
    }

    @Test
    public void test_false(){
        NoCollisionRuleChecker<Character> c = new NoCollisionRuleChecker<Character>(null);
        RectangleShip<Character> s = new RectangleShip<Character>("Submarine",new Coordinate(1,2),1,2,'s','*');
        RectangleShip<Character> s1 = new RectangleShip<Character>("Submarine",new Coordinate(1,2),2,1,'s','*');
        Board<Character> b = new BattleShipBoard<Character>(10,20);
        b.tryAddShip(s);
        assertEquals("That placement is invalid: the ship overlaps another ship.",b.tryAddShip(s1));
    }

    @Test
    public void test_BattleShipBoard_constructor(){
        InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null));
        BattleShipBoard<Character> b = new BattleShipBoard<Character>(10,20,c, 'X');
        V1ShipFactory shipFactory = new V1ShipFactory();
        Ship<Character> s1 = shipFactory.makeDestroyer(new Placement(new Coordinate(1,2),'H'));
        Ship<Character> s2 = shipFactory.makeDestroyer(new Placement(new Coordinate(1,2),'V'));
        b.tryAddShip(s1);
        assertEquals("That placement is invalid: the ship overlaps another ship.",b.tryAddShip(s2));
    }
}
