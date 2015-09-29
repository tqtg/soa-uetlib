package phong.nt.qltv;

import javax.swing.JFrame;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class EditBookFrame extends JFrame {

	public BookPanel panel;
	public String id;
	public JSONObject book;
	public boolean responseCode;

	public EditBookFrame(JSONObject edittingBook) {
		this.responseCode = false;
		this.id = (String) edittingBook.get("_id");
		this.book = edittingBook;

		setTitle("Edit book");
		setSize(500, 565);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new BookPanel("Summit");
		getContentPane().add(panel);

		String bookName = (String) book.get("title");
		String author = (String) book.get("author");
		String category = (String) book.get("category");
		Long pageLong = (Long) book.get("page");
		String page = "";
		if (pageLong != null) {
			page = pageLong.toString();
		}
		String publisher = (String) book.get("publisher");
		String date = (String) book.get("date");
		String imageLink = (String) book.get("image");
		String description = (String) book.get("description");

		panel.nameText.setText(bookName);
		panel.authorText.setText(author);
		panel.categoryBox.setSelectedIndex(Helper.indexOfCategoryCode(category));
		panel.pageText.setText(page);
		panel.publisherText.setText(publisher);
		panel.dateText.setText(date);
		panel.imageText.setText(imageLink);
		panel.desText.setText(description);

		setLocationRelativeTo(null);
	}

}
