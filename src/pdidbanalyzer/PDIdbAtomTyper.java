/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.util.ArrayList;
import java.util.List;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assign types to DNA & protein atoms according to PDIdb rules
 * @author archvile18
 */
public class PDIdbAtomTyper {
    
    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(PDIdbAtomTyper.class);
    
    /** The PDB polymer */
    private IPDBPolymer pdbPolymer;

    /** The name of the PDB file */
    private String filename;

    public PDIdbAtomTyper(IPDBPolymer pdbPolymer) {
        this.pdbPolymer = pdbPolymer;
    }

    public PDIdbAtomTyper() {
    }

    public PDIdbAtomTyper setPdbPolymer(IPDBPolymer pdbPolymer) {
        this.pdbPolymer = pdbPolymer;
        return this;
    }

    public IPDBPolymer getPdbPolymer() {
        return pdbPolymer;
    }

    public PDIdbAtomTyper setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public String getFilename() {
        return filename;
    }
    
    private boolean isNucleic(IPDBAtom atom) {
        if ("DG".equals(atom.getResName()) ||
            "G".equals(atom.getResName()) ||
            "DC".equals(atom.getResName()) ||
            "C".equals(atom.getResName()) ||
            "DA".equals(atom.getResName()) ||
            "A".equals(atom.getResName()) ||
            "DT".equals(atom.getResName()) ||
            "T".equals(atom.getResName())) {
            return true;
        }
        return false;
    }
    
    private boolean isPeptidic(IPDBAtom atom) {
        if ("ALA".equals(atom.getResName()) ||
            "ARG".equals(atom.getResName()) ||
            "ASN".equals(atom.getResName()) ||
            "ASP".equals(atom.getResName()) ||
            "CYS".equals(atom.getResName()) ||
            "GLU".equals(atom.getResName()) ||
            "GLN".equals(atom.getResName()) ||
            "GLY".equals(atom.getResName()) ||
            "HIS".equals(atom.getResName()) ||
            "ILE".equals(atom.getResName()) ||
            "LEU".equals(atom.getResName()) ||
            "LYS".equals(atom.getResName()) ||
            "MET".equals(atom.getResName()) ||
            "PHE".equals(atom.getResName()) ||
            "PRO".equals(atom.getResName()) ||
            "SER".equals(atom.getResName()) ||
            "THR".equals(atom.getResName()) ||
            "TRP".equals(atom.getResName()) ||
            "TYR".equals(atom.getResName()) ||
            "VAL".equals(atom.getResName())) {
            return true;
        }
        return false;
    }
    
    private boolean isWater(IPDBAtom atom) {
        if ("HOH".equals(atom.getResName())) {
            return true;
        }
        return false;
    }
    
