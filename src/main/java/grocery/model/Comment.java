package grocery.model;

import java.util.Date;

public class Comment {
	private Integer id;
	private String text;
	private Date date;
	private Number rating;
	private Integer productId;
	private Product product;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Number getRating() {
		return rating;
	}
	
	public void setRating(Number rating) {
		this.rating = rating;
	}
	
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
