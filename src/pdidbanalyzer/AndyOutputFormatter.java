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
public class AndyOutputFormatter extends PDIdbOutputFormatter {
    
    private String separator = "\t";
    
    /**
     * Creates new PDIdb compliant instant
     */
    public AndyOutputFormatter() {
    }

    @Override
    public String getHeader() {
        // Build the header
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# PDIdb analysis by PDIdbAnalyzer.jar v").append(PDIdbAnalyzer.version).append(" (extended mode)").append('\n');
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
