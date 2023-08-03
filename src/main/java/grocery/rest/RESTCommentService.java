package grocery.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
	@Path("/all-comments")
	public List<Comment> allComments() {
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
	public Response getComment(@PathParam("id") int id) {
	    Comment comment;
		try {
			comment = commentRepository.getCommentById(id);
		    return Response.ok(comment).build();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@GET
	@Path("/all-comments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment2(@PathParam("id") int id) {
	    Comment comment;
		try {
			comment = commentRepository.getCommentById(id);
		    return comment;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/comment/{id}")
	public Response update(@PathParam("id") int id, Comment comment) {
	    comment.setId(id);
	    try {
			commentRepository.updateComment(comment);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	    return Response.ok().build();
	}
	
	@DELETE
	@Path("/comment/{id}")
	public Response delete(@PathParam("id") int id) {
	    try {
			commentRepository.deleteCommentById(id);
		    return Response.ok().build();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}