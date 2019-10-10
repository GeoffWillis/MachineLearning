/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mark
 */
public class Emitter {
    
    String Elnot = new String();
    
    List<Mode> mode = new ArrayList<>();

    public String getElnot() {
        return Elnot;
    }

    
    public void setElnot(String Elnot) {
        this.Elnot = Elnot;
    }

    public List<Mode> getMode() {
        return mode;
    }

    public void setMode(List<Mode> mode) {
        this.mode = mode;
    }
    
}
