/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 * Defines DBIdb DNA atom types
 * @author archvile18
 */
public enum PDIdbDNAAtomType implements IAtomType {
    
    /** Type 1: free phosphate oxygens OP1, OP2 and OP3 (5' terminus) */
    OP1OP2OP3(1),
    
    /** Type 2: phosphate P */
    P(2),
    
    /** Type 3: ribose O3' and O5' (phosphoester) */
    O3PrO5Pr(3),
    
    /** Type 4: ribose C5' */
    C5Pr(4),
    
    /** Type 5: ribose C3' and C4' */
    C3PrC4Pr(5),
    
    /** Type 6: ribose O3' (3' terminus) */
    O3Pr(6),
    
    /** Type 7: ribose C2' */
    C2Pr(7),
    
    /** Type 8: ribose C1' */
    C1Pr(8),
    
    /** Type 9: ribose O4' (ring oxygen) */
    O4Pr(9),
    
    /** Type 10: purine base N9 and pyrimidine base N1 (tertiary N) */
    TertN(10),
    
    /** Type 11: purine base C8 */
    C8(11),
    
    /** Type 12: adenine N1, purine base N3 and N7, and cytosine N3 (imine) */
    Imine(12),
    
    /** Type 13: purine base C5 */
    PuC5(13),
    
    /** Type 14: purine base C4 */
    PuC4(14),
    
    /** Type 15: adenine C2 */
    AC2(15),
    
    /** Type 16: adenine C6 and cytosine C4 */
    AC6CC4(16),
    
    /** Type 17: guanine N2, adenine N6 and cytosine N4 (primary N) */
    PrmN(17),
    
    /** Type 18: guanine C2 */
    GC2(18),
    
    /** Type 19: guanine C6 and thymine C4 */
    GC6TC4(19),
    
    /** Type 20: guanine O6, cytosine O2, and thymine O2 and O4 (carbonyl O) */
    CarbonylO(20),
    
    /** Type 21: pyrimidine base C2 */
    PyC2(21),
    
    /** Type 22: cytosine C5 */
    CC5(22),
    
    /** Type 23: pyrimidine base C6 */
    PyC6(23),
    
    /** Type 24: guanine N1 and thymine N3 (amide N) */
    AmideN(24),
    
    /** Type 25: thymine C5 */
    TC5(25),
    
    /** Type 26: thymine C7 (methyl group) */
    TC7(26);
    
    
    /** Integer representation of the atom types */
    private int typeInt;
    
    /**
     * Constructs a new atom type with the given number
     * @param typeInt integer representation of the atom type
     */
    PDIdbDNAAtomType(int typeInt) {
        this.typeInt = typeInt;
    }

    /**
     * Returns an integer representation of the atom type
     * @return an integer representation of the atom type
     */
    public int getTypeInt() {
        return typeInt;
    }

}
