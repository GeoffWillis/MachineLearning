/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DoubleRange;
import model.Emitter;
import model.IrRange;
import model.Mode;
import model.PdRange;
import model.PriRange;
import model.RfRange;
import model.SpRange;

/**
 *
 * @author mark
 */
public class utilities {

    static DoubleRange createRange(String val1, String val2) {

        if (val1.isEmpty() || val2.isEmpty()) {
            return null;
        }

        Double d1 = Double.valueOf(val1);
        Double d2 = Double.valueOf(val2);
        return new DoubleRange(d1, d2);

    }

    public static Emitter createEmitter() {

        Emitter emitter = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("reffile.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] vals = line.split(",");
                emitter = new Emitter();
                emitter.setElnot(vals[0]);
                Mode mode = new Mode();
                DoubleRange r = createRange(vals[1], vals[2]);
                if (r != null) {
                    ArrayList<RfRange> a = new ArrayList<>();
                    RfRange range = new RfRange(r.firstVal,r.secondVal);
                    a.add(range);
                    mode.setRf(a);
                }

                r = createRange(vals[3], vals[4]);
                if (r != null) {
                    ArrayList<PriRange> a = new ArrayList<>();
                    PriRange range = new PriRange(r.firstVal,r.secondVal);
                    a.add(range);
                    mode.setPri(a);
                }

                r = createRange(vals[5], vals[6]);
                if (r != null) {
                    ArrayList<PdRange> a = new ArrayList<>();
                    PdRange range = new PdRange(r.firstVal, r.secondVal);
                    a.add(range);
                    mode.setPd(a);
                }

                r = createRange(vals[7], vals[8]);
                if (r != null) {
                    ArrayList<SpRange> a = new ArrayList<>();
                    SpRange range = new SpRange(r.firstVal, r.secondVal);
                    a.add(range);
                    mode.setSp(a);
                }

                r = createRange(vals[9], vals[10]);
                if (r != null) {
                    ArrayList<IrRange> a = new ArrayList<>();
                    IrRange range = new IrRange(r.firstVal, r.secondVal);
                    a.add(range);
                    mode.setIr(a);
                }

                if (!vals[11].isEmpty()) {
                    mode.setModType(vals[11]);
                }

                if (!vals[12].isEmpty()) {
                    mode.setScanType(vals[12]);
                }

                List<Mode> modes = new ArrayList<>();
                modes.add(mode);
                emitter.setMode(modes);
                System.out.println("got here");
            }
        } catch (Exception ex) {
            Logger.getLogger(RangeCompareTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return emitter;

    }
}
