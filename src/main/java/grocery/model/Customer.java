package grocery.model;

import java.util.Date;

public class Customer {
	private Integer id;
	private String name;
	private Date customerSince;
	
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
	
	public Date getCustomerSince() {
		return customerSince;
	}
	
	public void setCustomerSince(Date customer_since) {
		this.customerSince = customer_since;
	}
	
}
