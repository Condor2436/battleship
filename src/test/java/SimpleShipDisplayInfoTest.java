import edu.duke.zj82.battleship.Coordinate;
import edu.duke.zj82.battleship.SimpleShipDisplayInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleShipDisplayInfoTest {
    @Test
    public void test_constructor(){
        SimpleShipDisplayInfo<Character> t = new SimpleShipDisplayInfo<Character>('s','*');
        assertEquals('s',t.getInfo(new Coordinate(1,2),false));
        assertEquals('*',t.getInfo(new Coordinate(1,2),true));
    }
}
