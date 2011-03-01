/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 * Enum to define whether a DNA atom is part of the major groove, minor groove,
 * backbone or not assigned.
 * @author andreas
 */
public enum GrooveType {
    /** Major groove ("wide") */
    W,
    
    /** Minor groove ("short") */
    S,
    
    /** Backbone */
    B,
    
    /** Not assigned */
    N;

}
