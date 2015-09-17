package soa.functiondemo.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Function {
	private static final String USER_AGENT = "Mozilla/5.0";
//	private static final String IP = "http://128.199.89.183";
	private static final String IP = "http://localhost";
	private static final String PORT = "3000";
	
	// GET
	public static String getAllBooks() throws Exception {
		String url = IP + ":" + PORT + "/books/api";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending GET request to URL: " + url);
		System.out.println("Response Code: " + responseCode);
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println(response.toString());
//		parseJSON(response.toString());
		
		return response.toString();
	}
	
	// POST
	@SuppressWarnings("unchecked")
	public static void createBook(JSONObject book) throws Exception {
		String url = IP + ":" + PORT + "/books/api";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Charset", "UTF-8"); 
		con.setRequestProperty("Content-Type", "application/json");
		
		// encode book data to UTF-8
		for (Object key : book.keySet()) {
			String encodedValue = URLEncoder.encode((String)book.get(key), "UTF-8").replace("+", "%20");
			book.put(key, encodedValue);
		}
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(book.toJSONString());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + book.toJSONString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
	
	// PUT
	@SuppressWarnings("unchecked")
	public static void editBook(String id, JSONObject editedBook) throws Exception {
		String url = IP + ":" + PORT + "/books/api/" + id;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Charset", "UTF-8");
		con.setRequestProperty("Content-Type", "application/json"); 

		// encode book data to UTF-8
		for (Object key : editedBook.keySet()) {
			String encodedValue = URLEncoder.encode((String)editedBook.get(key), "UTF-8").replace("+", "%20");
			editedBook.put(key, encodedValue);
		}
		
		// Send put request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(editedBook.toJSONString());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'PUT' request to URL : " + url);
		System.out.println("Post parameters : " + editedBook.toJSONString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
	
	// PUT
	public static void delete(String id) throws Exception {
		String url = IP + ":" + PORT + "/books/api/" + id;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("DELETE");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		// Send delete request
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'DELETE' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
}
