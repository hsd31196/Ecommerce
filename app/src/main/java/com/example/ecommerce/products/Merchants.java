package com.example.ecommerce.products;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Merchants{

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("productStock")
	private int productStock;

	@SerializedName("merchantPrice")
	private int merchantPrice;

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

	public void setProductStock(int productStock){
		this.productStock = productStock;
	}

	public int getProductStock(){
		return productStock;
	}

	public void setMerchantPrice(int merchantPrice){
		this.merchantPrice = merchantPrice;
	}

	public int getMerchantPrice(){
		return merchantPrice;
	}

	@Override
 	public String toString(){
		return 
			"Merchants{" + 
			"productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",productStock = '" + productStock + '\'' + 
			",merchantPrice = '" + merchantPrice + '\'' + 
			"}";
		}
}