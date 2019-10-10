/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mark
 */
public class ResultObject {
    
    List<String> elnot = new LinkedList<>();
    
    Mode mode = null;
    
    // I'm not sure if I need this
    List<Mode> overlaps = new LinkedList<>();

    public List<String> getElnot() {
        return elnot;
    }

    public void setElnot(List<String> elnot) {
        this.elnot = elnot;
    }

    public boolean isInsideRange() {
        return mode != null;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<Mode> getOverlaps() {
        return overlaps;
    }

}
