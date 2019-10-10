/**
 * Compares a passed in emiiter with a reference emitter
 * 
 * 
 */
package operations;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Emitter;
import model.DoubleRange;
import model.GenericRange;
import model.IrRange;
import model.Mode;
import model.PdRange;
import model.PriRange;
import model.ResultObject;
import model.RfRange;
import model.SpRange;

/**
 *
 * @author mark
 */
public class EmitterValidator {
    
    static Logger logger = Logger.getLogger(EmitterValidator.class.getName());

    // Ground truth for the emitter
    Emitter reference = null;
    
    public static String[] VALID_MODES = { "PRI", "RF", "PD", "SP", "IR" };

    public EmitterValidator(Emitter emitter) {

        reference = emitter;
    }


    /**
     * This compares a range to every reference mode of a given type
     * 
     * @param field  (PRI, RF, SP, PD, IR)
     * @param collectedRange  - range to compare to mode
     * @return 
     */
    public boolean insideRange(String field, DoubleRange collectedRange) {

        boolean matched = false;

        List<Mode> refMode = reference.getMode();

        Iterator<Mode> modeItr = refMode.iterator();

        while (modeItr.hasNext() && !matched) {
            List<GenericRange> rangeList = (List<GenericRange>)getRange(field, modeItr.next());
            Iterator<GenericRange> dRange = rangeList.iterator();
            while (dRange.hasNext()) {
                GenericRange range = dRange.next();
                if (RangeCompare.parametricCompare(field, range.getRange(), collectedRange) == 0) {
                    matched = true;
                    return matched;
                }
            }

        }
        return matched;
    }
    
    /**
     * Compare all modes of a new emitter to a reference emitter
     * 
     * @param collectedEmitter
     * @return 
     */
    public boolean compareAllModes(Emitter collectedEmitter) {
        
        boolean flag = false;
        Iterator<Mode> collectedModeItr = collectedEmitter.getMode().iterator();
        
        while (!flag && collectedModeItr.hasNext()) {
            Mode collectedMode = collectedModeItr.next();
            //TODO:  What if we don't have a modType in the reference?
            if (!collectedMode.getModType().equals(reference.getMode().get(0).getModType()))
                return true;
            
            Iterator<PriRange> priItr = collectedMode.getPri().iterator();
            while (!flag && priItr.hasNext()) {
                PriRange range = priItr.next();
                if (!insideRange("PRI", range.getRange())) {
                    logger.log(Level.FINEST, "failed PRI");
                    return true;
                }
            }
            
            Iterator<RfRange> rfItr = collectedMode.getRf().iterator();
            while (!flag && rfItr.hasNext()) {
                RfRange range = rfItr.next();
                if (!insideRange("RF", range.getRange())) {
                    logger.log(Level.FINEST, "failed RF");
                    return true;
                }
            }     
            
            Iterator<PdRange> pdItr = collectedMode.getPd().iterator();
            while (!flag && pdItr.hasNext()) {
                PdRange range = pdItr.next();
                if (!insideRange("PD", range.getRange())) {
                    logger.log(Level.FINEST, "failed PD");
                    return true;
                }
            }  

            Iterator<SpRange> spItr = collectedMode.getSp().iterator();
            while (!flag && spItr.hasNext()) {
                SpRange range = spItr.next();
                
                if (!insideRange("SP", range.getRange())) {
                    logger.log(Level.FINEST, "failed SP");
                    return true;
                }
            }  

            Iterator<IrRange> irItr = collectedMode.getIr().iterator();
            while (!flag && irItr.hasNext()) {
                IrRange range = irItr.next();
                if (!insideRange("IR", range.getRange())) {
                    logger.log(Level.FINEST, "failed IR");
                    return true;
                }
            }              
            
        }
        
        return flag;
    }


    /**
     * Returns the list associated with a given parameter
     * 
     * @param field
     * @param mode
     * @return 
     */
    ArrayList<?> getRange(String field, Mode mode) {

        
        switch (field) {
            case "PRI":
                return mode.getPri();
            case "RF":
                return mode.getRf();
            case "PD":
                return mode.getPd();
            case "SP":
                return mode.getSp();
            default:
                return mode.getIr();
        }

    }
}
