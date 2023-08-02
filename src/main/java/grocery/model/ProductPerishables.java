package grocery.model;

import java.util.Date;

public class ProductPerishables extends ProductExpirables {
	private double dailyDiscount;
    public ProductPerishables(int id, String name, double price, boolean inStock, String imagePath, Date expirationDate, double dailyDiscount) {
        super(id, name, price, inStock, imagePath, expirationDate);
        this.setDailyDiscount(dailyDiscount);
    }
	public double getDailyDiscount() {
		return dailyDiscount;
	}
	public void setDailyDiscount(double dailyDiscount) {
		this.dailyDiscount = dailyDiscount;
	}
}
