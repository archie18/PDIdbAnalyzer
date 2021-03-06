/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.vecmath.Vector3d;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author archvile18
 */
public class InteractionAnalyzer {
    
    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(InteractionAnalyzer.class);
    
    /** Maximum distance to consider (in Angstrom) */
    private double distanceCutoff = 7.0;
    
    /** Calculate only effectiveOnly interactions */
    private boolean effectiveOnly = true;
    
    /** Omega angle for effectiveOnly interactions. Defaults to 90 degrees */
    private double omega = Math.PI / 2.0;

    /** The PDB polymer */
    private IPDBPolymer pdbPolymer;
    
    /** Distance matrix of atoms in pdbPolymer */
    private double[][] distanceMatrix;
    
    /** As a key for atom properties */
    private String distanceProperty = "DISTANCE";
    
    /** PrintStream for the output */
    private PrintStream out;
    
    /** OutputFormatter to generate string representations of interactions */
    private IOutputFormatter outputFormatter;
    
    /** InteractionTyper */
    private IInteractionTyper interactionTyper;

    /** Ignore unknown (null) atomtypes in effectiveOnly interactions */
    private boolean ignoreUnknownAtomTypes = true;
    
    /** OutputFilter */
    private PantanoOutputFilter outputFilter = null;

    
    /**
     * Creates a new instance which uses a default <code>PDIdbOutputFormatter</code>,
     * a default <code>System.out</code> PrintStream and a default
     * <code>PDIdbInteractionTyper</code> InteractionTyper.
     */
    public InteractionAnalyzer() {
        outputFormatter = new PDIdbOutputFormatter();
        out = System.out;
        interactionTyper = new PDIdbInteractionTyper();
    }

    /**
     * @return the distanceCutoff
     */
    public double getDistanceCutoff() {
        return distanceCutoff;
    }
    
    /**
     * Sets the distance cut-off used to determine effectiveOnly interactions
     * @param distanceCutoff the distance cut-off in Angstrom
     * @return this object
     */
    public InteractionAnalyzer setDistanceCutoff(double distanceCutoff) {
        this.distanceCutoff = distanceCutoff;
        return this;
    }

    /**
     * If true, only effective interactions are considered (default)
     * @param effectiveOnly set true to consider only effective interactions
     * @return this object
     */
    public InteractionAnalyzer setEffectiveOnly(boolean effectiveOnly) {
        this.effectiveOnly = effectiveOnly;
        return this;
    }

    /** Sets the omega angle for effectiveOnly interactions (in radians)
     * @param omega the omega to set (in radians)
     * @return this object 
     */
    public InteractionAnalyzer setOmega(double omega) {
        this.omega = omega;
        return this;
    }

    /**
     * @return the omega
     */
    public double getOmega() {
        return omega;
    }

    /**
     * Sets the PDB polymer to work with
     * @param pdbPolymer the PDB polymer
     * @return this object
     */
    public InteractionAnalyzer setPdbPolymer(IPDBPolymer pdbPolymer) {
        this.pdbPolymer = pdbPolymer;
        return this;
    }

    /**
     * Ignores unknown (null) atom types in effectiveOnly interaction calculations
     * @param ignoreUnknownAtomTypes
     */
    public InteractionAnalyzer setIgnoreUnknownAtomTypes(boolean ignoreUnknownAtomTypes) {
        this.ignoreUnknownAtomTypes = ignoreUnknownAtomTypes;
        return this;
    }

    /**
     * Returns true if unknown (null) atom types are ignored in effectiveOnly
     * interaction calculations
     * @return
     */
    public boolean isIgnoreUnknownAtomTypes() {
        return ignoreUnknownAtomTypes;
    }

    public InteractionAnalyzer setOutputFilter(PantanoOutputFilter outputFilter) {
        this.outputFilter = outputFilter;
        return this;
    }



    /**
     * Returns the PDB polymer
     * @return the PDB polymer
     */
    public IPDBPolymer getPdbPolymer() {
        return pdbPolymer;
    }

    public PrintStream getOut() {
        return out;
    }

    public InteractionAnalyzer setOut(PrintStream out) {
        this.out = out;
        return this;
    }

    public IOutputFormatter getOutputFormatter() {
        return outputFormatter;
    }

    public InteractionAnalyzer setOutputFormatter(IOutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
        return this;
    }

    public IInteractionTyper getInteractionTyper() {
        return interactionTyper;
    }

    public InteractionAnalyzer setInteractionTyper(IInteractionTyper interactionTyper) {
        this.interactionTyper = interactionTyper;
        return this;
    }
    
