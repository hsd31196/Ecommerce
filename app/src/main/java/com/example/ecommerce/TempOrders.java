package com.example.ecommerce;


import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Date;
public class TempOrders {
    @SerializedName("orderProducts")
    private List<OrderProductsItem> cartProducts;
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("productId")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @SerializedName("userId")
    private String userId;
    @SerializedName("orderDate")
    private Date orderDate;

    public void setCartProducts(List<OrderProductsItem> cartProducts){
        this.cartProducts = cartProducts;
    }

    public List<OrderProductsItem> getCartProducts(){
        return cartProducts;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public String getOrderId(){
        return orderId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }

    public Date getOrderDate(){
        return orderDate;
    }

    @Override
    public String toString(){
        return
                "TempOrders{" +
                        "cartProducts = '" + cartProducts + '\'' +
                        ",orderId = '" + orderId + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",orderDate = '" + orderDate + '\'' +
                        "}";
    }
}