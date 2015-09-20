package crawler.tiki.books;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
	private static final String BASE_URL = "http://tiki.vn";
	private static final String USER_AGENT = "Mozilla";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String url = BASE_URL + "/sach-truyen-tieng-viet";
		Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
		System.out.println("Fetching " + url);
		
		JSONArray books = new JSONArray();
		JSONArray bookCategories = new JSONArray();
		
		Elements bookCategory = doc.select("#collapse-category > div > div > a");
		for (Element category : bookCategory) {
			String href = category.attr("href");
			String categoryId = href.substring(href.lastIndexOf("/") + 1);
			String categoryName = category.text();
			
			JSONObject col = new JSONObject();
			col.put("id", categoryId);
			col.put("name", categoryName);
			bookCategories.add(col);
			
			int nPage = 5;
			for (int i = 1; i <= nPage; i++) {
				url = BASE_URL + href + "?page=" + i;
				System.out.println(url);
				books.addAll(crawl(url, categoryId));
			}
			
		}
		
		write(books.toJSONString(), "../output/books.json");
		write(bookCategories.toJSONString(), "../output/categories.json");

		System.out.println("Done!");
	}
	
	@SuppressWarnings("unchecked")
	private static JSONArray crawl(String url, String categoryId) throws Exception {
		JSONArray books = new JSONArray();
		
		Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
		System.out.println("Fetching " + url);
		
		Elements products = doc.select("body > div.wrap > div > div > div.col-lg-9 > div.product-box.no-mg > div.product-box-list > div");
		for (Element product : products) {
			JSONObject book = new JSONObject();
			book.put("title", product.select("a").attr("title"));
			book.put("author", product.select("p.author").text());
			book.put("category", categoryId);
			book.put("image", product.select("a > span.image > img").attr("src"));
			
			String bookURL = BASE_URL + product.select("a").attr("href");
			book.putAll(crawlBook(bookURL));
			
			books.add(book);
		}
		
		return books;
	}
	
	@SuppressWarnings("unchecked")
	private static JSONObject crawlBook(String url) throws Exception {
		JSONObject book = new JSONObject();
		Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
		System.out.println("Fetching " + url);

		Elements detailTable = doc.select("#chi-tiet > tbody > tr");
		for (Element e : detailTable) {
			if (book.size() == 3) break;
			
			String key = e.children().get(0).text();
			String value = e.children().get(1).text();
			
			if (key.equals("Nhà xuất bản")) {
				book.put("publisher", value);
				continue;
			}
			
			if (key.equals("Ngày xuất bản")) {
				book.put("date", value);
				continue;
			}
			
			if (key.equals("Số trang")) {
				book.put("page", Integer.parseInt(value));
			}
		}
		
		// Get description
		Elements description = doc.select("#gioi-thieu").first().children();
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < description.size(); i++) {
			builder.append(description.get(i).text() + "\n");
		}
		book.put("description", builder.toString());
		
		return book;
	}
	
	
	private static void write(String s, String filename) {
		try {
			File file = new File(filename);
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(s);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
