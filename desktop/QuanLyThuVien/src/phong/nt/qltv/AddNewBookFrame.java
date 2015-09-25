package phong.nt.qltv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.json.simple.JSONObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AddNewBookFrame extends JFrame implements ActionListener {

	private JPanel panel;
	private JLabel nameLabel;
	private JLabel authorLabel;
	private JLabel pageLabel;
	private JLabel publisherLabel;
	private JTextField nameText;
	private JTextField authorText;
	private JTextField pageText;
	private JTextField publisherText;
	private JButton submitButton;
	private JTextField dateText;
	private JTextField imageText;
	private JLabel collectionLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox collectionBox;
	private JLabel dateLabel;
	private JLabel imageLabel;
	private JLabel desLabel;
	private JTextArea desText;
	private JScrollPane scrollPane;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	AddNewBookFrame() {
		setTitle("Add book");
		setSize(500, 565);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		nameLabel = new JLabel("Title");
		nameLabel.setBounds(30, 30, 80, 25);
		panel.add(nameLabel);

		nameText = new JTextField(100);
		nameText.setBounds(120, 30, 320, 25);
		panel.add(nameText);

		authorLabel = new JLabel("Author");
		authorLabel.setBounds(30, 80, 80, 25);
		panel.add(authorLabel);

		authorText = new JTextField(50);
		authorText.setBounds(120, 80, 320, 25);
		panel.add(authorText);

		pageLabel = new JLabel("Page");
		pageLabel.setBounds(30, 180, 80, 25);
		panel.add(pageLabel);

		pageText = new JTextField(50);
		pageText.setBounds(120, 180, 320, 25);
		panel.add(pageText);

		publisherLabel = new JLabel("Publisher");
		publisherLabel.setBounds(30, 230, 80, 25);
		panel.add(publisherLabel);

		publisherText = new JTextField(50);
		publisherText.setBounds(120, 230, 320, 25);
		panel.add(publisherText);

		submitButton = new JButton("Submit");
		submitButton.setBounds(210, 485, 80, 25);
		submitButton.addActionListener(this);
		panel.add(submitButton);

		collectionLabel = new JLabel("Collection");
		collectionLabel.setBounds(30, 130, 80, 25);
		panel.add(collectionLabel);

		collectionBox = new JComboBox();
		collectionBox.setModel(new DefaultComboBoxModel(Helper.COLLECTION));
		collectionBox.setMaximumRowCount(16);
		collectionBox.setBounds(120, 130, 320, 25);
		panel.add(collectionBox);

		dateLabel = new JLabel("Date");
		dateLabel.setBounds(30, 280, 80, 25);
		panel.add(dateLabel);

		dateText = new JTextField(50);
		dateText.setBounds(120, 280, 320, 25);
		panel.add(dateText);

		imageLabel = new JLabel("Image");
		imageLabel.setBounds(30, 330, 80, 25);
		panel.add(imageLabel);

		imageText = new JTextField(200);
		imageText.setBounds(120, 330, 320, 25);
		panel.add(imageText);

		desLabel = new JLabel("Description");
		desLabel.setBounds(30, 380, 80, 25);
		panel.add(desLabel);

		desText = new JTextArea();
		desText.setWrapStyleWord(true);
		desText.setLineWrap(true);
		
		scrollPane = new JScrollPane(desText);
		scrollPane.setBounds(120, 380, 320, 80);
		panel.add(scrollPane);

		setLocationRelativeTo(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String bookName = nameText.getText();
		String author = authorText.getText();
		String collection = (String) collectionBox.getSelectedItem();
		String page = pageText.getText();
		String publisher = publisherText.getText();
		String date = dateText.getText();
		String imageLink = imageText.getText();
		String description = desText.getText();

		if (bookName.equals("")) {
			System.out.println("Error - Book has no name!");
			JOptionPane.showMessageDialog(this, "Plese enter the book's name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JSONObject book = new JSONObject();
			book.put("title", bookName);
			book.put("author", author);
			book.put("collection", collection);
			book.put("page", page);
			book.put("publisher", publisher);
			book.put("date", date);
			book.put("image", imageLink);
			book.put("description", description);
//			System.out.println(description);
			try {
				int response = Function.createBook(book);
				if (response == 200) {
					int result = JOptionPane.showConfirmDialog(this, "Success!", "Infomation",
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
