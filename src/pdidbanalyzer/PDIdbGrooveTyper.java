/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assignes a PDIdbGrooveType to DNA atoms
 * @author andreas
 */
public class PDIdbGrooveTyper {
    
    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(PDIdbAtomTyper.class);
    
    /** The PDB polymer */
    private IPDBPolymer pdbPolymer;

    public PDIdbGrooveTyper setPdbPolymer(IPDBPolymer pdbPolymer) {
        this.pdbPolymer = pdbPolymer;
        return this;
    }

    public IPDBPolymer getPdbPolymer() {
        return pdbPolymer;
    }
    
    public void run() {
        // Loop over all atoms
        for (IAtom atom : pdbPolymer.atoms()) {
            IPDBAtom pdbAtom = (IPDBAtom) atom;
            
            // Backbone "B"
            if ("OP1".equals(pdbAtom.getName()) ||
                "OP2".equals(pdbAtom.getName()) ||
                "OP3".equals(pdbAtom.getName()) ||
                "P".equals(pdbAtom.getName()) ||
                "O5'".equals(pdbAtom.getName()) ||
                "C1'".equals(pdbAtom.getName()) ||
                "C2'".equals(pdbAtom.getName()) ||
                "C3'".equals(pdbAtom.getName()) ||
                "C4'".equals(pdbAtom.getName()) ||
                "C5'".equals(pdbAtom.getName()) ||
                "O3'".equals(pdbAtom.getName())) {
                                
                pdbAtom.setProperty(PDIdbGrooveType.class, PDIdbGrooveType.B);
            }
            
            // Major groove "W"
            else if (("C5".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("O6".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("O6".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("N7".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("N7".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("C8".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("C8".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                    
                     ("C5".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("N6".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("N6".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("N7".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("N7".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("C8".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("C8".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                    
                     ("C4".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                     ("N4".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("N4".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                    
                     ("C4".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("O4".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("O4".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("C5".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("C6".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("C7".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("C7".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName()))) {

                pdbAtom.setProperty(PDIdbGrooveType.class, PDIdbGrooveType.W);
            }
            
            // Minor groove "S"
            else if (("C2".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("C2".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("N2".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("N2".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                    
                     ("N1".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("N1".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("C2".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("C2".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("C4".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                    
                     ("C2".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("C2".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                     ("O2".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("O2".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                    
                     ("C2".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("C2".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("O2".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("O2".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName()))) {

                pdbAtom.setProperty(PDIdbGrooveType.class, PDIdbGrooveType.S);
            }
            
            // Not assigned "N"
            else if (("N1".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("N1".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                     ("N9".equals(pdbAtom.getName()) && "DG".equals(pdbAtom.getResName())) ||
                     ("N9".equals(pdbAtom.getName()) && "G".equals(pdbAtom.getResName())) ||
                    
                     ("N9".equals(pdbAtom.getName()) && "DA".equals(pdbAtom.getResName())) ||
                     ("N9".equals(pdbAtom.getName()) && "A".equals(pdbAtom.getResName())) ||
                    
                     ("N1".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("N1".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "DC".equals(pdbAtom.getResName())) ||
                     ("N3".equals(pdbAtom.getName()) && "C".equals(pdbAtom.getResName())) ||
                    
                     ("N1".equals(pdbAtom.getName()) && "DT".equals(pdbAtom.getResName())) ||
                     ("N1".equals(pdbAtom.getName()) && "T".equals(pdbAtom.getResName()))) {

                pdbAtom.setProperty(PDIdbGrooveType.class, PDIdbGrooveType.N);
            }
        }
    }
    

}
