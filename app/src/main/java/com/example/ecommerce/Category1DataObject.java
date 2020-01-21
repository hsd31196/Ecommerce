package com.example.ecommerce;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Category1DataObject {

	@SerializedName("productId")
	private String productId;

	@SerializedName("p_rating")
	private String pRating;

	@SerializedName("pname")
	private String pname;

	@SerializedName("review")
	private String review;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("description")
	private String description;

	@SerializedName("specification")
	private String specification;

	@SerializedName("mrp")
	private int mrp;

	@SerializedName("categoryId")
	private String categoryId;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setPRating(String pRating){
		this.pRating = pRating;
	}

	public String getPRating(){
		return pRating;
	}

	public void setPname(String pname){
		this.pname = pname;
	}

	public String getPname(){
		return pname;
	}

	public void setReview(String review){
		this.review = review;
	}

	public String getReview(){
		return review;
	}

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSpecification(String specification){
		this.specification = specification;
	}

	public String getSpecification(){
		return specification;
	}

	public void setMrp(int mrp){
		this.mrp = mrp;
	}

	public int getMrp(){
		return mrp;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Category1DataObject{" +
			"productId = '" + productId + '\'' + 
			",p_rating = '" + pRating + '\'' + 
			",pname = '" + pname + '\'' + 
			",review = '" + review + '\'' + 
			",imageURL = '" + imageURL + '\'' + 
			",description = '" + description + '\'' + 
			",specification = '" + specification + '\'' + 
			",mrp = '" + mrp + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}