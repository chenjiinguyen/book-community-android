package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.UserModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse extends Response {

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("data")
    @Expose
    UserModel user;

    public AuthResponse(int status, String message, boolean success, String token, UserModel user) {
        super(status, message, success);
        this.token = token;
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
