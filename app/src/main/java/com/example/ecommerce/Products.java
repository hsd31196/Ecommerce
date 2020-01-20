package com.example.ecommerce;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {

    @SerializedName("image_url")
    private String productName;
    @SerializedName("price")
    private int price;
    @SerializedName("image_url")
    private List<String> imgurls;
    @SerializedName("rating")
    private int rating;
    @SerializedName("description")
    private String description;

    @SerializedName("specification")
    private ProductSpecifications specification;
    @SerializedName("reviews")
    private String reviews;



    public Products(String productName, int price, List<String> imgurls, int rating, String description, ProductSpecifications specification, String reviews) {
        this.productName = productName;
        this.price = price;
        this.imgurls = imgurls;
        this.rating = rating;
        this.description = description;
        specification = specification;
        this.reviews = reviews;
    }

    public List<String> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<String> imgurls) {
        this.imgurls = imgurls;
    }

    public int getRating() {
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

    public ProductSpecifications getSpecification() {
        return specification;
    }

    public void setSpecification(ProductSpecifications specification) {
        specification = specification;
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

    public int getPrice() {
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
                ", specification=" + specification +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}
