/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import java.util.ArrayList;
import model.DoubleRange;
import model.Emitter;
import model.IrRange;
import model.Mode;
import model.PdRange;
import model.PriRange;
import model.RfRange;
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
public class EmitterValidatorTest {
    
    static Emitter referenceEmitter = null;
    
    public EmitterValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        referenceEmitter = utilities.createEmitter();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkEmitterParametrics method, of class EmitterValidator.
     */
    @Test
    public void testCompareAllModes() {
        System.out.println("testCompareAllModes");
        EmitterValidator ec = new EmitterValidator(referenceEmitter);

        Emitter collectedEmitter = new Emitter();
        collectedEmitter.setElnot("A123B");
        Mode collectedMode = new Mode();
        RfRange rfRange = new RfRange(8999.0, 9000.0);
        collectedMode.getRf().add(rfRange);
        PriRange priRange = new PriRange(2950.0, 2952.0);
        collectedMode.getPri().add((priRange));
        PdRange pdRange = new PdRange(1.4, 1.5);
        collectedMode.getPd().add(pdRange);
        IrRange irRange = new IrRange(43.4, 45.0);
        collectedMode.getIr().add(irRange);
        collectedMode.setModType("KK");
        collectedEmitter.getMode().add(collectedMode);
        
        boolean flag = ec.compareAllModes(collectedEmitter);
        assertEquals(flag, false);

    }

    /**
     * Test of insideRange method, of class EmitterValidator.
     */
    @Test
    public void testInsideRange() {
        System.out.println("insideRange");
        DoubleRange collectedRange = new DoubleRange(8999.0, 9000.0);
        
        EmitterValidator ec = new EmitterValidator(referenceEmitter);
        boolean result = ec.insideRange("RF", collectedRange);
        assertEquals("The RF values were different", true, result);
        
        collectedRange.setSecondVal(10000.0);
        result = ec.insideRange("RF", collectedRange);
        assertNotEquals("The RF values was not out of range", true, result);
        
        collectedRange.setFirstVal(3000.0);
        collectedRange.setSecondVal(3025.0);
        result = ec.insideRange("PRI", collectedRange);
        assertEquals("The PRI values were different", true, result);
        
        collectedRange.setSecondVal(10000.0);
        result = ec.insideRange("PRI", collectedRange);
        assertNotEquals("The PRI values was not out of range", true, result);      
        
        collectedRange.setFirstVal(1.4);
        collectedRange.setSecondVal(1.5);
        result = ec.insideRange("PD", collectedRange);
        assertEquals("The PD values were different", true, result);      
        
        collectedRange.setFirstVal(1.4);
        collectedRange.setSecondVal(2.5);
        result = ec.insideRange("PD", collectedRange);
        assertNotEquals("The PD values were different", true, result);   
        
        result = ec.insideRange("SP", collectedRange);
        assertNotEquals("The SP was inside even though the reference didn't have one", true, result);
        
        collectedRange.setFirstVal(43.0);
        collectedRange.setSecondVal(45.0);
        result = ec.insideRange("IR", collectedRange);
        assertEquals("The IR values were different", true, result);

    }

    /**
     * Test of getRange method, of class EmitterValidator.
     */
    @Test
    public void testGetRange() {
        System.out.println("getRange");

        EmitterValidator ec = new EmitterValidator(referenceEmitter);
        ArrayList result = ec.getRange("PRI", referenceEmitter.getMode().get(0));
        assertEquals("PRI values didn't equal", referenceEmitter.getMode().get(0).getPri(), result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
