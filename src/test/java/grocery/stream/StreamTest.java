package grocery.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import grocery.json.Shipment;

public class StreamTest {
	@Test
	public void simpleListStreamTest() {
		List<Integer> intList = Arrays.asList(1,5,2,19,12,16,32,3);
		System.out.println(intList.stream().count());
		System.out.println(intList.stream().filter(x -> x <= 10).toList());
		System.out.println(intList.stream().filter(x -> x > 10).toList());
		System.out.println(intList.stream().map(x-> x*x).toList());		
	}
	
	@Test
	public void simpleArrayStreamTest() {
		Integer[] intArray = {1,6,32,2,7,9,128,80};
		System.out.println(Arrays.stream(intArray).count());
		System.out.println(Arrays.stream(intArray).filter(x -> x >= 30).toList());
		System.out.println(Arrays.stream(intArray).map(x -> x*x).toList());
		System.out.println(Arrays.stream(intArray).map(x -> {
			if (x < 30) {
				return x*x;
			} else {
				return -x*x;
			}
		}).toList());
	}
	
	@Test
	public void shipmentStreamTest() {
		Shipment shipment1 = new Shipment(1, 20, "small quantity of goods");
		Shipment shipment2 = new Shipment(2, 400, "big container");
		Shipment shipment3 = new Shipment(3, 5000, "multiple containers");
		
		Shipment[] shipments = {shipment1, shipment2, shipment3};
//		Shipment[] shipments = new Shipment[3];
//		shipments[0] = shipment1;
//		shipments[1] = shipment2;
//		shipments[2] = shipment3;
		Arrays.stream(shipments).forEach(x -> System.out.println(x));
		
	}
}
