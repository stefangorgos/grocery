package grocery.model;

public class ProductRetiree extends Product {
	private double discount;
    public ProductRetiree(String name, Double price, Boolean inStock, String imagePath, Double discount) {
        super(name, price, inStock, imagePath);
        this.discount = discount;
    }
    
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
