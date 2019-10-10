/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mark
 */
public class Mode {
    
    String elnot;
    
    
    Map<String, PriRange> parametrics = new HashMap<>();
    
    ArrayList<RfRange> rf = new ArrayList<>();
    
    ArrayList<PdRange> pd = new ArrayList<>();
    
    ArrayList<IrRange> ir = new ArrayList<>();
    
    ArrayList<SpRange> sp = new ArrayList<>();
    
    ArrayList<PriRange> pri = new ArrayList<>();
    
    String modType = new String();
    
    String ScanType = new String();

    public String getElnot() {
        return elnot;
    }

    public void setElnot(String elnot) {
        this.elnot = elnot;
    }

    public ArrayList<RfRange> getRf() {
        return rf;
    }

    public void setRf(ArrayList<RfRange> rf) {
        this.rf = rf;
    }

    public ArrayList<PdRange> getPd() {
        return pd;
    }

    public void setPd(ArrayList<PdRange> pd) {
        this.pd = pd;
    }

    public ArrayList<IrRange> getIr() {
        return ir;
    }

    public void setIr(ArrayList<IrRange> ir) {
        this.ir = ir;
    }

    public ArrayList<SpRange> getSp() {
        return sp;
    }

    public void setSp(ArrayList<SpRange> sp) {
        this.sp = sp;
    }

    public ArrayList<PriRange> getPri() {
        return pri;
    }

    public void setPri(ArrayList<PriRange> pri) {
        this.pri = pri;
    }

    public String getModType() {
        return modType;
    }

    public void setModType(String modType) {
        this.modType = modType;
    }

    public String getScanType() {
        return ScanType;
    }

    public void setScanType(String ScanType) {
        this.ScanType = ScanType;
    }
    
}
