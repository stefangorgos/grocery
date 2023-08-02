package grocery.model;

import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Boolean inStock;
    private List<Comment> comments;
    private String imagePath;

	public Product() {
	}

    public Product(String name, Double price, Boolean inStock, String imagePath) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.imagePath = imagePath;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", inStock=" + inStock + "]";
	}
}

