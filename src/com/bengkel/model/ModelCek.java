package com.bengkel.model;

import javax.swing.Icon;

public class ModelCek {
    private String title;
    private String title1;
    private String title2;
    private int value1;
    private int value2;
    private Icon icon;

    public ModelCek(String title, String title1, String title2, int value1, int value2, Icon icon) {
        this.title = title;
        this.title1 = title1;
        this.title2 = title2;
        this.value1 = value1;
        this.value2 = value2;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
