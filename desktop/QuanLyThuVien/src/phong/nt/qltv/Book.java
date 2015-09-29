package phong.nt.qltv;

import org.json.simple.JSONObject;

public class Book {
	private String id;
	private String title;
	private String author;
	private String category;
	private Long page;
	private String publisher;
	private String date;
	private String image;
	private String description;

	Book(JSONObject obj) {
		this.id = (String) obj.get("_id");
		this.title = (String) obj.get("title");
		this.author = (String) obj.get("author");
		this.category = (String) obj.get("category");
		this.page = (Long) obj.get("page");
		this.publisher = (String) obj.get("publisher");
		this.date = (String) obj.get("date");
		this.image = (String) obj.get("image");
		this.description = (String) obj.get("description");
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public Long getPage() {
		return page;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getDate() {
		return date;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

}
