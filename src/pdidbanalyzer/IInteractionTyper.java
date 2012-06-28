/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import org.openscience.cdk.interfaces.IAtom;

/**
 *
 * @author archvile18
 */
public interface IInteractionTyper {

    /**
     * Returns the type of the interaction between atom1 and atom2. Returns null
     * if no appropriate type could be assigned.
     * @param atom1 first atom
     * @param atom2 second atom
     * @return the interaction type
     */
    IInteractionType getInteractionType(IAtom atom1, IAtom atom2);

}
