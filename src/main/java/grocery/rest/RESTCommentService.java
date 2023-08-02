package grocery.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import grocery.model.Comment;
import grocery.repository.CommentRepository;

@Path("/")
public class RESTCommentService {
	private CommentRepository commentRepository = new CommentRepository();

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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/comments")
	public Response comments() {
	    try {
			List<Comment> comments = commentRepository.getComments();
			if (comments.isEmpty()) {
		        return Response.noContent().build();
		    }
		    return Response.ok(comments).build();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	    
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list-comments")
	public List<Comment> listComments() {
	    try {
			List<Comment> comments = commentRepository.getComments();
		    return comments;	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	@GET
	@Path("/comments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
	    Comment comment = null;
		try {
			comment = commentRepository.getCommentById(id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	    if (comment != null) {
	        return Response.ok(comment, MediaType.APPLICATION_JSON).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).build();
	    }
	}
}