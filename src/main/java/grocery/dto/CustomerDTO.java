package grocery.dto;

import java.sql.Date;

public class CustomerDTO {
	private Integer id;
	private String name;
	private Date customerSince;
	private Integer orderCount;
	private Double totalSpent;
	
	
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
	public void setCustomerSince(Date customerSince) {
		this.customerSince = customerSince;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(Double totalSpent) {
		this.totalSpent = totalSpent;
	}
	
	

}
