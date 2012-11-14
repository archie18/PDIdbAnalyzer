/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.util.List;

/**
 * Container class to hold the Pantano group residue map definitions
 * @author andreas
 */
public class PantanoResidue {
    
    /** List of alternative residue names */
    List<String> resNames;
    
    /** List of considered atom names */
    List<String> atomNames;

    public List<String> getAtomNames() {
        return atomNames;
    }

    public PantanoResidue setAtomNames(List<String> atomNames) {
        this.atomNames = atomNames;
        return this;
    }

    public List<String> getResNames() {
        return resNames;
    }

    public PantanoResidue setResNames(List<String> resNames) {
        this.resNames = resNames;
        return this;
    }
    
    
    
}
