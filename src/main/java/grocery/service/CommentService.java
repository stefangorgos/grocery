package grocery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import grocery.model.Comment;
import grocery.repository.CommentRepository;

@ManagedBean(name = "commentService"/* , eager = true */)
public class CommentService {
	private CommentRepository commentRepository = new CommentRepository();
	
	public List<Comment> getComments() {
		try {
			return commentRepository.getComments();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
}
