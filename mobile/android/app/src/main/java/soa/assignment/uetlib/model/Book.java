package soa.assignment.uetlib.model;

import android.graphics.drawable.Drawable;

import org.json.JSONObject;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class Book {
    private static final String TAG = "Book";

    private String title;
    private String author;
    private String category;
    private int page;
    private String publisher;
    private String date;
    private String description;

    private Drawable image;

    public Book() {

    }

    public Book(JSONObject book) {
        try {
            this.title = book.getString("title");
            this.author = book.getString("author");
            this.description = book.getString("description");
            this.category = book.getString("category");
            this.page = book.getInt("page");
            this.publisher = book.getString("publisher");
            this.date = book.getString("date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPage() {
        return page;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
