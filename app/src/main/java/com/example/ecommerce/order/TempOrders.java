package com.example.ecommerce.order;


import com.example.ecommerce.order.OrderProductsItem;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
public class TempOrders implements Serializable {
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("productId")
    private String productId;
    @SerializedName("productName")
    private  String productName;
    private double price;
    private int quantity;
    private String image;
    private String merchantId;

    @SerializedName("userId")
    private String userId;
    @SerializedName("orderDate")
    private Date orderDate;

    @Override
    public String toString() {
        return "TempOrders{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}