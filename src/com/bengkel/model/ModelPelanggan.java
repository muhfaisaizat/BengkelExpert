
package com.bengkel.model;

import com.bengkel.swing.table.EventAction;
import com.bengkel.swing.table.ModelAction;


public class ModelPelanggan {
    
    public ModelPelanggan(String ID_Pelanggan, String Nama, String NoHp, String Alamat) {
        this.ID_Pelanggan=ID_Pelanggan;
        this.Nama=Nama;
        this.NoHp=NoHp;
        this.Alamat=Alamat;
        
    }

    public ModelPelanggan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getID_Pelanggan() {
        return ID_Pelanggan;
    }

    public void setID_Pelanggan(String ID_Pelanggan) {
        this.ID_Pelanggan = ID_Pelanggan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNoHp() {
        return NoHp;
    }

    public void setNoHp(String NoHp) {
        this.NoHp = NoHp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
    private String ID_Pelanggan;
    private String Nama;
    private String NoHp;
    private String Alamat;
    private String Status;
//    private 
    
    
    public Object[] toRowTable(EventAction event){
       return new Object[]{ID_Pelanggan,Nama,NoHp,Alamat,new ModelAction(this,event)}; 
    }

    public void addRow(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
