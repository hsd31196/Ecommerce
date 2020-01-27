package com.example.ecommerce;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TokenClass  {
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("tokenType")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "TokenClass{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
