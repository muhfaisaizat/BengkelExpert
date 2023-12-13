package com.bengkel.swing.table;

import com.bengkel.model.ModelPelanggan;
import com.bengkel.model.ModelPesanBarang;
import com.bengkel.model.ModelStudent;

public interface EventAction {
    
    public void Tambah(ModelPelanggan pelanggan);

    public void delete(ModelStudent student,ModelPesanBarang pesanbarang,ModelPelanggan pelanggan);

    public void update(ModelStudent student,ModelPesanBarang pesanbarang,ModelPelanggan pelangganl);
}
