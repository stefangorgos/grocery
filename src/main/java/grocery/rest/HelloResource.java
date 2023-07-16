package grocery.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text")
	public String direBonjour() {
		return "Bonjour, tout le monde!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/json")
	public String sayJsonHello() {
	    return "{\"name\":\"greeting\", \"message\":\"Bonjour tout le monde!\"}";
	}
}