    /**
     * Performs the interaction analysis. Only interactions between typed DNA
     * and protein atoms are analysed. However, if
     * <code>ignoreUnknownAtomTypes<code> is false, other atoms (e.g. HETATM,
     * waters...) will be taken into account when determining effectiveOnly
     * interactions. If this is not desired <code>ignoreUnknownAtomTypes<code>
     * should be set true (default) or these atoms should be removed prior to
     * calling this method.
     */
    public void run() {
        
        log.trace("start distanceMatrix");
        distanceMatrix = calculateDistanceMatrix();
        log.trace("end distanceMatrix");
        
        // Separate DNA atoms from protein atoms
        List<Integer> dnaAtomIndices = new ArrayList<Integer>();
        List<Integer> proteinAtomIndices = new ArrayList<Integer>();
        for (int i = 0; i < pdbPolymer.getAtomCount(); i++) {
            IPDBAtom pdbAtom = (IPDBAtom) pdbPolymer.getAtom(i);

            // Safeguard for null atom types
            if (pdbAtom.getProperty(IAtomType.class) == null) {
                continue;
            }

            if (PDIdbDNAAtomType.class.equals(pdbAtom.getProperty(IAtomType.class).getClass())) {
                dnaAtomIndices.add(i);
            } else if (PDIdbProteinAtomType.class.equals(pdbAtom.getProperty(IAtomType.class).getClass())) {
                proteinAtomIndices.add(i);
            }
        }
        
        // Loop over all DNA atoms
        for (Integer i : dnaAtomIndices) {
            // Loop over all protein atoms
            for (Integer j : proteinAtomIndices) {
                
                if (log.isTraceEnabled()) {
                    IPDBAtom a = (IPDBAtom) pdbPolymer.getAtom(i);
                    log.trace("Atom 1: {} {} {} {} {} {}", new Object[]{a.getName(), a.getSerial(), a.getResName(), a.getChainID(), a.getResSeq(), a.getProperty(IAtomType.class)});
                    a = (IPDBAtom) pdbPolymer.getAtom(j);
                    log.trace("Atom 2: {} {} {} {} {} {}", new Object[]{a.getName(), a.getSerial(), a.getResName(), a.getChainID(), a.getResSeq(), a.getProperty(IAtomType.class)});
                    log.trace("Distance: {}", String.format("%.4f", getDistance(i, j)));
                }
                
                // Ensure that distance <= distanceCutoff and that the
                // interaction is effectiveOnly.
                if (getDistance(i, j) <= distanceCutoff &&
                        (!effectiveOnly || isEffectiveInteraction(i, j)) &&
                        (outputFilter == null || (outputFilter != null && outputFilter.accept((IPDBAtom) pdbPolymer.getAtom(i)) && outputFilter.accept((IPDBAtom) pdbPolymer.getAtom(j)))) ) {
                    log.debug("Found valid interaction within cutoff range");
                    IInteractionType intType = interactionTyper.getInteractionType(pdbPolymer.getAtom(i), pdbPolymer.getAtom(j));
                        log.debug("Interaction type: {}", intType);
                    if (intType != null) {
                        // Format output
                        String output = outputFormatter.format(intType, (IPDBAtom) pdbPolymer.getAtom(i), (IPDBAtom) pdbPolymer.getAtom(j), getDistance(i, j), pdbPolymer);
                        log.debug("Interaction output: {}", output);
                        // Print output
                        out.println(output);
                    }
                }
            }
        }
        

    }

    /**
     * Create a lower triangular distance matrix as a 2d array of doubles of
     * all atoms of the pdbPolymer.
     * @return the distance matrix
     */
    private double[][] calculateDistanceMatrix() {
        double[][] distanceMatrix = new double[pdbPolymer.getAtomCount()][pdbPolymer.getAtomCount()];
        for (int i = 0; i < pdbPolymer.getAtomCount(); i++) {
            for (int j = i + 1; j < pdbPolymer.getAtomCount(); j++) {
                distanceMatrix[i][j] = pdbPolymer.getAtom(i).getPoint3d().distance(pdbPolymer.getAtom(j).getPoint3d());
            }
        }
        return distanceMatrix;
    }
    
    /**
     * Returns a distance from the lower triangular distance matrix ensuring
     * that the indices to not refer to the upper triangle.
     * @param i index of the first atom
     * @param j index of the second atom
     * @return a distance from the distance matrix
     */
    private double getDistance(int i, int j) {
        if (i < j) {
            return distanceMatrix[i][j];
        } else {
            return distanceMatrix[j][i];
        }        
    }
    
