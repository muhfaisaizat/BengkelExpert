/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
         this.event = event;
       
    }


    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        
        if (column == 5) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row);
        action.setBackground(new Color(239, 244, 255));
        return action;   
        } else if (column == 0) {
              actionhapus action = new actionhapus();
           action.initEvent(event, row);
        action.setBackground(new Color(239, 244, 255));
        return action; 
        }else if (column== 4) {
            actionhapusnedit action = new actionhapusnedit();
            action.initEvent(event, row);
        action.setBackground(new Color(239, 244, 255));
        return action; 
        }else if (column== 6) {
            actionhapusnedit action = new actionhapusnedit();
            action.initEvent(event, row);
        action.setBackground(new Color(239, 244, 255));
        return action; 
        }
        else if (column== 3) {
            actionhapusnedit action = new actionhapusnedit();
            action.initEvent(event, row);
        action.setBackground(new Color(239, 244, 255));
        return action; 
        }
            return super.getTableCellEditorComponent(jtable, o, bln, row, column);
        
    }
}
