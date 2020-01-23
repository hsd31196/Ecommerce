package com.example.ecommerce;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {

    @SerializedName( "productId")
    private String productId;

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

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @SerializedName("productName")
    private String productName;
    @SerializedName("mrp")
    private double price;
    @SerializedName("imageURL")
    private List<String> imgurls;
    @SerializedName("productRating")
    private double rating;
    @SerializedName("description")
    private String description;

    @SerializedName("specification")
    private String  specification;
    @SerializedName("review")
    private String reviews;

    @SerializedName("merchantId")
    private String merchantId;

    @SerializedName("productStock")
    private int productStock;

    @SerializedName("merchantPrice")
    private double discountedPrice;






    public Products(String productName, int price, List<String> imgurls, int rating, String description, String specification, String reviews) {
        this.productName = productName;
        this.price = price;
        this.imgurls = imgurls;
        this.rating = rating;
        this.description = description;
        this.specification = specification;
        this.reviews = reviews;
    }

    public List<String> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<String> imgurls) {
        this.imgurls = imgurls;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String  specification) {
        this.specification = specification;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", imgurl='" + imgurls + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}

