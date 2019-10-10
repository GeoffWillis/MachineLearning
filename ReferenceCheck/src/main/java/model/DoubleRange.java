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
public class DoubleRange {
    
 
    
    public DoubleRange(Double first, Double second) {
        firstVal = first;
        secondVal = second;
    }
    
    
    public Double firstVal = null;
    
    public Double secondVal = null;

    public Double getFirstVal() {
        return firstVal;
    }

    public void setFirstVal(Double firstVal) {
        this.firstVal = firstVal;
    }

    public Double getSecondVal() {
        return secondVal;
    }

    public void setSecondVal(Double secondVal) {
        this.secondVal = secondVal;
    }
  
  
    
}
