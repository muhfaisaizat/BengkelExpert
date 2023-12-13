
package com.bengkel.model;

import javax.swing.Icon;


public class ModelProfit {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return Values;
    }

    public void setValues(String Values) {
        this.Values = Values;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    public ModelProfit(String title,String Values,Icon icon){
        this.title=title;
        this.Values=Values;
        this.icon=icon;
    }
     private String title;
    private String Values;
    private Icon icon;
}
