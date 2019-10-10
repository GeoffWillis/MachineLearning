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
public class IrRange extends GenericRange {
    
    public IrRange(Double first, Double second) {
        super.setRange(new DoubleRange(first,second));
    }
}
