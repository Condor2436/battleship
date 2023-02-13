import edu.duke.zj82.battleship.*;
import org.junit.jupiter.api.Test;

import static java.lang.Character.toLowerCase;
import static org.junit.jupiter.api.Assertions.*;

public class V2ShipFactoryTest {//since V1 and V2 sub and dd don't change, this test will focus on BB and CV
    private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Placement where){
        assertEquals(expectedName, testShip.getName());
        assertEquals(expectedLetter, toLowerCase(testShip.getName().charAt(0)));
        char type = where.getOrientation();
        int width = 0;
        int height = 0;
        if(expectedLetter =='b'){
            if(type=='U'||type=='D'){
                width = 3;
                height = 2;
            } else {
                width = 2;
                height = 3;
            }
        } else {
            if(type=='U'||type=='D'){
                width = 2;
                height = 5;
            } else {
                width = 5;
                height = 2;
            }
        }
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
//                System.out.println(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i).toString());
                if(expectedLetter =='b'){
                    if(type=='U'&&j==0&&i!=1) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='D'&&j==1&&i!=1) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='R'&&i==1&&j!=1) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='L'&&i==0&&j!=1) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else assertTrue(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                } else {
                    if(type=='U'&&((i==0&&j==4)||(i==1&&j==0)||(i==1&&j==1))) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='D'&&((i==0&&j==3)||(i==0&&j==4)||(i==1&&j==0))) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='R'&&((i==0&&j==0)||(i==3&&j==1)||(i==4&&j==1))) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else if(type=='L'&&((i==0&&j==0)||(i==1&&j==0)||(i==4&&j==1))) assertFalse(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                    else assertTrue(testShip.occupiesCoordinates(new Coordinate(where.getWhere().getRow()+j,where.getWhere().getColumn()+i)));
                }
            }
        }
    }
    @Test
    public void test_BB(){
        V2ShipFactory sf = new V2ShipFactory();
        Ship<Character> s = sf.makeBattleship(new Placement("A0U"));
        checkShip(s,"Battleship",'b',new Placement("A0U"));
        Ship<Character> s1 = sf.makeBattleship(new Placement("C0D"));
        checkShip(s1,"Battleship",'b',new Placement("C0D"));
        Ship<Character> s2 = sf.makeBattleship(new Placement("A3R"));
        checkShip(s2,"Battleship",'b',new Placement("A3R"));
        Ship<Character> s3 = sf.makeBattleship(new Placement("D3L"));
        checkShip(s3,"Battleship",'b',new Placement("D3L"));
    }
    @Test
    public void test_CV(){
        V2ShipFactory sf = new V2ShipFactory();
        Ship<Character> s = sf.makeCarrier(new Placement("A0R"));
        checkShip(s,"Carrier",'c',new Placement("A0R"));
        Ship<Character> s1 = sf.makeCarrier(new Placement("C0L"));
        checkShip(s1,"Carrier",'c',new Placement("C0L"));
        Ship<Character> s2 = sf.makeCarrier(new Placement("E0U"));
        checkShip(s2,"Carrier",'c',new Placement("E0U"));
        Ship<Character> s3 = sf.makeCarrier(new Placement("E2D"));
        checkShip(s3,"Carrier",'c',new Placement("E2D"));
    }
}
