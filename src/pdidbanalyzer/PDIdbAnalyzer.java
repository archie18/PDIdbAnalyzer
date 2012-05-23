/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdidbanalyzer;

import pdidbanalyzer.cdk.PDBReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.FilenameUtils;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.openscience.cdk.interfaces.IPDBPolymer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PROJECT HISTORY
 *     2011-09-06    0.2.1    Added parameter ignoreUnknownAtomTypes to
 *                            InteractionAnalyzer to ignore unknown atoms
 *                            in effective interactions
 *     2011-08-22    0.2      Added AndyInteractionTypes and updated atom
 *                            typing with missing types. Refactored a few
 *                            classes to be more modular
 *     2011-03-02    0.1      Initial version
 * @author archvile18
 */
public class PDIdbAnalyzer {

    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(PDIdbAnalyzer.class);
    
    /** The version string of this project */
    public final static String version = "0.2.1";


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Ignore water atoms?
        boolean ignoreWater = true;

        // Ignore unknown (null) atom types in effective interactions?
        boolean ignoreUnknownAtomTypes = true;

        // Parse command line arguments
        CommandLineParameters clParams = new CommandLineParameters();
        clParams.parse(args);
        if (clParams.getHelpOption())
          clParams.printHelp();
        else if (clParams.getVersionOption())
          clParams.printVersion();
        File pdbInput = new File(clParams.getPdbFileOption());
        String mode = clParams.getModeOption();
    
        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        
        // If pdbFile is a directory get all .pdb files contained in it.
        // Otherwise only work on the single given PDB file.
        File[] pdbFiles;
        if (pdbInput.isDirectory()) {
            pdbFiles = pdbInput.listFiles(new PDBFilenameFilter());
        } else {
            pdbFiles = new File[1];
            pdbFiles[0] = pdbInput;
        }
        
        // Process all PDB files (supports gzipped files)
        for (int i = 0; i < pdbFiles.length; i++) {
            File pdbFile = pdbFiles[i];

            // Ensure the file is a regular file (e.g. not a directory)
            if (!pdbFile.isFile()) {
                continue;
            }
            
            HBPlus hbp = new HBPlus();
            hbp.run(pdbFile);
            
            String filename = pdbFile.getName();
            log.debug("File: {}", pdbFile);
            
            IPDBPolymer pdbPolymer = null;
            try {
                // Read PDB file
                PDBReader reader = null;
                if ("gz".equals(FilenameUtils.getExtension(pdbFile.getName()))) {
                    reader = new PDBReader(new GZIPInputStream(new FileInputStream(pdbFile)));
                    filename = FilenameUtils.getBaseName(pdbFile.getName());
                } else {
                    reader = new PDBReader(new FileReader(pdbFile));
                }
                IChemFile chemFile = (IChemFile) reader.read(builder.newChemFile());
                pdbPolymer = (IPDBPolymer) chemFile.getChemSequence(0).getChemModel(0).getMoleculeSet().getMolecule(0);
            } catch (CDKException ex) {
                log.error(ex.toString(), ex);
            } catch (FileNotFoundException ex) {
                log.error(ex.toString(), ex);
            } catch (IOException ex) {
                log.error(ex.toString(), ex);
            }

            if (pdbPolymer == null) {
                log.error("Error parsing PDB file: {}", filename);
            } else {

                // Remove waters if applicable
                if (ignoreWater) {
                    for (int j = pdbPolymer.getAtomCount() - 1; j > 0; j--) {
                        IPDBAtom pdbAtom = (IPDBAtom) pdbPolymer.getAtom(j);
                        if ("HOH".equals(pdbAtom.getResName())) {
                            pdbPolymer.removeAtomAndConnectedElectronContainers(pdbAtom);
                        }
                    }
                }

                // Perform atom typing
                PDIdbAtomTyper atomTyper = new PDIdbAtomTyper().setPdbPolymer(pdbPolymer).setFilename(filename);
                atomTyper.run();

                // Perform groove tryping
                PDIdbGrooveTyper grooveTyper = new PDIdbGrooveTyper().setPdbPolymer(pdbPolymer);
                grooveTyper.run();

                // Create the output PrintStream to a file with name: <pdbFile_basename>.dat
                PrintStream out = null;
                try {
                    out = new PrintStream(FilenameUtils.getBaseName(filename) + ".dat");
                } catch (FileNotFoundException ex) {
                    log.error(ex.toString(), ex);
                }

                // Create an IOutputFormatter
                IOutputFormatter outputFormatter;
                if ("2".equals(mode))
                    outputFormatter = new AndyOutputFormatter();
                else
                    outputFormatter = new PDIdbOutputFormatter();

                // Create an IInteractionTyper
                IInteractionTyper interactionTyper;
                if ("2".equals(mode))
                    interactionTyper = new AndyInteractionTyper();
                else
                    interactionTyper = new PDIdbInteractionTyper();


                // Print header line
                out.println(outputFormatter.getHeader());

                // Run the interaction analysis
                InteractionAnalyzer intAnalyzer = new InteractionAnalyzer().setPdbPolymer(pdbPolymer).setOut(out).setOutputFormatter(outputFormatter).setIgnoreUnknownAtomTypes(ignoreUnknownAtomTypes);
                intAnalyzer.run();

                // Close the output stream
                out.close();
            }
        }

    }

}
