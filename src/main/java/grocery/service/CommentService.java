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
	private Comment selectedComment;
	private Product selectedProduct;
	
	public void init() {
		selectedComment = new Comment();
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
	
	public String saveComment() throws IOException {
		selectedComment.setDate(new Date());
		try {
			commentRepository.persisteComment(selectedComment);
			return "comments";
		} catch (SQLException e) {
			throw new IOException(e);
		}
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
