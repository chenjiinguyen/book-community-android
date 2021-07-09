package com.chenjinguyen.bookcommunity.model;

import java.io.Serializable;

public class InfoAccount implements Serializable {
    public String getNameinfo() {
        return nameinfo;
    }

    public void setNameinfo(String nameinfo) {
        this.nameinfo = nameinfo;
    }

    public InfoAccount(int iconinfo, String nameinfo) {
        this.iconinfo = iconinfo;
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

}
