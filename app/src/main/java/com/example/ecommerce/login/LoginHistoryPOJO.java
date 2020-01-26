package com.example.ecommerce.login;

import com.google.gson.annotations.SerializedName;

public  class LoginHistoryPOJO {
    private String userEmail;
    @SerializedName("loginDate")
    private String loginDate;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
}
