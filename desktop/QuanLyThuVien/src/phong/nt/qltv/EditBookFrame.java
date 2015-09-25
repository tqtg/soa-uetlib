package phong.nt.qltv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class EditBookFrame extends JFrame implements ActionListener {

	private BookPanel panel;
	private String id;
	private JSONObject book;
	private JSONObject editedBook;
	private boolean responseCode;

	public EditBookFrame(JSONObject edittingBook) {
		this.responseCode = false;
		this.id = (String) edittingBook.get("_id");
		;
		this.book = edittingBook;

		setTitle("Edit book");
		setSize(500, 565);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new BookPanel("Summit", this);
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

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String bookName = panel.nameText.getText();
		String author = panel.authorText.getText();
		int indexCategory = panel.categoryBox.getSelectedIndex();
		if (indexCategory == -1)
			indexCategory = 0;
		String category = Helper.CATEGORY_CODE[indexCategory];
		String page = panel.pageText.getText();
		String publisher = panel.publisherText.getText();
		String date = panel.dateText.getText();
		String imageLink = panel.imageText.getText();
		String description = panel.desText.getText();

		if (bookName.equals("")) {
			System.out.println("Error - Book has no name!");
			JOptionPane.showMessageDialog(this, "Plese enter the book's name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JSONObject newBook = new JSONObject();
			newBook.put("title", bookName);
			newBook.put("author", author);
			newBook.put("category", category);
			newBook.put("page", page);
			newBook.put("publisher", publisher);
			newBook.put("date", date);
			newBook.put("image", imageLink);
			newBook.put("description", description);
			try {
				int response = Function.editBook(this.id, newBook);
				if (response == 200) {
					int result = JOptionPane.showConfirmDialog(this, "Success!", "Infomation",
							JOptionPane.CLOSED_OPTION);
					if (result == 0) {
						setVisible(false);
						dispose();
					}
					this.responseCode = true;
					this.editedBook = newBook;
					this.editedBook.put("_id", this.id);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				this.responseCode = false;
			}

		}
	}

	public boolean getResponseCode() {
		return this.responseCode;
	}

	public JSONObject getNewBook() {
		if (responseCode == false)
			return this.book;
		return this.editedBook;
	}

}
