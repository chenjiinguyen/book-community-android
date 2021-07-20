package com.chenjinguyen.bookcommunity.model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceResponse {
    @SerializedName("token")
    @Expose
    String token;

    public DeviceResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
