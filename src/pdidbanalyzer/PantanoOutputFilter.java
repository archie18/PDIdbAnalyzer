/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author andreas
 */
public class PantanoOutputFilter {
    
    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(PantanoOutputFilter.class);
    
    /** DNA map file */
    private String dnaMapFilename = "data/ADN_atoms.map";
    
    /** Protein map file */
    private String protMapFilename = "data/Prot_atoms.map";
    
    /** Pantano group mapped residues */
    private List<PantanoResidue> pantanoResidues;

    /**
     * Parses the Pantano group atom map files
     * @throws IOException 
     */
    public void parse() throws IOException {
        pantanoResidues = new ArrayList<PantanoResidue>();
        pantanoResidues.addAll(parse(dnaMapFilename));
        pantanoResidues.addAll(parse(protMapFilename));
    }
    
    /**
     * Parses a Pantano group atom map file
     * @throws IOException 
     */
    private List<PantanoResidue> parse(String filename) throws IOException {
        List<PantanoResidue> pantanoResidues = new ArrayList<PantanoResidue>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        PantanoResidue res = null;
        String line;
        while ((line = in.readLine()) != null) {
            line = line.trim();

            // Skip comments
            if ('#' == line.charAt(0)) {
                continue;
            }
            // Skip empty lines
            if ("".equals(line)) {
                continue;
            }

            // Trigger new buffer open and old buffer save
            if (line.startsWith("ALLNAME")) {
                // Save current buffer
                if (res != null) {
                    pantanoResidues.add(res);
                }
                // Initialize/clear buffer
                res = new PantanoResidue();
                
                // Add residue names
                List<String> resNames = Arrays.asList(line.split(" "));
                resNames.remove(0); // Removes ALLNAME
                res.setResNames(resNames);
                //Initialize list of atom names
                res.setAtomNames(new ArrayList<String>());
            }
            else if (res != null) {
                res.getAtomNames().addAll(Arrays.asList(line.split(" ")));
            }
        }
        // Save final buffer
        if (res != null) {
            pantanoResidues.add(res);
        }
        
        in.close();
        return pantanoResidues;
    }

}
