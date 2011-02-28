/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

/**
 *
 * @author archvile18
 */
public enum PDIdbInteractionType {
    
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
    OTHER(20, PDIdbInteractionClass.Other);
    
    /** Type int */
    private int typeInt;
    
    /** Interaction class ("super" type) */
    private PDIdbInteractionClass interactionClass;

    private PDIdbInteractionType(int typeInt, PDIdbInteractionClass interactionClass) {
        this.typeInt = typeInt;
        this.interactionClass = interactionClass;
    }

    /**
     * Returns the type as an int
     * @return the type
     */
    public int getTypeInt() {
        return typeInt;
    }

    /**
     * Returns the interaction class of this interaction type (i.e. the "super"
     * type).
     * @return the interaction class
     */
    public PDIdbInteractionClass getInteractionClass() {
        return interactionClass;
    }

}
