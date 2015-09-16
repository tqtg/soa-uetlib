package soa.functiondemo.main;

import org.json.simple.JSONObject;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		// Get all books
		Function.getAllBooks();
		
		// Create a book
		JSONObject book = new JSONObject();
		book.put("title", "Tôi thấy hoa vàng trên cỏ xanh");
		book.put("author", "Nguyễn Nhật Ánh");
		Function.createBook(book);
		
		// Edit a book by _id
		JSONObject editedBook = new JSONObject();
		String id = "55f52daac38bedc5121ac05f";
		editedBook.put("title", "Cho tôi xin một vé đi tuổi thơ");
		Function.editBook(id, editedBook);
		
		// Delete a book by _id
		String deletedId = "55f52daac38bedc5121ac05f";
		Function.delete(deletedId);
	}
}
