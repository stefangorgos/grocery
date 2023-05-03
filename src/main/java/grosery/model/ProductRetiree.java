package grosery.model;

public class ProductRetiree extends Product {
	private double discount;
    public ProductRetiree(String name, Double price, Boolean inStock, Double discount) {
        super(name, price, inStock);
        this.discount = discount;
    }
    
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
