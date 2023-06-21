package grocery.model;

public class OrderLines {
	private Integer lines_id;
	private Integer order_id;
	private Integer product_id;
	private Double purchase_price;
	private Integer quantity;
	
	
	public Integer getLines_id() {
		return lines_id;
	}
	public void setLines_id(Integer lines_id) {
		this.lines_id = lines_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Double getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(Double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
