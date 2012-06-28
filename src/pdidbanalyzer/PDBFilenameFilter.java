/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filters .pdb, .ent, and .gz files
 * @author archvile18
 */
public class PDBFilenameFilter implements FilenameFilter {
    
    @Override
    public boolean accept(File dir, String name) {
        return (name.endsWith(".pdb") || name.endsWith(".ent") || name.endsWith(".gz"));
    }
}
