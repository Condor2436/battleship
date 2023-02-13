import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import static java.lang.Character.toLowerCase;
import static org.junit.jupiter.api.Assertions.*;

public class V1ShipFactoryTest {
    private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Placement where){
        assertEquals(expectedName, testShip.getName());
        assertEquals(expectedLetter, toLowerCase(testShip.getName().charAt(0)));
        int count = 0;
        if(expectedLetter=='s'){
            count = 2;
        } else if(expectedLetter == 'd'){
            count = 3;
        } else if(expectedLetter =='b'){
            count = 4;
        } else {
            count = 6;
        }
        for(int i = 0; i < count; i++){
            if(where.getOrientation()=='H'){
                assertTrue(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow(),where.getWhere().getColumn()+i)));
                if(i != count -1){
                    assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+i+1,where.getWhere().getColumn())));
                }
            } else {
                assertTrue(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+i,where.getWhere().getColumn())));
                if(i != count -1){
                    assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow(),where.getWhere().getColumn()+i+1)));
                }
            }
        }
    }

    @Test
    public void test_sub(){
        V1ShipFactory sf = new V1ShipFactory();
        Ship<Character> s = sf.makeSubmarine(new Placement("A0H"));
        checkShip(s,"Submarine",'s',new Placement("A0H"));
        Ship<Character> s1 = sf.makeSubmarine(new Placement("C0V"));
        checkShip(s1,"Submarine",'s',new Placement("C0V"));
    }

    @Test
    public void test_DD(){
        V1ShipFactory sf = new V1ShipFactory();
        Ship<Character> s = sf.makeDestroyer(new Placement("A0H"));
        checkShip(s,"Destroyer",'d',new Placement("A0H"));
        Ship<Character> s1 = sf.makeDestroyer(new Placement("C0V"));
        checkShip(s1,"Destroyer",'d',new Placement("C0V"));
    }
    @Test
    public void test_BB(){
        V1ShipFactory sf = new V1ShipFactory();
        Ship<Character> s = sf.makeBattleship(new Placement("A0H"));
        checkShip(s,"Battleship",'b',new Placement("A0H"));
        Ship<Character> s1 = sf.makeBattleship(new Placement("C0V"));
        checkShip(s1,"Battleship",'b',new Placement("C0V"));
    }
    @Test
    public void test_CV(){
        V1ShipFactory sf = new V1ShipFactory();
        Ship<Character> s = sf.makeCarrier(new Placement("A0H"));
        checkShip(s,"Carrier",'c',new Placement("A0H"));
        Ship<Character> s1 = sf.makeCarrier(new Placement("C0V"));
        checkShip(s1,"Carrier",'c',new Placement("C0V"));
    }
}
