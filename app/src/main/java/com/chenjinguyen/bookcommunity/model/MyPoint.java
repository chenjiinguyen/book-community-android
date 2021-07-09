package com.chenjinguyen.bookcommunity.model;

import java.io.Serializable;

public class MyPoint implements Serializable {
    String tenTruyen, diem;

    public MyPoint(String pSoDiem, String pTenTruyen) {
        this.diem = pSoDiem;
        this.tenTruyen = pTenTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }
}