    public void run() {
        // Loop over all chains (called strands in CDK)
        for (String chainId: pdbPolymer.getStrandNames()) {
            log.debug("Chain: {}", chainId);
            boolean isFirstResidue = true;
            boolean isLastResidue = false;
            int counter = 0;
            // Loop over all monomers (amino acids, bases, etc.)
            List<String> monomerNames = new ArrayList<String>(pdbPolymer.getStrand(chainId).getMonomerNames());
            for (String monomerId : monomerNames) {
                log.debug("Residue: {}", monomerId);
                counter++;
                if (counter == pdbPolymer.getStrand(chainId).getMonomerCount() + 1) {
                    isLastResidue = true;
                
                // Set isLastResidue true if a water entry follows
                } else if (isWater((IPDBAtom) pdbPolymer.getStrand(chainId).getMonomer(monomerNames.get(counter)).getAtom(0))) {
                    isLastResidue = true;
                }
                log.debug("#atoms: {}", pdbPolymer.getStrand(chainId).getMonomer(monomerId).getAtomCount());
                // Loop over all atoms
                for (IAtom atom : pdbPolymer.getStrand(chainId).getMonomer(monomerId).atoms()) {
                    IPDBAtom pdbAtom = (IPDBAtom) atom;
                    
                    // DNA atom typing
                    if (isNucleic(pdbAtom)) {
                        
                        //log.debug("Heavy: {}", (pdbPolymer.getConnectedAtomsCount(pdbAtom) - AtomContainerManipulator.countExplicitHydrogens(pdbPolymer, pdbAtom)));
                        // Type 1: free phosphate oxygens OP1, OP2 and OP3 (5' terminus)
                        if     ("OP1".equals(pdbAtom.getName()) ||
                                "OP2".equals(pdbAtom.getName()) ||
                                "OP3".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.OP1OP2OP3);
                        }
                        // Type 2: phosphate phosphorus
                        else if ("P".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.P);
                        }
                        // Type 3: ribose O3' and O5' (phosphoester)
                        // The heavy neighbor count check is required b/c of Type 6
                        else if (("O3'".equals(pdbAtom.getName()) && !isLastResidue) ||
                                 "O5'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.O3PrO5Pr);
                        }
                        // Type 4: ribose C5'
                        else if ("C5'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.C5Pr);
                        }
                        // Type 5: ribose C3' and C4'
                        else if ("C3'".equals(pdbAtom.getName()) ||
                                 "C4'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.C3PrC4Pr);
                        }
                        // Type 6: ribose O3' (3' terminus)
                        else if ("O3'".equals(pdbAtom.getName()) && isLastResidue) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.O3Pr);
                        }
                        // Type 7: ribose C2'
                        else if ("C2'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.C2Pr);
                        }
                        // Type 8: ribose C1'
                        else if ("C1'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.C1Pr);
                        }
                        // Type 9: ribose O4' (ring oxygen)
                        else if ("O4'".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.O4Pr);
                        }
                        // Type 10: purine base N9 and pyrimidine base N1 (tertiary N)
                        else if ( "N9".equals(pdbAtom.getName()) ||
                                 ("N1".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName()))) ||
                                 ("N1".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.TertN);
                        }
                        // Type 11: purine base C8
                        else if ("C8".equals(pdbAtom.getName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.C8);
                        }
                        // Type 12: adenine N1, purine base N3 and N7, and cytosine N3 (imine)
                        else if (("N1".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName()))) ||
                                 ("N3".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("N3".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName()))) ||
                                 ("N3".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName()))) ||
                                 ("N7".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("N7".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.Imine);
                        }
                        // Type 13: purine base C5
                        else if (("C5".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("C5".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.PuC5);
                        }
                        // Type 14: purine base C4
                        else if (("C4".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("C4".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.PuC4);
                        }
                        // Type 15: adenine C2
                        else if (("C2".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.AC2);
                        }
                        // Type 16: adenine C6 and cytosine C4
                        else if (("C6".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName()))) ||
                                 ("C4".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.AC6CC4);
                        }
                        // Type 17: guanine N2, adenine N6 and cytosine N4 (primary N)
                        else if (("N2".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("N6".equals(pdbAtom.getName()) && ("DA".equals(pdbAtom.getResName()) || "A".equals(pdbAtom.getResName()))) ||
                                 ("N4".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.PrmN);
                        }
                        // Type 18: guanine C2
                        else if (("C2".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.GC2);
                        }
                        // Type 19: guanine C6 and thymine C4
                        else if (("C6".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("C4".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.GC6TC4);
                        }
                        // Type 20: guanine O6, cytosine O2, and thymine O2 and O4 (carbonyl O)
                        else if (("O6".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("O2".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName()))) ||
                                 ("O2".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName()))) ||
                                 ("O4".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.CarbonylO);
                        }
                        // Type 21: pyrimidine base C2
                        else if (("C2".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName()))) ||
                                 ("C2".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.PyC2);
                        }
                        // Type 22: cytosine C5
                        else if (("C5".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.CC5);
                        }
                        // Type 23: pyrimidine base C6
                        else if (("C6".equals(pdbAtom.getName()) && ("DC".equals(pdbAtom.getResName()) || "C".equals(pdbAtom.getResName()))) ||
                                 ("C6".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.PyC6);
                        }
                        // Type 24: guanine N1 and thymine N3 (amide N)
                        else if (("N1".equals(pdbAtom.getName()) && ("DG".equals(pdbAtom.getResName()) || "G".equals(pdbAtom.getResName()))) ||
                                 ("N3".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.AmideN);
                        }
                        // Type 25: thymine C5
                        else if (("C5".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.TC5);
                        }
                        // Type 26: thymine C7 (methyl group), also named C5M
                        // according to older PDB file formats
                        else if (("C7".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName()))) ||
                                 ("C5M".equals(pdbAtom.getName()) && ("DT".equals(pdbAtom.getResName()) || "T".equals(pdbAtom.getResName())))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbDNAAtomType.TC7);
                        }
                        
                    // Protein atom typing
                    } else if (isPeptidic(pdbAtom)) {
                        
                        // Type 1: C-alpha (without Gly)
                        if ( "CA".equals(pdbAtom.getName()) &&
                            ("ALA".equals(pdbAtom.getResName()) ||
                             "ARG".equals(pdbAtom.getResName()) ||
                             "ASN".equals(pdbAtom.getResName()) ||
                             "ASP".equals(pdbAtom.getResName()) ||
                             "CYS".equals(pdbAtom.getResName()) ||
                             "GLU".equals(pdbAtom.getResName()) ||
                             "GLN".equals(pdbAtom.getResName()) ||
                             //"GLY".equals(pdbAtom.getResName()) ||
                             "HIS".equals(pdbAtom.getResName()) ||
                             "ILE".equals(pdbAtom.getResName()) ||
                             "LEU".equals(pdbAtom.getResName()) ||
                             "LYS".equals(pdbAtom.getResName()) ||
                             "MET".equals(pdbAtom.getResName()) ||
                             "PHE".equals(pdbAtom.getResName()) ||
                             "PRO".equals(pdbAtom.getResName()) ||
                             "SER".equals(pdbAtom.getResName()) ||
                             "THR".equals(pdbAtom.getResName()) ||
                             "TRP".equals(pdbAtom.getResName()) ||
                             "TYR".equals(pdbAtom.getResName()) ||
                             "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CA);
                        }
                        // Type 2: Glycine C-alpha only
                        else if ( "CA".equals(pdbAtom.getName()) &&
                                  "GLY".equals(pdbAtom.getResName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.GLY_CA);
                        }
                        // Type 3: Backbone amino N (without Pro and N-terminus)
                        else if ( "N".equals(pdbAtom.getName()) && !isFirstResidue &&
                                 ("ALA".equals(pdbAtom.getResName()) ||
                                  "ARG".equals(pdbAtom.getResName()) ||
                                  "ASN".equals(pdbAtom.getResName()) ||
                                  "ASP".equals(pdbAtom.getResName()) ||
                                  "CYS".equals(pdbAtom.getResName()) ||
                                  "GLU".equals(pdbAtom.getResName()) ||
                                  "GLN".equals(pdbAtom.getResName()) ||
                                  "GLY".equals(pdbAtom.getResName()) ||
                                  "HIS".equals(pdbAtom.getResName()) ||
                                  "ILE".equals(pdbAtom.getResName()) ||
                                  "LEU".equals(pdbAtom.getResName()) ||
                                  "LYS".equals(pdbAtom.getResName()) ||
                                  "MET".equals(pdbAtom.getResName()) ||
                                  "PHE".equals(pdbAtom.getResName()) ||
                                  //"PRO".equals(atom.getResName()) ||
                                  "SER".equals(pdbAtom.getResName()) ||
                                  "THR".equals(pdbAtom.getResName()) ||
                                  "TRP".equals(pdbAtom.getResName()) ||
                                  "TYR".equals(pdbAtom.getResName()) ||
                                  "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.N);
                        }                        
                        // Type 4: Backbone carbonyl C
                        else if ( "C".equals(pdbAtom.getName()) &&
                                 ("ALA".equals(pdbAtom.getResName()) ||
                                  "ARG".equals(pdbAtom.getResName()) ||
                                  "ASN".equals(pdbAtom.getResName()) ||
                                  "ASP".equals(pdbAtom.getResName()) ||
                                  "CYS".equals(pdbAtom.getResName()) ||
                                  "GLU".equals(pdbAtom.getResName()) ||
                                  "GLN".equals(pdbAtom.getResName()) ||
                                  "GLY".equals(pdbAtom.getResName()) ||
                                  "HIS".equals(pdbAtom.getResName()) ||
                                  "ILE".equals(pdbAtom.getResName()) ||
                                  "LEU".equals(pdbAtom.getResName()) ||
                                  "LYS".equals(pdbAtom.getResName()) ||
                                  "MET".equals(pdbAtom.getResName()) ||
                                  "PHE".equals(pdbAtom.getResName()) ||
                                  "PRO".equals(pdbAtom.getResName()) ||
                                  "SER".equals(pdbAtom.getResName()) ||
                                  "THR".equals(pdbAtom.getResName()) ||
                                  "TRP".equals(pdbAtom.getResName()) ||
                                  "TYR".equals(pdbAtom.getResName()) ||
                                  "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.C);
                        }  
                        // Type 5: Backbone carbonyl O
                        else if ( "O".equals(pdbAtom.getName()) &&
                                 ("ALA".equals(pdbAtom.getResName()) ||
                                  "ARG".equals(pdbAtom.getResName()) ||
                                  "ASN".equals(pdbAtom.getResName()) ||
                                  "ASP".equals(pdbAtom.getResName()) ||
                                  "CYS".equals(pdbAtom.getResName()) ||
                                  "GLU".equals(pdbAtom.getResName()) ||
                                  "GLN".equals(pdbAtom.getResName()) ||
                                  "GLY".equals(pdbAtom.getResName()) ||
                                  "HIS".equals(pdbAtom.getResName()) ||
                                  "ILE".equals(pdbAtom.getResName()) ||
                                  "LEU".equals(pdbAtom.getResName()) ||
                                  "LYS".equals(pdbAtom.getResName()) ||
                                  "MET".equals(pdbAtom.getResName()) ||
                                  "PHE".equals(pdbAtom.getResName()) ||
                                  "PRO".equals(pdbAtom.getResName()) ||
                                  "SER".equals(pdbAtom.getResName()) ||
                                  "THR".equals(pdbAtom.getResName()) ||
                                  "TRP".equals(pdbAtom.getResName()) ||
                                  "TYR".equals(pdbAtom.getResName()) ||
                                  "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.O);
                        }                    
                        // Type 6: Side chain methyl carbon (without MET_METHYL)
                        else if (("CB".equals(pdbAtom.getName()) && "ALA".equals(pdbAtom.getResName())) ||
                                 ("CD1".equals(pdbAtom.getName()) && "ILE".equals(pdbAtom.getResName())) ||
                                 ("CG2".equals(pdbAtom.getName()) && "ILE".equals(pdbAtom.getResName())) ||
                                 ("CD1".equals(pdbAtom.getName()) && "LEU".equals(pdbAtom.getResName())) ||
                                 ("CD2".equals(pdbAtom.getName()) && "LEU".equals(pdbAtom.getResName())) ||
                                 ("CG2".equals(pdbAtom.getName()) && "THR".equals(pdbAtom.getResName())) ||
                                 ("CG1".equals(pdbAtom.getName()) && "VAL".equals(pdbAtom.getResName())) ||
                                 ("CG2".equals(pdbAtom.getName()) && "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.METHYL);
                        }
                        // Type 7: Side chain sp3 CH group (Leu, Ile, Val)
                        else if (("CB".equals(pdbAtom.getName()) && "ILE".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "LEU".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "VAL".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CH);
                        }
                        // Type 8: Side chain sp3 CH2 group (without PRO_CD, MET_CYS_CH2, LYS_CE, ARG_CD)
                        else if (//"ALA".equals(pdbAtom.getResName()) ||
                                 ("CB".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "ASN".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "ASP".equals(pdbAtom.getResName())) ||
                                  //"CYS".equals(pdbAtom.getResName()) ||
                                 ("CB".equals(pdbAtom.getName()) && "GLU".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "GLU".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "GLN".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "GLN".equals(pdbAtom.getResName())) ||
                                  //"GLY".equals(pdbAtom.getResName()) ||
                                 ("CB".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName())) ||
                                 ("CG1".equals(pdbAtom.getName()) && "ILE".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "LEU".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "LYS".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "LYS".equals(pdbAtom.getResName())) ||
                                 ("CD".equals(pdbAtom.getName()) && "LYS".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "MET".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "PRO".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "PRO".equals(pdbAtom.getResName())) ||
                                  //"SER".equals(pdbAtom.getResName()) ||
                                  //"THR".equals(pdbAtom.getResName()) ||
                                 ("CB".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CB".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName()))
                                  //"VAL".equals(pdbAtom.getResName()))
                                ) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CH2);
                        }
                        // Type 9: Side chain methionine S
                        else if (("SD".equals(pdbAtom.getName()) && "MET".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.MET_S);
                        }                        
                        // Type 10: Back bone proline amino N
                        else if ("N".equals(pdbAtom.getName()) && !isFirstResidue &&
                                    "PRO".equals(pdbAtom.getResName())) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.PRO_N);
                        }                        
                        // Type 11: Side chain C sp2 (zero H's)
                        else if (("CG".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CD2".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CH0_SP2);
                        }
                        // Type 12: Side chain CH sp2 (standard benzene C only)
                        else if (("CD1".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CD2".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CE1".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CE2".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CZ".equals(pdbAtom.getName()) && "PHE".equals(pdbAtom.getResName())) ||
                                 ("CZ2".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CH2".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CE3".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CZ3".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName())) ||
                                 ("CD1".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName())) ||
                                 ("CD2".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName())) ||
                                 ("CE1".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName())) ||
                                 ("CE2".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CH_SP2);
                        }
                        // Type 13: Side chain tryptophan C sp2 in 5-ring bound to CB
                        else if (("CG".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TRP_CG);
                        }
                        // Type 14: Side chain tryptophan C sp2 bound to indole N
                        else if (("CE2".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TRP_CE2);
                        }
                        // Type 15: Side chain serine CB
                        else if (("CB".equals(pdbAtom.getName()) && "SER".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.SER_CB);
                        }
                        // Type 16: Side chain serine/threonine hydroxyl O
                        else if (("OG".equals(pdbAtom.getName()) && "SER".equals(pdbAtom.getResName())) ||
                                 ("OG1".equals(pdbAtom.getName()) && "THR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.SER_THR_O);
                        }
                        // Type 17: Side chain threonine CB
                        else if (("CB".equals(pdbAtom.getName()) && "THR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.THR_CB);
                        }
                        // Type 18: Side chain asparagine/glutamine amide N
                        else if (("ND2".equals(pdbAtom.getName()) && "ASN".equals(pdbAtom.getResName())) ||
                                 ("NE2".equals(pdbAtom.getName()) && "GLN".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ASN_GLN_N);
                        }
                        // Type 19: Side chain cysteine thiol S
                        else if (("SG".equals(pdbAtom.getName()) && "CYS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.CYS_S);
                        }
                        // Type 20: Side chain lysine amino N and back bone N-terminus
                        else if (("NZ".equals(pdbAtom.getName()) && "LYS".equals(pdbAtom.getResName())) ||
                                 ("N".equals(pdbAtom.getName()) && isFirstResidue)) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.LYS_N);
                        }
                        // Type 21: Side chain arginine guanidine C
                        else if (("CZ".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CZ);
                        }
                        // Type 22: Side chain arginine guanidine terminal N
                        else if (("NH1".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName())) ||
                                 ("NH2".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_NH);
                        }
                        // Type 23: Side chain histidine C sp2 bound to CB
                        else if (("CG".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.HIS_CG);
                        }
                        // Type 24: Side chain CH sp2 bound to N sp2 in Trp (CD1) and His (CD2)
                        else if (("CD2".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName())) ||
                                 ("CD1".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TRP_HIS_CH);
                        }
                        // Type 25: Side chain histidine NE2 (NH, sp2, donor) bound to HIS_CD2
                        else if (("NE2".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.HIS_NE2);
                        }
                        // Type 26: Side chain histidine C sp2 bound to HIS_ND1 and HIS_NE2
                        else if (("CE1".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.HIS_CE1);
                        }
                        // Type 27: Side chain aspartate/glutamate carboxyl C
                        else if (("CG".equals(pdbAtom.getName()) && "ASP".equals(pdbAtom.getResName())) ||
                                 ("CD".equals(pdbAtom.getName()) && "GLU".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ASP_GLU_C);
                        }
                        // Type 28: Side chain aspartate/glutamate carboxyl O back bone and C-terminus (OXT)
                        else if (("OD1".equals(pdbAtom.getName()) && "ASP".equals(pdbAtom.getResName())) ||
                                 ("OD2".equals(pdbAtom.getName()) && "ASP".equals(pdbAtom.getResName())) ||
                                 ("OE1".equals(pdbAtom.getName()) && "GLU".equals(pdbAtom.getResName())) ||
                                 ("OE2".equals(pdbAtom.getName()) && "GLU".equals(pdbAtom.getResName())) ||
                                 ("OXT".equals(pdbAtom.getName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ASP_GLU_O);
                        }
                        // Type 29: Side chain methionine/cysteine CH2 sp3 bound to S
                        else if (("CB".equals(pdbAtom.getName()) && "CYS".equals(pdbAtom.getResName())) ||
                                 ("CG".equals(pdbAtom.getName()) && "MET".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.MET_CYS_CH2);
                        }
                        // Type 30: Side chain methionine CH3 sp3 bound to S
                        else if (("CE".equals(pdbAtom.getName()) && "MET".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.MET_METHYL);
                        }
                        // Type 31: Side chain thyrosine CH0 sp2 bound to thiol S
                        else if (("CZ".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TYR_CZ);
                        }
                        // Type 32: Side chain proline CH2 sp3 bound to back bone amino N (ring closure)
                        else if (("CD".equals(pdbAtom.getName()) && "PRO".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.PRO_CD);
                        }
                        // Type 33: Side chain asparagine/glutamine amide C
                        else if (("CG".equals(pdbAtom.getName()) && "ASN".equals(pdbAtom.getResName())) ||
                                 ("CD".equals(pdbAtom.getName()) && "GLN".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ASN_GLN_C);
                        }
                        // Type 34: Side chain asparagine/glutamine amide O
                        else if (("OD1".equals(pdbAtom.getName()) && "ASN".equals(pdbAtom.getResName())) ||
                                 ("OE1".equals(pdbAtom.getName()) && "GLN".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ASN_GLN_O);
                        }
                        // Type 35: Side chain lysine CH2 sp3 bound to amino N
                        else if (("CE".equals(pdbAtom.getName()) && "LYS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.LYS_CE);
                        }
                        // Type 36: Side chain arginine guanidine N (non terminal)
                        else if (("NE".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_NE);
                        }
                        // Type 37: Side chain arginine CH2 sp3 bound to ARG_NE
                        else if (("CD".equals(pdbAtom.getName()) && "ARG".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.ARG_CD);
                        }
                        // Type 38: Side chain histidine ND1 (-N=, sp2, acceptor) bound to HIS_CG
                        else if (("ND1".equals(pdbAtom.getName()) && "HIS".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.HIS_ND1);
                        }
                        // Type 39: Side chain tryptophan indole N
                        else if (("NE1".equals(pdbAtom.getName()) && "TRP".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TRP_N);
                        }
                        // Type 40: Side chain tyrosine hydroxyl O
                        else if (("OH".equals(pdbAtom.getName()) && "TYR".equals(pdbAtom.getResName()))) {
                            pdbAtom.setProperty(IAtomType.class, PDIdbProteinAtomType.TYR_O);
                        }
                    
                        
                    // Water atom typing
                    } else if (isWater(pdbAtom)) {
                        
                        pdbAtom.setProperty(IAtomType.class, WaterAtomType.WATER_O);

                    }
                    log.debug("{} {} {}", new Object[]{pdbAtom.getName(), pdbAtom.getResName(), pdbAtom.getProperty(IAtomType.class)});
                    if (pdbAtom.getProperty(IAtomType.class) == null && (isPeptidic(pdbAtom) || isNucleic(pdbAtom) || isWater(pdbAtom)) && !"H".equals(pdbAtom.getSymbol())) {
                        log.warn("Atom type = null. PDB file: {} Chain: {} Res: {} {} Atom: {} {} HETATM: {}", new Object[] {filename, pdbAtom.getChainID(), pdbAtom.getResName(), pdbAtom.getResSeq(), pdbAtom.getName(), pdbAtom.getSerial(), pdbAtom.getHetAtom()});
                    }
                }
                if (isFirstResidue && !monomerId.equals("")) {
                    isFirstResidue = false;
                }
            }
        }
    }
}
