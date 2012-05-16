/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 *
 * @author andreas
 */
public interface IInteractionType {

    /**
     * Returns the interaction class of this interaction type (i.e. the "super"
     * type).
     * @return the interaction class
     */
    PDIdbInteractionClass getInteractionClass();

    /**
     * Returns the type as an int
     * @return the type
     */
    int getTypeInt();

}
