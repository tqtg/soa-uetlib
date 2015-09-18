package soa.functiondemo.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String cookie = Function.login("admin", "admin");
//		System.out.println(cookie);
		
		// Get all books
//		String result = Function.getAllBooks(cookie);
//		parseJSON(result);
//		
		// Create a book
//		JSONObject book = new JSONObject();
//		book.put("title", "Cho tôi xin một vé về tuổi thơ");
//		book.put("author", "Nguyễn Nhật Ánh");
//		Function.createBook(cookie, book);
//		
////		// Edit a book by _id
//		JSONObject editedBook = new JSONObject();
//		String id = "55faeb7f09787020112f3d98";
//		editedBook.put("title", "Cho tôi xin một vé đi tuổi thơ");
//		Function.editBook(cookie, id, editedBook);
////		
////		// Delete a book by _id
//		String deletedId = "55fb05951533b3281c143e73";
//		Function.delete(cookie, deletedId);
	}
	
	// Parse string to json object
	@SuppressWarnings("unused")
	private static void parseJSON(String s) throws Exception {
		System.out.println("================");
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(s);
		
		JSONArray array = (JSONArray) obj;
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			System.out.println(object.get("title"));
		}
		
		System.out.println("================");
		System.out.println("Total books: " + array.size());
	}
}
