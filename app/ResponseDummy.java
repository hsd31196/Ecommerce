package null;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ResponseDummy{

	@SerializedName("productId")
	private String productId;

	@SerializedName("productStock")
	private int productStock;

	@SerializedName("description")
	private String description;

	@SerializedName("specification")
	private String specification;

	@SerializedName("mrp")
	private int mrp;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("productName")
	private String productName;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("review")
	private String review;

	@SerializedName("imageURL")
	private List<String> imageURL;

	@SerializedName("merchantPrice")
	private int merchantPrice;

	@SerializedName("productRating")
	private int productRating;

	@SerializedName("categoryId")
	private String categoryId;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductStock(int productStock){
		this.productStock = productStock;
	}

	public int getProductStock(){
		return productStock;
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

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setReview(String review){
		this.review = review;
	}

	public String getReview(){
		return review;
	}

	public void setImageURL(List<String> imageURL){
		this.imageURL = imageURL;
	}

	public List<String> getImageURL(){
		return imageURL;
	}

	public void setMerchantPrice(int merchantPrice){
		this.merchantPrice = merchantPrice;
	}

	public int getMerchantPrice(){
		return merchantPrice;
	}

	public void setProductRating(int productRating){
		this.productRating = productRating;
	}

	public int getProductRating(){
		return productRating;
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
			"ResponseDummy{" + 
			"productId = '" + productId + '\'' + 
			",productStock = '" + productStock + '\'' + 
			",description = '" + description + '\'' + 
			",specification = '" + specification + '\'' + 
			",mrp = '" + mrp + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",productName = '" + productName + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",review = '" + review + '\'' + 
			",imageURL = '" + imageURL + '\'' + 
			",merchantPrice = '" + merchantPrice + '\'' + 
			",productRating = '" + productRating + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}