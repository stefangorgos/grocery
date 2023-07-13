package grocery.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shipment {
	@JsonProperty("id")
	private Integer shipmentId;
	private Integer quantity;
	private String description;
	
	
	
	public Shipment() {
		super();
	}
	public Shipment(Integer shipmentId, Integer quantity, String description) {
		super();
		this.shipmentId = shipmentId;
		this.quantity = quantity;
		this.description = description;
	}
	public Integer getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", quantity=" + quantity + ", description=" + description + "]";
	}
	
	
	
}
