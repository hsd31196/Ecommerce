package com.example.ecommerce.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ecommerce.Url;

@Entity
public class CartRoomEntity {
    @NonNull
    @PrimaryKey
    private  String productId;
    // private String cartId;
    private String productName;
   // private String merchantId;
    private String imageUrl;
    private double totalAmount;
    private int  qty;

    @NonNull
//    public String getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(@NonNull String cartId) {
//        this.cartId = cartId;
//    }

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
//
//    public String getMerchantId() {
//        return merchantId;
//    }
//
//    public void setMerchantId(String merchantId) {
//        this.merchantId = merchantId;
//    }

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
}
