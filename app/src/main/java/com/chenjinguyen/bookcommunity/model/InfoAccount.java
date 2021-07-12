package com.chenjinguyen.bookcommunity.model;

import android.content.Context;

import java.io.Serializable;

public class InfoAccount implements Serializable {
    public String getNameinfo() {
        return nameinfo;
    }

    public void setNameinfo(String nameinfo) {
        this.nameinfo = nameinfo;
    }

    String nameinfo;

    public int getIconinfo() {
        return iconinfo;
    }

    public void setIconinfo(int iconinfo) {
        this.iconinfo = iconinfo;
    }

    int iconinfo;



    Class _class;

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public InfoAccount(int iconinfo,String nameinfo, Class _class) {
        this.nameinfo = nameinfo;
        this.iconinfo = iconinfo;
        this._class = _class;
    }

    public InfoAccount(int iconinfo,String nameinfo) {
        this.nameinfo = nameinfo;
        this.iconinfo = iconinfo;
        this._class = null;
    }
}
