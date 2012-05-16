/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 * Based on PDIdbInteractionType adding new types:
 * <ul>
 *     <li>21: DBE-PSC: ND-NA (His ND1)</li>
 *     <li>22: DBB-PSC: OD-NA (DNA O3' terminal)</li>
 *     <li>23: DBB-PSC: OD-OA (DNA O3' terminal)</li>
 *     <li>24: DBB-PBB: OD-OA (DNA O3' terminal)</li>
 *     <li>25: DBB-PSC: OD-SA (DNA O3' terminal)</li>
 *     <li>26: (-)...(-): Ionic bond assuming invisible cation. Now distinct
 *         from type 18 (+)...(-)</li>
 * </ul>
 * @author archvile18
 */
public enum AndyInteractionType implements IInteractionType {
    
    /** Type 1 */
    DBE_PSC_NA_ND(1, PDIdbInteractionClass.CHb),
    
    /** Type 2 */
    DBE_PSC_NA_OD(2, PDIdbInteractionClass.CHb),
    
    /** Type 3 */
    DBE_PSC_OA_ND(3, PDIdbInteractionClass.CHb),
    
    /** Type 4 */
    DBE_PSC_OA_OD(4, PDIdbInteractionClass.CHb),
    
    /** Type 5 */
    DBE_PSC_ND_OA(5, PDIdbInteractionClass.CHb),
    
    /** Type 6 */
    DBE_PBB_NA_ND(6, PDIdbInteractionClass.CHb),
    
    /** Type 7 */
    DBE_PBB_ND_OA(7, PDIdbInteractionClass.CHb),
    
    /** Type 8 */
    DBE_PBB_OA_ND(8, PDIdbInteractionClass.CHb),
    
    /** Type 9 */
    DBB_PSC_OA_ND(9, PDIdbInteractionClass.CHb),
    
    /** Type 10 */
    DBB_PSC_OA_OD(10, PDIdbInteractionClass.CHb),
    
    /** Type 11 */
    DBB_PBB_OA_ND(11, PDIdbInteractionClass.CHb),
    
    /** Type 12 */
    DBB_PSC_OA_SD(12, PDIdbInteractionClass.SHb),
    
    /** Type 13 */
    DBE_PSC_NA_SD(13, PDIdbInteractionClass.SHb),
    
    /** Type 14 */
    DBE_PSC_OA_SD(14, PDIdbInteractionClass.SHb),
    
    /** Type 15 */
    DBE_PSC_ND_SA(15, PDIdbInteractionClass.SHb),
    
    /** Type 16 */
    DBE_PSC_CD_OA(16, PDIdbInteractionClass.CHO),
    
    /** Type 17 */
    DBE_PBB_CD_OA(17, PDIdbInteractionClass.CHO),
    
    /** Type 18 */
    ION(18, PDIdbInteractionClass.Ion),
    
    /** Type 19 */
    HPH(19, PDIdbInteractionClass.Hph),
    
    /** Type 20 */
    OTHER(20, PDIdbInteractionClass.Other),

    /** Type 21: DBE-PSC: ND-NA (His ND1) */
    DBE_PSC_ND_NA(21, PDIdbInteractionClass.CHb),

    /** Type 22: DBB-PSC: OD-NA (DNA O3' terminal) */
    DBB_PSC_OD_NA(22, PDIdbInteractionClass.CHb),

    /** Type 23: DBB-PSC: OD-OA (DNA O3' terminal) */
    DBB_PSC_OD_OA(23, PDIdbInteractionClass.CHb),

    /** Type 24: DBB-PBB: OD-OA (DNA O3' terminal) */
    DBB_PBB_OD_OA(24, PDIdbInteractionClass.CHb),

    /** Type 25: DBB-PSC: OD-SA (DNA O3' terminal) */
    DBB_PSC_OD_SA(25, PDIdbInteractionClass.SHb),

    /** Type 26: (-)...(-): Ionic bond assuming invisible cation. Now distinct
                 from type 18 (+)...(-) */
    ION_NEG_NEG(26, PDIdbInteractionClass.Ion);
    
    /** Type int */
    private int typeInt;
    
    /** Interaction class ("super" type) */
    private PDIdbInteractionClass interactionClass;

    private AndyInteractionType(int typeInt, PDIdbInteractionClass interactionClass) {
        this.typeInt = typeInt;
        this.interactionClass = interactionClass;
    }

    /**
     * Returns the type as an int
     * @return the type
     */
    @Override
    public int getTypeInt() {
        return typeInt;
    }

    /**
     * Returns the interaction class of this interaction type (i.e. the "super"
     * type).
     * @return the interaction class
     */
    @Override
    public PDIdbInteractionClass getInteractionClass() {
        return interactionClass;
    }

}
