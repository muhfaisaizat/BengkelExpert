package com.bengkel.swing.table;

import com.bengkel.model.ModelPelanggan;
import com.bengkel.model.ModelPesanBarang;
import com.bengkel.model.ModelStudent;
import com.bengkel.model.ModelTambah;

public class ModelAction {

    

    

    public ModelStudent getStudent() {
        return student;
    }

    public void setStudent(ModelStudent student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(ModelStudent student, EventAction event) {
        this.student = student;
        this.event = event;
    }
    
    public ModelPesanBarang getPesanbarang() {
        return pesanbarang;
    }

    public void setPesanbarang(ModelPesanBarang pesanbarang) {
        this.pesanbarang = pesanbarang;
    }
    public ModelAction(ModelPesanBarang pesanbarang, EventAction event) {
        this.pesanbarang = pesanbarang;
        this.event = event;
    }

    public ModelPelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(ModelPelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }
    
    public ModelAction(ModelPelanggan pelanggan, EventAction event) {
        this.pelanggan = pelanggan;
        this.event = event;
    }

    public ModelTambah getTambah() {
        return tambah;
    }

    public void setTambah(ModelTambah tambah) {
        this.tambah = tambah;
    }
    
    public ModelAction(ModelTambah tambah, EventAction event) {
        this.tambah=tambah;
        this.event = event;
    }
    
    public ModelAction() {
    }

    private ModelStudent student;
    private ModelPesanBarang pesanbarang;
    private ModelPelanggan pelanggan;
    private EventAction event;
    private ModelTambah tambah;

}
