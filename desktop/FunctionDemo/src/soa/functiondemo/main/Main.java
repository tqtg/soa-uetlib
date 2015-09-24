package soa.functiondemo.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String username = "admin";
		String password = "admin";
		
		if (Function.login(username, password)) {
			String result = Function.getAllBooks();
			parseJSON(result);
			
			JSONObject book = new JSONObject();
			book.put("title", "Cho tôi xin một vé về tuổi thơ");
			book.put("author", "Nguyễn Nhật Ánh");
			Function.createBook(book);
			
//			JSONObject editedBook = new JSONObject();
//			String id = "55faeb7f09787020112f3d98";
//			editedBook.put("title", "Cho tôi xin một vé đi tuổi thơ");
//			Function.editBook(id, editedBook);
//			
//			String deletedId = "55feebe8cb2cd5020ec97a4f";
//			Function.delete(deletedId);
		} else {
			System.out.println("Wrong username or password!");
		}
	}
	
	// Parse string to json object
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
