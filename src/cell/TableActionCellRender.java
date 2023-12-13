/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cell;

import com.bengkel.swing.table.Table;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableActionCellRender extends DefaultTableCellRenderer {

    private Table table;
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        if (column == 5) {
        PanelAction action = new PanelAction();

        if (isSeleted == false) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(new Color(239, 244, 255));
        }

        return action;
    } else if (column == 0) {
        actionhapus hapus = new actionhapus();

        if (isSeleted == false) {
            hapus.setBackground(Color.WHITE);
        } else {
            hapus.setBackground(new Color(239, 244, 255));
        }

        return hapus;
    }else if (column == 4) {
           actionhapusnedit hapusnedit = new actionhapusnedit();

        if (isSeleted == false) {
            hapusnedit.setBackground(Color.WHITE);
        } else {
            hapusnedit.setBackground(new Color(239, 244, 255));
        }

        return hapusnedit; 
        }else if (column == 6) {
           actionhapusnedit hapusnedit = new actionhapusnedit();

        if (isSeleted == false) {
            hapusnedit.setBackground(Color.WHITE);
        } else {
            hapusnedit.setBackground(new Color(239, 244, 255));
        }

        return hapusnedit; 
        }
        else if (column == 3) {
           actionhapusnedit hapusnedit = new actionhapusnedit();

        if (isSeleted == false) {
            hapusnedit.setBackground(Color.WHITE);
        } else {
            hapusnedit.setBackground(new Color(239, 244, 255));
        }

        return hapusnedit; 
        }
    else {
        // return default cell renderer for other columns
        return super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
    }
    }
}
