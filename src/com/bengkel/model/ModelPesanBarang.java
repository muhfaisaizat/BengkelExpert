
package com.bengkel.model;

import com.bengkel.swing.table.EventAction;
import com.bengkel.swing.table.ModelAction;


public class ModelPesanBarang {

    public ModelPesanBarang(String ID_Pelanggan, String Nama_Pelanggan, String Nama_Barang, String Status) {
        this.ID_Pelanggan=ID_Pelanggan;
        this.Nama_Pelanggan=Nama_Pelanggan;
        this.Nama_Barang=Nama_Barang;
        this.Status=Status;
        
    }
    

    public String getID_Pelanggan() {
        return ID_Pelanggan;
    }

    public void setID_Pelanggan(String ID_Pelanggan) {
        this.ID_Pelanggan = ID_Pelanggan;
    }

    public String getNama_Pelanggan() {
        return Nama_Pelanggan;
    }

    public void setNama_Pelanggan(String Nama_Pelanggan) {
        this.Nama_Pelanggan = Nama_Pelanggan;
    }

    public String getNama_Barang() {
        return Nama_Barang;
    }

    public void setNama_Barang(String Nama_Barang) {
        this.Nama_Barang = Nama_Barang;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    private String ID_Pelanggan;
    private String Nama_Pelanggan;
    private String Nama_Barang;
    private String Status;
   
    public Object[] toRowTable(EventAction event){
       return new Object[]{ID_Pelanggan,Nama_Pelanggan,Nama_Barang,Status,new ModelAction(this,event)}; 
    }
}
