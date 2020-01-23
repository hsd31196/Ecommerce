package com.example.ecommerce;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProductsItem  implements Serializable {


    @SerializedName("quantity")
    private int quantity;
    @SerializedName("productId")
    private String productId;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("price")
    private double price;
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setProductId(String productId){
        this.productId = productId;
    }

    public String getProductId(){
        return productId;
    }

    public void setMerchantId(String merchantId){
        this.merchantId = merchantId;
    }

    public String getMerchantId(){
        return merchantId;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductName(){
        return productName;
    }

    @Override
    public String toString(){
        return
                "OrderProductsItem{" +
                        "quantity = '" + quantity + '\'' +
                        ",productId = '" + productId + '\'' +
                        ",merchantId = '" + merchantId + '\'' +
                        ",price = '" + price + '\'' +
                        ",id = '" + id + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",productName = '" + productName + '\'' +
                        "}";
    }
}
