package grocery.model;

import java.util.Date;

public class ProductExpirables extends Product {
	private Date expirationDate;
    public ProductExpirables(int id, String name, double price, boolean inStock, Date expirationDate) {
        super(name, price, inStock);
        this.expirationDate = expirationDate;
    }
    public Date getExpirationDate() {
    	return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
    	this.expirationDate = expirationDate;
    }
}