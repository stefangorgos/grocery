package grosery.model;

import java.time.LocalDate;

public class ProductPerishables extends ProductExpirables {
	private double dailyDiscount;
    public ProductPerishables(int id, String name, double price, boolean inStock, LocalDate expirationDate, double dailyDiscount) {
        super(id, name, price, inStock, expirationDate);
        this.setDailyDiscount(dailyDiscount);
    }
	public double getDailyDiscount() {
		return dailyDiscount;
	}
	public void setDailyDiscount(double dailyDiscount) {
		this.dailyDiscount = dailyDiscount;
	}
}
