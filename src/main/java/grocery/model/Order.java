package grocery.model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private Integer id;
	private Integer customer_id;
	private Date date;
	private ArrayList<OrderLines> orderLines;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<OrderLines> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(ArrayList<OrderLines> orderLines) {
		this.orderLines = orderLines;
	}

	
}
