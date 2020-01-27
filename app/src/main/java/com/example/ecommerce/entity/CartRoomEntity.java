package com.example.ecommerce.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class CartRoomEntity {
    @NonNull
    @PrimaryKey
    @SerializedName("productId")
    private  String productId;
    @SerializedName("userId")
     private String userId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("price")
    private double totalAmount;
    @SerializedName("quantity")
    private int  qty;

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartRoomEntity{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", totalAmount=" + totalAmount +
                ", qty=" + qty +
                '}';
    }
}
