package grocery.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.junit.Test;

public class EmployeeJSONWriterTest {

	@Test
	public void testBuildEmployee() throws FileNotFoundException {

		Employee emp = createEmployee();

		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();

		for (long phone : emp.getPhoneNumbers()) {
			phoneNumBuilder.add(phone);
		}
		
		addressBuilder.add("street", emp.getAddress().getStreet())
						.add("city", emp.getAddress().getCity())
							.add("zipcode", emp.getAddress().getZipcode());
		
		empBuilder.add("id", emp.getId())
					.add("name", emp.getName())
						.add("permanent", emp.isPermanent())
							.add("role", emp.getRole());
		
		empBuilder.add("phoneNumbers", phoneNumBuilder);
		empBuilder.add("address", addressBuilder);
		
		JsonObject empJsonObject = empBuilder.build();
		
		System.out.println("Employee JSON String\n"+empJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("src\\main\\resources\\out_employee.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(empJsonObject);
		jsonWriter.close();
	}
	
	@Test
	public void testBuildEmployee2() throws FileNotFoundException {
		Employee emp = new Employee();
		emp.setId(100);
		emp.setName("Matei");
		emp.setPermanent(false);
		emp.setPhoneNumbers(new Long[] {654321L, 121212L});
		emp.setRole("Coder");

		Address add = new Address();
		add.setCity("Chisinau");
		add.setStreet("Stefan cel mare");
		add.setZipcode(2005);
		emp.setAddress(add);
		
		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();
		
		for (long phone : emp.getPhoneNumbers()) {
			phoneNumBuilder.add(phone);
		}
		
		addressBuilder.add("street", emp.getAddress().getStreet())
						.add("city", emp.getAddress().getCity())
							.add("zipcode", emp.getAddress().getZipcode());
		
		empBuilder.add("id", emp.getId())
					.add("name", emp.getName())
						.add("permanent", emp.isPermanent())
							.add("role", emp.getRole());
		
		empBuilder.add("phoneNumbers", phoneNumBuilder);
		empBuilder.add("address", addressBuilder);
		
		JsonObject empJsonObject = empBuilder.build();
		
		System.out.println("Employee JSON String\n"+empJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("src\\main\\resources\\out_employee.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		jsonWriter.writeObject(empJsonObject);
		jsonWriter.close();
	}
	

	public static Employee createEmployee() {

		Employee emp = new Employee();
		emp.setId(100);
		emp.setName("David");
		emp.setPermanent(false);
		emp.setPhoneNumbers(new Long[] {123456L, 987654L});
		emp.setRole("Manager");

		Address add = new Address();
		add.setCity("Bangalore");
		add.setStreet("BTM 1st Stage");
		add.setZipcode(560100);
		emp.setAddress(add);

		return emp;
	}

}