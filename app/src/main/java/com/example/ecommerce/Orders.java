package com.example.ecommerce;

import com.google.gson.annotations.SerializedName;


public class Orders{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("orderId")
	private String orderId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private int price;

	@SerializedName("userId")
	private String userId;

	@SerializedName("orderDate")
	private String orderDate;

	@SerializedName("productName")
	private String productName;

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

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
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
			"Orders{" + 
			"quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",userId = '" + userId + '\'' + 
			",orderDate = '" + orderDate + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}