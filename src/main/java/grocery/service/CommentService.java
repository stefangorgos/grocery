package grocery.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import grocery.model.Comment;
import grocery.model.Product;
import grocery.repository.CommentRepository;

@ManagedBean(name = "commentService")
@SessionScoped
public class CommentService {
	private CommentRepository commentRepository = new CommentRepository();
	private Comment selectedComment = new Comment();
	private Product selectedProduct;
	
	public void init() throws SQLException {
		if (selectedComment.getId() == null) {
			selectedComment = new Comment();
		} else {
			selectedComment = commentRepository.getCommentById(selectedComment.getId());
		}
		selectedProduct = new Product();
		selectedComment.setProduct(selectedProduct);
	}
	
	public List<Comment> getComments() {
		try {
			return commentRepository.getComments();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public List<Comment> getAll() {
		try {
			return commentRepository.getComments();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public List<Comment> getCommentsByProduct(int productId) {
		try {
			return commentRepository.getCommentsByProduct(productId);
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public String saveComment() throws IOException {
		selectedComment.setDate(new Date());
		try {
			commentRepository.persisteComment(selectedComment);
			return "comments";
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public void updateComment() throws SQLException {
		commentRepository.updateComment(selectedComment);
	}
	
	public Comment getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	
}
