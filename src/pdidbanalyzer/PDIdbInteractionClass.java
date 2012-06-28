/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 *
 * @author archvile18
 */
public enum PDIdbInteractionClass {
    
    /** Canonical H-bonds */
    CHb(1),
    
    /** Sulphur mediated H-bonds */
    SHb(2),
    
    /** Week CH...O interactions */
    CHO(3),
    
    /** Ionic/electrostatic interactions (salt bridges) */
    Ion(4),
    
    /** Hydrophobic interactions */
    Hph(5),
    
    /** Unassigned interactions */
    Other(6);
    
    private int typeInt;

    private PDIdbInteractionClass(int typeInt) {
        this.typeInt = typeInt;
    }

    /**
     * Returns the type as an int
     * @return the type
     */
    public int getTypeInt() {
        return typeInt;
    }
    
}