    /**
     * Calculates the angle between the vector atom1->atom2 and the vector
     * atom1->atom3
     * Found as a private method in org.openscience.cdk.geometry.GeometryTools
     * @param atom1 centre atom
     * @param atom2 another atom
     * @param atom3 another atom
     * @return The angle between the three atoms
     */
    public static double getAngle(IAtom atom1, IAtom atom2, IAtom atom3){

            Vector3d centerAtom = new Vector3d();
            centerAtom.x=atom1.getPoint3d().x;
            centerAtom.y=atom1.getPoint3d().y;
            centerAtom.z=atom1.getPoint3d().z;
            Vector3d firstAtom = new Vector3d();
            Vector3d secondAtom = new Vector3d();

            firstAtom.x=atom2.getPoint3d().x;
            firstAtom.y=atom2.getPoint3d().y;
            firstAtom.z=atom2.getPoint3d().z;

            secondAtom.x=atom3.getPoint3d().x;
            secondAtom.y=atom3.getPoint3d().y;
            secondAtom.z=atom3.getPoint3d().z;

            firstAtom.sub(centerAtom);
            secondAtom.sub(centerAtom);

            return firstAtom.angle(secondAtom);
    }    

    /**
     * Determines whether the interaction between atom1 and atom2 is effectiveOnly
     * according to Ferrara & Melo, Protein Science (2009) 18, 1469-1485.
     * @param atom1Idx Index of atom1
     * @param atom2idx Index of atom2
     * @return true if the interaction is effectiveOnly, false otherwise.
     */
    private boolean isEffectiveInteraction(int atom1Idx, int atom2Idx) {

        log.trace("start isEffectiveInteraction");
        for (int atom3Idx = 0; atom3Idx < pdbPolymer.getAtomCount(); atom3Idx++) {

            // Skip atom if atom type is null and ignoreUnknownAtomTypes is true
            if (ignoreUnknownAtomTypes == true && pdbPolymer.getAtom(atom3Idx).getProperty(IAtomType.class) == null) {
                continue;
            }

            if ((atom3Idx != atom1Idx) && (atom3Idx != atom2Idx)) {
                // Get distance from the lower triangle of the distance matrix
                double distance = getDistance(atom1Idx, atom3Idx);
                
                // Ensure distance is within range
                if (distance <= distanceCutoff) {
                    // Ensure angle <= omega, if not the interaction is not effectiveOnly
                    double angle = InteractionAnalyzer.getAngle(pdbPolymer.getAtom(atom3Idx), pdbPolymer.getAtom(atom1Idx), pdbPolymer.getAtom(atom2Idx));
                    if (angle > omega) {
                        // Not effectiveOnly
                        if (log.isDebugEnabled()) {
                            log.debug("not effective");
                            IPDBAtom a = (IPDBAtom) pdbPolymer.getAtom(atom1Idx);
                            log.debug("Atom 1: {} {} {} {} {} {}", new Object[]{a.getName(), a.getSerial(), a.getResName(), a.getChainID(), a.getResSeq(), a.getProperty(IAtomType.class)});
                            a = (IPDBAtom) pdbPolymer.getAtom(atom2Idx);
                            log.debug("Atom 2: {} {} {} {} {} {}", new Object[]{a.getName(), a.getSerial(), a.getResName(), a.getChainID(), a.getResSeq(), a.getProperty(IAtomType.class)});
                            a = (IPDBAtom) pdbPolymer.getAtom(atom3Idx);
                            log.debug("Atom 3: {} {} {} {} {} {}", new Object[]{a.getName(), a.getSerial(), a.getResName(), a.getChainID(), a.getResSeq(), a.getProperty(IAtomType.class)});
                            log.debug("Angle: {}", String.format("%.4f", angle * 180.0 / Math.PI));
                        }
                        log.trace("end isEffectiveInteraction: false");
                        return false;
                    }
                }
            }
        }
        
        log.trace("end isEffectiveInteraction: true");
        return true;
    }
    
    private class AtomByDistanceComparator implements Comparator<IAtom> {

        @Override
        public int compare(IAtom o1, IAtom o2) {
            if (o1.getProperty(distanceProperty) != null && o2.getProperty(distanceProperty) == null)
                return -1;
            else if (o1.getProperty(distanceProperty) == null && o2.getProperty(distanceProperty) == null)
                return 0;
            else if (o1.getProperty(distanceProperty) == null && o2.getProperty(distanceProperty) != null)
                return 1;
            else if ((Double) o1.getProperty(distanceProperty) < (Double) o2.getProperty(distanceProperty))
                return -1;
            else if ((Double) o1.getProperty(distanceProperty) == (Double) o2.getProperty(distanceProperty))
                return 0;
            //if ((Double) o1.getProperty(distanceProperty) > (Double) o2.getProperty(distanceProperty))
            else
                return 1;       
        }
        
    }
    

}
