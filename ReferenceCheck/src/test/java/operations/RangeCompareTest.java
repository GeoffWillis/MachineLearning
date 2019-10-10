/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import model.DoubleRange;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mark
 */
import model.Emitter;
public class RangeCompareTest {

    
    static Emitter referenceEmitter = null;
    
    public RangeCompareTest() {
    }
    
    static DoubleRange createRange(String val1, String val2) {
    
         if (val1.isEmpty() || val2.isEmpty())
             return null;
                     
         Double d1 = Double.valueOf(val1);
         Double d2 = Double.valueOf(val2);
         return new DoubleRange(d1,d2);
         
    }
    
    @BeforeClass
    public static void setUpClass() {
        referenceEmitter = utilities.createEmitter();

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compare method, of class RangeCompare.
     */
    @Test
    public void testCompareInsideRange() {
        System.out.println("compare");
        DoubleRange o1 = new DoubleRange(2.0, 5.0);
        DoubleRange o2 = new DoubleRange(3.0,4.0);
        RangeCompare instance = new RangeCompare();

        int result = instance.compare(o1, o2);
        assertEquals( "The second range was not contained inside the first",result,0);

    }
    
    @Test
    public void testCompareLowerRangeSmallerinSecond() {

        DoubleRange o1 = new DoubleRange(2.0, 5.0);
        DoubleRange o2 = new DoubleRange(1.0,4.0);
        RangeCompare instance = new RangeCompare();

        int result = instance.compare(o1, o2);
        assertEquals( "The second range is lower than the first",result,-1);

    }     
    
    @Test
    public void testCompareLowerRangeLargerinSecond() {

        DoubleRange o1 = new DoubleRange(2.0, 5.0);
        DoubleRange o2 = new DoubleRange(3.0,5.5);
        RangeCompare instance = new RangeCompare();

        int result = instance.compare(o1, o2);
        assertEquals( "The second range is higher than the first",result,1);

    }     
    
    @Test
    public void testCompareInsideRangeWithTolerance() {

        DoubleRange reference = new DoubleRange(2.0, 5.0);
        DoubleRange o2 = new DoubleRange(1.9,4.0);
        RangeCompare instance = new RangeCompare();

        int result = instance.parametricCompare("PRI", reference, o2);
        assertEquals( "The first range is higher than the first",result,0);
        
        o2.setFirstVal(2.0);
        o2.setSecondVal(5.1);
        result = instance.parametricCompare("PRI", reference, o2);
        assertEquals( "The second range is higher than the first",result,0);
        
        o2.setFirstVal(1.8);
        o2.setSecondVal(5.1);
        result = instance.parametricCompare("PRI", reference, o2);
        assertEquals( "The second range is higher than the first",result,-1);
    } 
}
