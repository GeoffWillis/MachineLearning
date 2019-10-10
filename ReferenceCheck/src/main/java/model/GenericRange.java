/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mark
 */
public abstract class GenericRange {
    
    public static final double PRI_TOL = .1;
    public static final double PD_TOL = .3;
    public static final double RF_TOL = 5.0;
    public static final double SCAN_TOL = 1.2;
    public static final double IR_TOL = .5;      
    
    protected DoubleRange range = null;

    public DoubleRange getRange() {
        return range;
    }

    public void setRange(DoubleRange range) {
        this.range = range;
    }
    
    public int compare(DoubleRange reference, DoubleRange o2) {
        
        // The o2 is inside the o1
        if (reference.getFirstVal() <= o2.getFirstVal() && reference.getSecondVal() >= o2.getSecondVal())
            return 0;
        // o2's lowest range is lower than o1's lowest range
        else if (reference.getFirstVal() > o2.getFirstVal())
            return -1;
        // o2's higest range is greater than o1's
        else 
            return 1;

    }
    
    public boolean insideRange(String field, DoubleRange o2) {
        
        // So we don't modify the original range
        DoubleRange refValues = new DoubleRange(getRange().getFirstVal(), getRange().getSecondVal());
        
        switch (field) {
            case "PRI": refValues.setFirstVal(refValues.getFirstVal() - PRI_TOL);
                        refValues.setSecondVal(refValues.getSecondVal() + PRI_TOL);
                        break;
            case "PD": refValues.setFirstVal(refValues.getFirstVal() - PD_TOL);
                        refValues.setSecondVal(refValues.getSecondVal() + PD_TOL);
                        break;
            case "RF": refValues.setFirstVal(refValues.getFirstVal() - RF_TOL);
                        refValues.setSecondVal(refValues.getSecondVal() + RF_TOL);
                        break;                        
             case "SCAN": refValues.setFirstVal(refValues.getFirstVal() - SCAN_TOL);
                        refValues.setSecondVal(refValues.getSecondVal() + SCAN_TOL);
                        break;   
            case "IR": refValues.setFirstVal(refValues.getFirstVal() - IR_TOL);
                        refValues.setSecondVal(refValues.getSecondVal() + IR_TOL);
                        break;     
            default:    throw new IllegalArgumentException("Invalid field");
                   
        }
        
        int result = compare(refValues, o2);
        if (result == 0)
            return true;
        
        return false;
    } 
    
    
}
