package soa.assignment.uetlib.model;

/**
 * Created by TuanTQ on 9/23/15.
 */
public class BookItem {
    private static final String TAG = "Book";

    private int image;
    private String title;
    private String author;

    public BookItem (int image, String title, String author) {
        super();
        this.setImage(image);
        this.setTitle(title);
        this.setAuthor(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
