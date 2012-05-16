/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;

/**
 * Interface for classes that format string representations of an interaction
 * @author archvile18
 */
public interface IOutputFormatter {
    
    /**
     * Generates a formatted string representation of an interaction
     * @param interactionType
     * @param atom1
     * @param atom2
     * @param distance
     * @param pdbPolymer 
     */
    public String format(IInteractionType interactionType, IPDBAtom atom1, IPDBAtom atom2, double distance, IPDBPolymer pdbPolymer);
    
    /**
     * Returns the text header for the generated output. This header should
     * usually be printed before any interaction data.
     * @return The text header for the generated output
     */
    public String getHeader();

}
