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
    private String donorResname;
    private String donorResId;
    private String donorChainId;
    private String donorAtomname;
    private String donorElement;
    
    private String acceptorResname;
    private String acceptorResId;
    private String acceptorChainId;
    private String acceptorAtomname;
    private String acceptorElement;  
    
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

    public String getAcceptorChainId() {
        return acceptorChainId;
    }

    public void setAcceptorChainId(String acceptoChainId) {
        this.acceptorChainId = acceptoChainId;
    }

    public String getAcceptorAtomname() {
        return acceptorAtomname;
    }

    public void setAcceptorAtomname(String acceptorAtomname) {
        this.acceptorAtomname = acceptorAtomname;
    }

    public String getAcceptorElement() {
        return acceptorElement;
    }

    public void setAcceptorElement(String acceptorElement) {
        this.acceptorElement = acceptorElement;
    }

    public String getAcceptorResId() {
        return acceptorResId;
    }

    public void setAcceptorResId(String acceptorResId) {
        this.acceptorResId = acceptorResId;
    }

    public String getAcceptorResname() {
        return acceptorResname;
    }

    public void setAcceptorResname(String acceptorResname) {
        this.acceptorResname = acceptorResname;
    }

    public String getDonorAtomname() {
        return donorAtomname;
    }

    public void setDonorAtomname(String donorAtomname) {
        this.donorAtomname = donorAtomname;
    }

    public String getDonorChainId() {
        return donorChainId;
    }

    public void setDonorChainId(String donorChainId) {
        this.donorChainId = donorChainId;
    }

    public String getDonorElement() {
        return donorElement;
    }

    public void setDonorElement(String donorElement) {
        this.donorElement = donorElement;
    }

    public String getDonorResId() {
        return donorResId;
    }

    public void setDonorResId(String donorResId) {
        this.donorResId = donorResId;
    }

    public String getDonorResname() {
        return donorResname;
    }

    public void setDonorResname(String donorResname) {
        this.donorResname = donorResname;
    }
    

    
    //</editor-fold>
        
    public HBPlusRecord(){
        
    }
    
    public IPDBAtom GetAcceptor(){
        
        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        
        IPDBAtom pdbatom = builder.newPDBAtom(acceptorElement);        
        pdbatom.setChainID(acceptorChainId);
        pdbatom.setName(acceptorAtomname);
        pdbatom.setResName(acceptorResname);
        pdbatom.setResSeq(acceptorResId);
        
        IAtom iatom = builder.newAtom(acceptorElement);
        
        
        return pdbatom;
    }
    
    public IPDBAtom GetDonor(){
        
        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        IPDBAtom pdbatom = builder.newPDBAtom(donorElement);
        pdbatom.setChainID(donorChainId);
        pdbatom.setName(donorAtomname);
        pdbatom.setResName(donorResname);
        pdbatom.setResSeq(donorResId);
        return pdbatom;
    }
    
    
    
    
}
