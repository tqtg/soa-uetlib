package phong.nt.qltv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.json.simple.JSONObject;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AddNewBookFrame extends JFrame implements ActionListener {

	private BookPanel panel;

	@SuppressWarnings({})
	AddNewBookFrame() {
		setTitle("Add book");
		setSize(500, 565);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new BookPanel("Summit", this);
		getContentPane().add(panel);

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
			JSONObject book = new JSONObject();
			book.put("title", bookName);
			book.put("author", author);
			book.put("category", category);
			book.put("page", page);
			book.put("publisher", publisher);
			book.put("date", date);
			book.put("image", imageLink);
			book.put("description", description);
			try {
				int response = Function.createBook(book);
				if (response == 200) {
					int result = JOptionPane.showConfirmDialog(this, "Success!\nRefresh the table to see the lastest update!", "Infomation",
							JOptionPane.CLOSED_OPTION);
					if (result == 0) {
						setVisible(false);
						dispose();
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}
	}
}
