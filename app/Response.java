package null;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("reviews")
	private String reviews;

	@SerializedName("image_url")
	private List<String> imageUrl;

	@SerializedName("price")
	private int price;

	@SerializedName("rating")
	private int rating;

	@SerializedName("description")
	private String description;

	@SerializedName("specification")
	private String specification;

	@SerializedName("product_name")
	private String productName;

	public void setReviews(String reviews){
		this.reviews = reviews;
	}

	public String getReviews(){
		return reviews;
	}

	public void setImageUrl(List<String> imageUrl){
		this.imageUrl = imageUrl;
	}

	public List<String> getImageUrl(){
		return imageUrl;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
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

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"reviews = '" + reviews + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",price = '" + price + '\'' + 
			",rating = '" + rating + '\'' + 
			",description = '" + description + '\'' + 
			",specification = '" + specification + '\'' + 
			",product_name = '" + productName + '\'' + 
			"}";
		}
}