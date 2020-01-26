package com.example.ecommerce.login;


import com.google.gson.annotations.SerializedName;

public class LoginCheck {


    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginCheck(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCheck{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
