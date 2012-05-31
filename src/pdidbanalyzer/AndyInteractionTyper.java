/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBAtom;

/**
 *
 * @author archvile18
 */
public class AndyInteractionTyper extends PDIdbInteractionTyper {
    
    /** An HBPlus object used for H-bond validation */
    private HBPlus hbPlus;

    /**
     * Return the HBPlus object used for H-bond validation
     * @return
     */
    public HBPlus getHBPlus() {
        return hbPlus;
    }

    /**
     * Set an HBPlus object for H-bond validation
     * @param hbPlus
     * @return this object
     */
    public AndyInteractionTyper setHBPlus(HBPlus hbPlus) {
        this.hbPlus = hbPlus;
        return this;
    }
    
    /**
     * Returns true if the atom is a protein side chain nitrogen acceptor
     * @param atom an atom
     * @return true if the atom is a protein side chain nitrogen acceptor
     */
    protected boolean isProteinSideChainNitrogenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.HIS_ND1.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain nitrogen donor
     * @param atom an atom
     * @return true if the atom is a protein side chain nitrogen donor
     */
    @Override
    protected boolean isProteinSideChainNitrogenDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.ASN_GLN_N.equals(type) ||
                PDIdbProteinAtomType.TRP_N.equals(type) ||
                (PDIdbProteinAtomType.LYS_N.equals(type) && !"N".equals(((IPDBAtom) atom).getName())) ||
                PDIdbProteinAtomType.ARG_NH.equals(type) ||
                PDIdbProteinAtomType.ARG_NE.equals(type) ||
                PDIdbProteinAtomType.HIS_NE2.equals(type) ||
                PDIdbProteinAtomType.HIS_ND1.equals(type))    // Added, because ND1 can be protonated
            return true;

        return false;
    }

    /**
     * Returns true if the atom is a DNA backbone oxygen donor
     * @param atom an atom
     * @return true if the atom is a DNA backbone oxygen donor
     */
    protected boolean isDnaBackboneOxygenDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbDNAAtomType type = (PDIdbDNAAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbDNAAtomType.O3Pr.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns the type of the interaction between atom1 and atom2. Returns null
     * if no appropriate type could be assigned.
     * @param atom1 first atom
     * @param atom2 second atom
     * @return the interaction type
     */
    @Override
    public IInteractionType getInteractionType(IAtom atom1, IAtom atom2) {
        // Ensure first atom is DNA
        if (!atom1.getProperty(IAtomType.class).getClass().equals(PDIdbDNAAtomType.class)) {
            // Switch second atom to first atom if it is DNA
            if (atom2.getProperty(IAtomType.class).getClass().equals(PDIdbDNAAtomType.class)) {
                IAtom tempAtom = atom1;
                atom1 = atom2;
                atom2 = tempAtom;
            } else {
                return null;
            }
        }
        
        // Ensure second atom is Protein
        if (!atom2.getProperty(IAtomType.class).getClass().equals(PDIdbProteinAtomType.class)) {
            return null;
        }

        // Canonical H-bond interactions
        
        // Perfrom HBPLUS pre-check: Only consider H-bonds detected by HBPLUS
        if (hbPlus.isHBond((IPDBAtom)atom1,(IPDBAtom) atom2)) {

            // Type 1: DBE-PSC: NA-ND
            if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2)) {
                return AndyInteractionType.DBE_PSC_NA_ND;
            }

            // Type 2: DBE-PSC: NA-OD
            else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
                return AndyInteractionType.DBE_PSC_NA_OD;
            }

            // Type 3: DBE-PSC: OA-ND
            else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2)) {
                return AndyInteractionType.DBE_PSC_OA_ND;
            }

            // Type 4: DBE-PSC: OA-OD
            else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
                return AndyInteractionType.DBE_PSC_OA_OD;
            }

            // Type 5: DBE-PSC: ND-OA
            else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinSideChainOxygenAcceptor(atom2)) {
                return AndyInteractionType.DBE_PSC_ND_OA;
            }

            // Type 6: DBE-PBB: NA-ND
            else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2)) {
                return AndyInteractionType.DBE_PBB_NA_ND;
            }

            // Type 7: DBE-PBB: ND-OA
            else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinBackboneOxygenAcceptor(atom2)) {
                return AndyInteractionType.DBE_PBB_ND_OA;
            }

            // Type 8: DBE-PBB: OA-ND
            else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2)) {
                return AndyInteractionType.DBE_PBB_OA_ND;
            }

            // Type 9: DBB-PSC: OA-ND
            // The charged H-bonds between phosphate and LYS/ARG are classified as
            // AndyInteractionType.ION (type 18)
            else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2) &&
                        !(isDnaAnion(atom1) && isProteinCation(atom2))) {
                return AndyInteractionType.DBB_PSC_OA_ND;
            }

            // Type 10: DBB-PSC: OA-OD
            else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
                return AndyInteractionType.DBB_PSC_OA_OD;
            }

            // Type 11: DBB-PBB: OA-ND
            else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2) &&
                        !(isDnaAnion(atom1) && isProteinCation(atom2))) {
                return AndyInteractionType.DBB_PBB_OA_ND;
            }

            // Type 21: DBE-PSC: ND-NA (His ND1)
            else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinSideChainNitrogenAcceptor(atom2)) {
                return AndyInteractionType.DBE_PSC_ND_NA;
            }

            //Type 22: DBB-PSC: OD-NA (DNA O3' terminal)
            else if (isDnaBackboneOxygenDonor(atom1) && isProteinSideChainNitrogenAcceptor(atom2)) {
                return AndyInteractionType.DBB_PSC_OD_NA;
            }

            // Type 23: DBB-PSC: OD-OA (DNA O3' terminal)
            else if (isDnaBackboneOxygenDonor(atom1) && isProteinSideChainOxygenAcceptor(atom2)) {
                return AndyInteractionType.DBB_PSC_OD_OA;
            }

            //Type 24: DBB-PBB: OD-OA (DNA O3' terminal)
            else if (isDnaBackboneOxygenDonor(atom1) && isProteinBackboneOxygenAcceptor(atom2)) {
                return AndyInteractionType.DBB_PBB_OD_OA;
            }
        }
            
        // Sulphur H-bond interactions
        
        // Type 12: DBB-PSC: OA-SD
        if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return AndyInteractionType.DBB_PSC_OA_SD;
        }

        // Type 13: DBE_PSC: NA-SD
        else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return AndyInteractionType.DBE_PSC_NA_SD;
        }

        // Type 14: DBE-PSC: OA-SD
        else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return AndyInteractionType.DBE_PSC_OA_SD;
        }

        // Type 15: DBE-PSC: ND-SA
        else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinSideChainSulphurAcceptor(atom2)) {
            return AndyInteractionType.DBE_PSC_ND_SA;
        }

        // Type 25: DBB-PSC: OD-SA (DNA O3' terminal)
        else if (isDnaBackboneOxygenDonor(atom1) && isProteinSideChainSulphurAcceptor(atom2)) {
            return AndyInteractionType.DBB_PSC_OD_SA;
        }

        // Weak carbon H-bond interactions
        
        // Type 16: DBE-PSC: CD-OA
        else if (isDnaBaseEdgeCarbonDonor(atom1) && isProteinSideChainOxygenAcceptor(atom2)) {
            return AndyInteractionType.DBE_PSC_CD_OA;
        }

        // Type 17: DBE-PBB: CD-OA
        else if (isDnaBaseEdgeCarbonDonor(atom1) && isProteinBackboneOxygenAcceptor(atom2)) {
            return AndyInteractionType.DBE_PBB_CD_OA;
        }

        // Other interactions
        
        // Type 18: Ionic interaction: (-)...(+)
        else if (isDnaAnion(atom1) && isProteinCation(atom2)) {
            return AndyInteractionType.ION;
        }

        // Type 26: (-)...(-): Ionic bond assuming invisible cation.
        // Now distinct from type 18 (+)...(-)
        else if (isDnaAnion(atom1) && isProteinAnion(atom2)) {
            return AndyInteractionType.ION_NEG_NEG;
        }

        // Type 19: Hydrophobic interactions
        else if (isDnaCarbon(atom1) && isProteinCarbon(atom2)) {
            return AndyInteractionType.HPH;
        }

        // Type 20: All other interaction
        else {
            return AndyInteractionType.OTHER;
        }
    }

}
