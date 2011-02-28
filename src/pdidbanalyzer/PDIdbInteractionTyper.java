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
public class PDIdbInteractionTyper implements IInteractionTyper {
    
    /**
     * Returns true if the atom is a DNA base edge nitrogen acceptor
     * @param atom an atom
     * @return true if the atom is a DNA base edge nitrogen acceptor
     */
    private boolean isDnaBaseEdgeNitrogenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if (PDIdbDNAAtomType.Imine.equals(atom.getProperty(IAtomType.class)))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a DNA base edge oxygen acceptor
     * @param atom an atom
     * @return true if the atom is a DNA base edge oxygen acceptor
     */
    private boolean isDnaBaseEdgeOxygenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if (PDIdbDNAAtomType.CarbonylO.equals(atom.getProperty(IAtomType.class)))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a DNA base edge nitrogen donor
     * @param atom an atom
     * @return true if the atom is a DNA base edge nitrogen donor
     */
    private boolean isDnaBaseEdgeNitrogenDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if (PDIdbDNAAtomType.PrmN.equals(atom.getProperty(IAtomType.class)) ||
                PDIdbDNAAtomType.AmideN.equals(atom.getProperty(IAtomType.class)))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a DNA backbone oxygen acceptor
     * @param atom an atom
     * @return true if the atom is a DNA backbone oxygen acceptor
     */
    private boolean isDnaBackboneOxygenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbDNAAtomType type = (PDIdbDNAAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbDNAAtomType.OP1OP2OP3.equals(type) ||
                PDIdbDNAAtomType.O3PrO5Pr.equals(type) ||
                PDIdbDNAAtomType.O3Pr.equals(type) ||
                PDIdbDNAAtomType.O4Pr.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a DNA base edge carbon (CH) donor
     * @param atom an atom
     * @return true if the atom is a DNA base edge carbon (CH) donor
     */
    private boolean isDnaBaseEdgeCarbonDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbDNAAtomType type = (PDIdbDNAAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbDNAAtomType.C8.equals(type) ||
                PDIdbDNAAtomType.AC2.equals(type) ||
                PDIdbDNAAtomType.CC5.equals(type) ||
                PDIdbDNAAtomType.PyC6.equals(type) ||
                PDIdbDNAAtomType.TC7.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is DNA and an ion (free phosphate oxygens)
     * @param atom an atom
     * @return true if the DNA atom is DNA and an ion
     */
    private boolean isDnaAnion(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if (PDIdbDNAAtomType.OP1OP2OP3.equals(atom.getProperty(IAtomType.class)))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is DNA and a carbon
     * @param atom an atom
     * @return true if the DNA atom is DNA and a carbon
     */
    private boolean isDnaCarbon(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbDNAAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if ("C".equals(atom.getSymbol()))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain nitrogen donor
     * @param atom an atom
     * @return true if the atom is a protein side chain nitrogen donor
     */
    private boolean isProteinSideChainNitrogenDonor(IAtom atom) {
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
                PDIdbProteinAtomType.HIS_NE2.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain oxygen donor
     * @param atom an atom
     * @return true if the atom is a protein side chain oxygen donor
     */
    private boolean isProteinSideChainOxygenDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.SER_THR_O.equals(type) ||
                PDIdbProteinAtomType.TYR_O.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain oxygen acceptor
     * @param atom an atom
     * @return true if the atom is a protein side chain oxygen acceptor
     */
    private boolean isProteinSideChainOxygenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.SER_THR_O.equals(type) ||
                PDIdbProteinAtomType.TYR_O.equals(type) ||
                PDIdbProteinAtomType.ASN_GLN_O.equals(type) ||
                (PDIdbProteinAtomType.ASP_GLU_O.equals(type) && !"OXT".equals(((IPDBAtom) atom).getName())))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein backbone nitrogen donor
     * @param atom an atom
     * @return true if the atom is a protein backbone nitrogen donor
     */
    private boolean isProteinBackboneNitrogenDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.N.equals(type) ||
            PDIdbProteinAtomType.PRO_N.equals(type) ||
            // TO-DO: The use of protein atom type LYS_N for the terminal back
            // bone amino nitrogen is not consistent since lysine NZ is a side
            // chain atom and the N-terminal N is a back bone atom.
            (PDIdbProteinAtomType.LYS_N.equals(type) && "N".equals(((IPDBAtom) atom).getName())))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein backbone oxygen acceptor
     * @param atom an atom
     * @return true if the atom is a protein backbone oxygen acceptor
     */
    private boolean isProteinBackboneOxygenAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.O.equals(type) ||
                // TO-DO: Use of protein side chain atom type ASP_GLU_O for
                // backbone C-terminal OXT is not consistent.
                (PDIdbProteinAtomType.ASP_GLU_O.equals(type) && "OXT".equals(((IPDBAtom) atom).getName())))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain sulphur donor
     * @param atom an atom
     * @return true if the atom is a protein side chain sulphur donor
     */
    private boolean isProteinSideChainSulphurDonor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if (PDIdbProteinAtomType.CYS_S.equals((PDIdbProteinAtomType) atom.getProperty(IAtomType.class)))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a protein side chain sulphur acceptor
     * @param atom an atom
     * @return true if the atom is a protein side chain sulphur acceptor
     */
    private boolean isProteinSideChainSulphurAcceptor(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        if (PDIdbProteinAtomType.CYS_S.equals(type) ||
                PDIdbProteinAtomType.MET_S.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is a proteinic and a cation. The present
     * definition includes the side chain nitrogens of lysine and arginine and
     * the back bone terminal NH3+ group.
     * @param atom an atom
     * @return true if the atom is a proteinic and a cation
     */
    private boolean isProteinCation(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        PDIdbProteinAtomType type = (PDIdbProteinAtomType) atom.getProperty(IAtomType.class);
        // TO-DO: As of now protein atom type LYS_N includes the terminal back
        // bone NH3+ group. But that might change in the future.
        if (PDIdbProteinAtomType.LYS_N.equals(type) ||
                PDIdbProteinAtomType.ARG_NH.equals(type) ||
                PDIdbProteinAtomType.ARG_NE.equals(type))
            return true;

        return false;
    }
    
    /**
     * Returns true if the atom is proteinic and a carbon
     * @param atom an atom
     * @return true if the DNA atom is proteinic and a carbon
     */
    private boolean isProteinCarbon(IAtom atom) {
        if (atom.getProperty(IAtomType.class) == null)
            return false;
        if (!PDIdbProteinAtomType.class.equals(atom.getProperty(IAtomType.class).getClass()))
            return false;
        if ("C".equals(atom.getSymbol()))
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
    public PDIdbInteractionType getInteractionType(IAtom atom1, IAtom atom2) {
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
            
        // Type 1: DBE-PSC: NA-ND
        if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_NA_ND;
        }

        // Type 2: DBE-PSC: NA-OD
        else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_NA_OD;
        }

        // Type 3: DBE-PSC: OA-ND
        else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_OA_ND;
        }

        // Type 4: DBE-PSC: OA-OD
        else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_OA_OD;
        }

        // Type 5: DBE-PSC: ND-OA
        else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinSideChainOxygenAcceptor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_ND_OA;
        }

        // Type 6: DBE-PBB: NA-ND
        else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PBB_NA_ND;
        }

