/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 * Defines PDIdb protein atom types. Ref: Melo & Feytmans, J. Mol. Biol. (1997) 267, 207-222
 * @author archvile18
 */
public enum PDIdbProteinAtomType implements IAtomType {
    
    /** Type 1: C-alpha (without Gly) */
    CA(1),
    
    /** Type 2: Glycine C-alpha only */
    GLY_CA(2),
    
    /** Type 3: Backbone amino N (without Pro and N-terminus) */
    N(3),
    
    /** Type 4: Backbone carbonyl C */
    C(4),
    
    /** Type 5: Backbone carbonyl O */
    O(5),
    
    /** Type 6: Side chain methyl carbon (without MET_METHYL) */
    METHYL(6),
    
    /** Type 7: Side chain sp3 CH group (Leu, Ile, Val) */
    CH(7),
    
    /** Type 8: Side chain sp3 CH2 group (without PRO_CD, MET_CYS_CH2, LYS_CE, ARG_CD) */
    CH2(8),
    
    /** Type 9: Side chain methionine S */
    MET_S(9),
    
    /** Type 10: Back bone proline amino N */
    PRO_N(10),
    
    /** Type 11: Side chain C sp2 (zero H's) */
    CH0_SP2(11),
    
    /** Type 12: Side chain CH sp2 (standard benzene C only) */
    CH_SP2(12),
    
    /** Type 13: Side chain tryptophan C sp2 in 5-ring bound to CB */
    TRP_CG(13),
    
    /** Type 14: Side chain tryptophan C sp2 bound to indole N */
    TRP_CE2(14),
    
    /** Type 15: Side chain serine CB */
    SER_CB(15),
    
    /** Type 16: Side chain serine/threonine hydroxyl O */
    SER_THR_O(16),
    
    /** Type 17: Side chain threonine CB */
    THR_CB(17),
    
    /** Type 18: Side chain asparagine/glutamine amide N */
    ASN_GLN_N(18),
    
    /** Type 19: Side chain cysteine thiol S */
    CYS_S(19),
    
    /** Type 20: Side chain lysine amino N and back bone N-terminus */
    LYS_N(20),
    
    /** Type 21: Side chain arginine guanidine C */
    ARG_CZ(21),
    
    /** Type 22: Side chain arginine guanidine terminal N */
    ARG_NH(22),
    
    /** Type 23: Side chain histidine C sp2 bound to CB */
    HIS_CG(23),
    
    /** Type 24: Side chain CH sp2 bound to N sp2 in Trp (CD1) and His (CD2) */
    TRP_HIS_CH(24),
    
    /** Type 25: Side chain histidine NE2 (NH, sp2, donor) bound to HIS_CD2 */
    HIS_NE2(25),
    
    /** Type 26: Side chain histidine C sp2 bound to HIS_ND1 and HIS_NE2 */
    HIS_CE1(26),
    
    /** Type 27: Side chain aspartate/glutamate carboxyl C */
    ASP_GLU_C(27),
    
    /** Type 28: Side chain aspartate/glutamate carboxyl O and back bone C-terminus (OXT) */
    ASP_GLU_O(28),
    
    /** Type 29: Side chain methionine/cysteine CH2 sp3 bound to S */
    MET_CYS_CH2(29),
    
    /** Type 30: Side chain methionine CH3 sp3 bound to S */
    MET_METHYL(30),
    
    /** Type 31: Side chain thyrosine CH0 sp2 bound to thiol S */
    TYR_CZ(31),
    
    /** Type 32: Side chain proline CH2 sp3 bound to back bone amino N (ring closure) */
    PRO_CD(32),
    
    /** Type 33: Side chain asparagine/glutamine amide C */
    ASN_GLN_C(33),
    
    /** Type 34: Side chain asparagine/glutamine amide O */
    ASN_GLN_O(34),
    
    /** Type 35: Side chain lysine CH2 sp3 bound to amino N */
    LYS_CE(35),
    
    /** Type 36: Side chain arginine guanidine N (non terminal) */
    ARG_NE(36),
    
    /** Type 37: Side chain arginine CH2 sp3 bound to ARG_NE */
    ARG_CD(37),
    
    /** Type 38: Side chain histidine ND1 (-N=, sp2, acceptor) bound to HIS_CG */
    HIS_ND1(38),
    
    /** Type 39: Side chain tryptophan indole N */
    TRP_N(39),
    
    /** Type 40: Side chain tyrosine hydroxyl O */
    TYR_O(40);
    
    
    private int typeInt;
    
    PDIdbProteinAtomType(int typeInt) {
        this.typeInt = typeInt;
    }

    /**
     * Returns the type as an int
     * @return the type
     */
    public int getTypeInt() {
        return typeInt;
    }
}
