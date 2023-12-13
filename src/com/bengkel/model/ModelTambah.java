
package com.bengkel.model;

import com.bengkel.event.Eventbuttontambah;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ModelTambah {
    private String title;
    private Icon icon;
    private Eventbuttontambah eventbuttontambah;
    
    public ModelTambah(String title,Icon icon) {
       this.title= title;
       this.icon=icon; 
    }

   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
