/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openscience.cdk.interfaces.IPDBAtom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pipe
 */
public class HBPlus {

    /** slf4j logging */
    private static final Logger log = LoggerFactory.getLogger(HBPlus.class);

    private String programOut = "";
    private String programError = "";
    private File pdbFile;
    
    public static int count = 1;
    
    /** Parsed .hb2-file contents */
    private Map<String, HBPlusRecord> hb2Contents;

    /**
     * Runs the external program HBPlus
     *
     * @param pdbFile a PDB file
     */
    public void run(File pdbFile) throws IOException {
        this.pdbFile = pdbFile;

        String[] args = new String[2];
        args[0] = "./hbplus";
        //args[1]="-P";
        args[1] = pdbFile.getAbsolutePath();
        
        log.debug("HBPLUS command line: {}", Arrays.toString(args));

        try {

            
            ProcessBuilder pb = new ProcessBuilder(args);
            Process p = pb.start();
            p.waitFor();
            
            BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            
            while ((line = stdout.readLine()) != null) {
                programOut+=line+"\n";                
            }
            
            while ((line = stderr.readLine()) != null) {
                programError+=line+"\n";                
            }
            
            //<editor-fold defaultstate="collapsed" desc="Saving Output and Error in file -> Out_HBPlus.out">
            FileWriter fw = new FileWriter("Out_HBPlus.out", true); 
            fw.write("-----------------"+"HBPLus Output nº"+count+"-----------------"+"\n");
            count ++;
            fw.write("Exit code: " + p.exitValue()+"\n");
            fw.write("Output of running "+Arrays.toString(args)+" is:"+"\n");
            fw.write(line+"\n");
            fw.write(""+"\n");
            fw.write("Stderr of running "+ Arrays.toString(args)+" is:"+"\n");
            fw.write(line+"\n");
            fw.write("\n");
            fw.write("---------------------------------------------------------\n");
            fw.close();
            //</editor-fold>
        
            // Parse .hb2 file
            parse();

        } catch (InterruptedException ex) {
            log.error(ex.toString(), ex);
        }
   }
    
    private HBPlusRecord parseLine(String line) {
        // Parse line
        HBPlusRecord hbprecord = new HBPlusRecord();
        hbprecord.setDonorResName(line.substring(6,9).trim());
        hbprecord.setDonorResSeq(line.substring(1, 5).replaceFirst("^0+(?!$)", ""));
        hbprecord.setDonorChainID(line.substring(0,1));
        hbprecord.setDonorName(line.substring(9,13).trim());
        hbprecord.setDonorElement(hbprecord.getDonorName().substring(0, 1));
        hbprecord.setDonorICode(line.substring(5,6).replace("-", ""));
        
        hbprecord.setAcceptorResName(line.substring(20,23).trim());
        hbprecord.setAcceptorResSeq(line.substring(15, 19).replaceFirst("^0+(?!$)", ""));
        hbprecord.setAcceptorChainID(line.substring(14,15));
        hbprecord.setAcceptorName(line.substring(23,27).trim());
        hbprecord.setAcceptorElement(hbprecord.getAcceptorName().substring(0, 1));
        hbprecord.setAcceptorICode(line.substring(19,20).replace("-", ""));
        
        
        
        try
        {
            
        
            hbprecord.setDistDA(Float.parseFloat(line.substring(27, 32))) ;
            hbprecord.setDistHA(Float.parseFloat(line.substring(52, 57)));
            
            hbprecord.setAngle_D_H_A(Float.parseFloat(line.substring(46, 51)));
            hbprecord.setAngle_H_A_AA(Float.parseFloat(line.substring(58, 63)));
            hbprecord.setAngle_D_A_AA(Float.parseFloat(line.substring(64, 69)));
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
        // Fill HBPlusRecord object
        return hbprecord;
    }
    
    /**
     * Parses HBPLUS' output file .hb2 into an internal structure
     */
    private void parse() {
                
        hb2Contents = new HashMap<String, HBPlusRecord>();
        String newfilename = pdbFile.getName().substring(0, pdbFile.getName().lastIndexOf("."))+".hb2";        
        
        try {            
            
            FileInputStream fstream = new FileInputStream(newfilename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if(Character.isDigit(line.charAt(1))&&Character.isDigit(line.charAt(2)))
                {
                    // Generate unique key for each H-bond
                    HBPlusRecord record = parseLine(line);
                    sb.append(record.getAcceptorName());
                    sb.append(record.getAcceptorChainID());
                    sb.append(record.getAcceptorResSeq());
                    sb.append(record.getAcceptorICode());
                    sb.append(record.getDonorName());
                    sb.append(record.getDonorChainID());
                    sb.append(record.getDonorResSeq());
                    sb.append(record.getDonorICode());
                    
                    // Add HBPlusRecord with unique key to map
                    hb2Contents.put(sb.toString(), record);
                }
            }
            in.close();
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    /**
     * Returns true if HBPLUS detected an H-bond involving atom1 and atom2,
     * otherwise returns false.
     * @param atom1 first atom
     * @param atom2 second atom
     * @return true if HBPLUS detected an H-bond involving the two atoms,
     *         otherwise false
     */
    public boolean isHBond(IPDBAtom atom1, IPDBAtom atom2) {
        // Generate key from atoms
        StringBuilder sb = new StringBuilder();
        sb.append(atom1.getName());
        sb.append(atom1.getChainID());
        sb.append(atom1.getResSeq());
        sb.append(atom1.getICode());
        sb.append(atom2.getName());
        sb.append(atom2.getChainID());
        sb.append(atom2.getResSeq());
        sb.append(atom2.getICode());
        String key1 = sb.toString();
        sb = new StringBuilder();
        sb.append(atom2.getName());
        sb.append(atom2.getChainID());
        sb.append(atom2.getResSeq());
        sb.append(atom2.getICode());
        sb.append(atom1.getName());
        sb.append(atom1.getChainID());
        sb.append(atom1.getResSeq());
        sb.append(atom1.getICode());
        String key2 = sb.toString();
        
        return hb2Contents.containsKey(key1) || hb2Contents.containsKey(key2);
    }
}
