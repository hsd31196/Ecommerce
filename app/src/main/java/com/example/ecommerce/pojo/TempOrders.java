package com.example.ecommerce.pojo;

import java.util.List;

public class TempOrders{
	private List<CartProductsItem> cartProducts;
	private String orderId;
	private String userId;
	private String orderDate;

	public void setCartProducts(List<CartProductsItem> cartProducts){
		this.cartProducts = cartProducts;
	}

	public List<CartProductsItem> getCartProducts(){
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

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
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