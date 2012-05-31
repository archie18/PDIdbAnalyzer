/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdidbanalyzer;

import java.security.KeyStore;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IPDBAtom;

/**
 *
 * @author pipe
 */
public class HBPlusRecord { 
    private String donorResName;
    private String donorResSeq;
    private String donorChainID;
    private String donorName;
    private String donorElement;
    private String donorICode;
    
    private String acceptorResName;
    private String acceptorResSeq;
    private String acceptorChainID;
    private String acceptorName;
    private String acceptorElement;  
    private String acceptorICode;
    
    private Float DistDA;
    private Float DistHA;
    
    private Float Angle_D_H_A;
    private Float Angle_D_A_AA;
    private Float Angle_H_A_AA;
    
    
    //<editor-fold defaultstate="collapsed" desc="Geters">

    public Float getAngle_D_A_AA() {
        return Angle_D_A_AA;
    }

    public void setAngle_D_A_AA(Float Angle_D_A_AA) {
        this.Angle_D_A_AA = Angle_D_A_AA;
    }

    public Float getAngle_D_H_A() {
        return Angle_D_H_A;
    }

    public void setAngle_D_H_A(Float Angle_D_H_A) {
        this.Angle_D_H_A = Angle_D_H_A;
    }

    public Float getAngle_H_A_AA() {
        return Angle_H_A_AA;
    }

    public void setAngle_H_A_AA(Float Angle_H_A_AA) {
        this.Angle_H_A_AA = Angle_H_A_AA;
    }

    public Float getDistDA() {
        return DistDA;
    }

    public void setDistDA(Float DistDA) {
        this.DistDA = DistDA;
    }

    public Float getDistHA() {
        return DistHA;
    }

    public void setDistHA(Float DistHA) {
        this.DistHA = DistHA;
    }

    public String getAcceptorChainID() {
        return acceptorChainID;
    }

    public void setAcceptorChainID(String acceptorChainID) {
        this.acceptorChainID = acceptorChainID;
    }

    public String getAcceptorElement() {
        return acceptorElement;
    }

    public void setAcceptorElement(String acceptorElement) {
        this.acceptorElement = acceptorElement;
    }

    public String getAcceptorName() {
        return acceptorName;
    }

    public void setAcceptorName(String acceptorName) {
        this.acceptorName = acceptorName;
    }

    public String getAcceptorResName() {
        return acceptorResName;
    }

    public void setAcceptorResName(String acceptorResName) {
        this.acceptorResName = acceptorResName;
    }

    public String getAcceptorResSeq() {
        return acceptorResSeq;
    }

    public void setAcceptorResSeq(String acceptorResSeq) {
        this.acceptorResSeq = acceptorResSeq;
    }

    public String getDonorChainID() {
        return donorChainID;
    }

    public void setDonorChainID(String donorChainID) {
        this.donorChainID = donorChainID;
    }

    public String getDonorElement() {
        return donorElement;
    }

    public void setDonorElement(String donorElement) {
        this.donorElement = donorElement;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorResName() {
        return donorResName;
    }

    public void setDonorResName(String donorResName) {
        this.donorResName = donorResName;
    }

    public String getDonorResSeq() {
        return donorResSeq;
    }

    public void setDonorResSeq(String donorResSeq) {
        this.donorResSeq = donorResSeq;
    }

    public String getAcceptorICode() {
        return acceptorICode;
    }

    public void setAcceptorICode(String acceptorICode) {
        this.acceptorICode = acceptorICode;
    }

    public String getDonorICode() {
        return donorICode;
    }

    public void setDonorICode(String donorICode) {
        this.donorICode = donorICode;
    }

   
    

    
    //</editor-fold>
        
    public HBPlusRecord(){
        
    }
    
    public IPDBAtom GetAcceptor(){
        
        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        
        IPDBAtom pdbatom = builder.newPDBAtom(acceptorElement);        
        pdbatom.setChainID(acceptorChainID);
        pdbatom.setName(acceptorName);
        pdbatom.setResName(acceptorResName);
        pdbatom.setResSeq(acceptorResSeq);
        
        IAtom iatom = builder.newAtom(acceptorElement);
        
        
        return pdbatom;
    }
    
    public IPDBAtom GetDonor(){
        
        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        IPDBAtom pdbatom = builder.newPDBAtom(donorElement);
        pdbatom.setChainID(donorChainID);
        pdbatom.setName(donorName);
        pdbatom.setResName(donorResName);
        pdbatom.setResSeq(donorResSeq);
        return pdbatom;
    }
    
    
    
    
}
