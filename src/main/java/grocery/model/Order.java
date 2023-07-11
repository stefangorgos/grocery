package grocery.model;

import java.util.Date;
import java.util.ArrayList;

public class Order {
	private Integer id;
	private Integer customerId;
	private String customerName;
	private Date date;
	private ArrayList<OrderLine> orderLines;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<OrderLine> getOrderLines() {
		return orderLine;
	}
	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLine = orderLine;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
}
