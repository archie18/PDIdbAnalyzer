/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import java.text.DecimalFormat;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;

/**
 * Formats the interaction analysis output according to PDIdb standards
 * @author archvile18
 */
public class PDIdbOutputFormatter implements IOutputFormatter {
    
    private String separator = "\t";
    
    /**
     * Creates new PDIdb compliant instant
     */
    public PDIdbOutputFormatter() {
    }
    
    /**
     * Generates a formatted string representation of an interaction
     * @param interactionType
     * @param atom1
     * @param atom2
     * @param distance
     * @param pdbPolymer 
     */
    @Override
    public String format(IInteractionType interactionType, IPDBAtom atom1, IPDBAtom atom2, double distance, IPDBPolymer pdbPolymer) {
        // Ensure first atom is DNA
        if (atom1.getProperty(IAtomType.class).getClass().equals(PDIdbProteinAtomType.class) &&
                atom2.getProperty(IAtomType.class).getClass().equals(PDIdbDNAAtomType.class)) {
            IPDBAtom tempAtom = atom1;
            atom1 = atom2;
            atom2 = tempAtom;
        }
        
        // DecimalFormat for decimals without trailing zeros
        // String.format("%.3f") doesn't do the trick
        DecimalFormat df = new DecimalFormat("#.###");
        
        // Build a line of output
        StringBuilder stringBuilder = new StringBuilder();
        // First atom
        stringBuilder.append(atom1.getResName()).append(separator);
        stringBuilder.append(atom1.getResSeq()).append(separator);
        stringBuilder.append(atom1.getChainID()).append(separator);
        stringBuilder.append(atom1.getName()).append(separator);
        stringBuilder.append(atom1.getSymbol()).append(separator);
        stringBuilder.append(atom1.getSerial()).append(separator);
        stringBuilder.append(df.format(atom1.getPoint3d().x)).append(separator);
        stringBuilder.append(df.format(atom1.getPoint3d().y)).append(separator);
        stringBuilder.append(df.format(atom1.getPoint3d().z)).append(separator);
        // Second atom
        stringBuilder.append(atom2.getResName()).append(separator);
        stringBuilder.append(atom2.getResSeq()).append(separator);
        stringBuilder.append(atom2.getChainID()).append(separator);
        stringBuilder.append(atom2.getName()).append(separator);
        stringBuilder.append(atom2.getSymbol()).append(separator);
        stringBuilder.append(atom2.getSerial()).append(separator);
        stringBuilder.append(df.format(atom2.getPoint3d().x)).append(separator);
        stringBuilder.append(df.format(atom2.getPoint3d().y)).append(separator);
        stringBuilder.append(df.format(atom2.getPoint3d().z)).append(separator);
        // Interaction information
        stringBuilder.append(df.format(distance)).append(separator);
        stringBuilder.append(interactionType.getTypeInt()).append(separator);
        stringBuilder.append(interactionType.getInteractionClass().getTypeInt()).append(separator);
        // TO-DO: Add groove info here
        stringBuilder.append(atom1.getProperty(PDIdbGrooveType.class));
        return stringBuilder.toString();
    }

    @Override
    public String getHeader() {
        // Build the header
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# PDIdb analysis by PDIdbAnalyzer.jar v").append(PDIdbAnalyzer.version).append(" (original mode)").append('\n');
        stringBuilder.append("# by Andreas Schueller <aschueller@bio.puc.cl>. Based on the works of Tomas Norambuena and Francisco Melo, The Protein-DNA Interface database. BMC Bioinformatics 2010, 11, 262.").append('\n');
        stringBuilder.append("# Detailed effective interactions").append('\n');
        stringBuilder.append("# 1: DNA; 2: Protein").append('\n');
        // First atom
        stringBuilder.append("# ");
        stringBuilder.append("ResName_1").append(separator);
        stringBuilder.append("ResIndex_1").append(separator);
        stringBuilder.append("ChainID_1").append(separator);
        stringBuilder.append("AtmName_1").append(separator);
        stringBuilder.append("Element").append(separator);
        stringBuilder.append("AtmIndex").append(separator);
        stringBuilder.append("Coord_x").append(separator);
        stringBuilder.append("Coord_y").append(separator);
        stringBuilder.append("Coord_z").append(separator);
        // Second atom
        stringBuilder.append("ResName_2").append(separator);
        stringBuilder.append("ResIndex_2").append(separator);
        stringBuilder.append("ChainID_2").append(separator);
        stringBuilder.append("AtmName_2").append(separator);
        stringBuilder.append("Element").append(separator);
        stringBuilder.append("AtmIndex").append(separator);
        stringBuilder.append("Coord_x").append(separator);
        stringBuilder.append("Coord_y").append(separator);
        stringBuilder.append("Coord_z").append(separator);
        // Interaction information
        stringBuilder.append("Distance").append(separator);
        stringBuilder.append("Interaction_Type").append(separator);
        stringBuilder.append("Interaction_Class").append(separator);
        stringBuilder.append("Groove");
        
        return stringBuilder.toString();
    }
    
    

}
