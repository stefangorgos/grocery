package grocery.model;

import java.time.LocalDate;

public class ProductExpirables extends Product {
	private LocalDate expirationDate;
    public ProductExpirables(int id, String name, double price, boolean inStock, LocalDate expirationDate) {
        super(name, price, inStock);
        this.expirationDate = expirationDate;
    }
    public LocalDate getExpirationDate() {
    	return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
    	this.expirationDate = expirationDate;
    }
}