        // Type 7: DBE-PBB: ND-OA
        else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinBackboneOxygenAcceptor(atom2)) {
            return PDIdbInteractionType.DBE_PBB_ND_OA;
        }

        // Type 8: DBE-PBB: OA-ND
        else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2)) {
            return PDIdbInteractionType.DBE_PBB_OA_ND;
        }

        // Type 9: DBB-PSC: OA-ND
        // The charged H-bonds between phosphate and LYS/ARG are classified as
        // PDIdbInteractionType.ION (type 18)
        else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainNitrogenDonor(atom2) &&
                    !(isDnaAnion(atom1) && isProteinCation(atom2))) {
            return PDIdbInteractionType.DBB_PSC_OA_ND;
        }

        // Type 10: DBB-PSC: OA-OD
        else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainOxygenDonor(atom2)) {
            return PDIdbInteractionType.DBB_PSC_OA_OD;
        }

        // Type 11: DBB-PBB: OA-ND
        else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinBackboneNitrogenDonor(atom2) &&
                    !(isDnaAnion(atom1) && isProteinCation(atom2))) {
            return PDIdbInteractionType.DBB_PBB_OA_ND;
        }

        // Type 12: DBB-PSC: OA-SD
        else if (isDnaBackboneOxygenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return PDIdbInteractionType.DBB_PSC_OA_SD;
        }

        // Type 13: DBE_PSC: NA-SD
        else if (isDnaBaseEdgeNitrogenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_NA_SD;
        }

        // Type 14: DBE-PSC: OA-SD
        else if (isDnaBaseEdgeOxygenAcceptor(atom1) && isProteinSideChainSulphurDonor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_OA_SD;
        }

        // Type 15: DBE-PSC: ND-SA
        else if (isDnaBaseEdgeNitrogenDonor(atom1) && isProteinSideChainSulphurAcceptor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_ND_SA;
        }

        // Type 16: DBE-PSC: CD-OA
        else if (isDnaBaseEdgeCarbonDonor(atom1) && isProteinSideChainOxygenAcceptor(atom2)) {
            return PDIdbInteractionType.DBE_PSC_CD_OA;
        }

        // Type 17: DBE-PBB: CD-OA
        else if (isDnaBaseEdgeCarbonDonor(atom1) && isProteinBackboneOxygenAcceptor(atom2)) {
            return PDIdbInteractionType.DBE_PBB_CD_OA;
        }

        // Type 18: Ionic interaction: (-)...(+)
        else if (isDnaAnion(atom1) && isProteinCation(atom2)) {
            return PDIdbInteractionType.ION;
        }

        // Type 19: Hydrophobic interactions
        else if (isDnaCarbon(atom1) && isProteinCarbon(atom2)) {
            return PDIdbInteractionType.HPH;
        }

        // Type 20: All other interaction
        else {
            return PDIdbInteractionType.OTHER;
        }
    }

}
