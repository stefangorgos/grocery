package grocery.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import grocery.model.Customer;
import grocery.model.Order;
import grocery.model.OrderLine;
import grocery.model.Product;
import grocery.repository.CustomerRepository;
import grocery.repository.OrderRepository;
import grocery.repository.ProductRepository;

@ManagedBean(name = "orderService")
@SessionScoped
public class OrderService {
	private OrderRepository orderRepository = new OrderRepository();
	private CustomerRepository customerRepository = new CustomerRepository();
	private ProductRepository productRepository = new ProductRepository();
	private Order selectedOrder = new Order();
	private OrderLine selectedOrderLine;
	private Customer selectedCustomer;
	private List<Customer> customers;
	private List<Product> products; 
	
	public void init() throws SQLException {
		if (selectedOrder.getId() == null) {
			selectedOrder = new Order();
		} else {
			selectedOrder = orderRepository.getOrderById(selectedOrder.getId());
		}
		selectedCustomer = new Customer();
		selectedOrderLine = new OrderLine();	
		products = productRepository.readProducts();
		customers = customerRepository.readCustomers();
	}
	

	
	public List<OrderLine> getOrderLines() {
		try {
			return orderRepository.getOrderLines(selectedOrder.getId());
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public List<Order> getOrders() {
		try {
			return orderRepository.getOrders();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public String saveOrder() throws IOException {
		selectedOrder.setDate(new Date());
		
		try {
			selectedOrder = orderRepository.createOrder(selectedOrder);
//			selectedOrderLine.setId(selectedOrder.getId());
//			orderRepository.createOrderDetails(selectedOrder, selectedOrderLine);
			return "orders";
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public String saveOrderLine() throws IOException {
		selectedOrderLine.setOrderId(selectedOrder.getId());
		try {
			orderRepository.createOrderDetails(selectedOrder, selectedOrderLine);
			return "orders";
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	


	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public OrderLine getSelectedOrderLine() {
		return selectedOrderLine;
	}

	public void setSelectedOrderLine(OrderLine selectedOrderLines) {
		this.selectedOrderLine = selectedOrderLines;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
