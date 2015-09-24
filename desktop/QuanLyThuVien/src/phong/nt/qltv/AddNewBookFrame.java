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
		collectionBox.setModel(new DefaultComboBoxModel(new String[] { "S\u00E1ch Ti\u1EBFng Anh",
				"S\u00E1ch V\u0103n H\u1ECDc - Ti\u1EC3u Thuy\u1EBFt", "S\u00E1ch Kinh T\u1EBF",
				"S\u00E1ch Chuy\u00EAn Ng\u00E0nh",
				"S\u00E1ch K\u1EF9 N\u0103ng S\u1ED1ng - Ngh\u1EC7 Thu\u1EADt S\u1ED1ng",
				"S\u00E1ch Gi\u00E1o Khoa - Tham Kh\u1EA3o",
				"S\u00E1ch H\u1ECDc Ngo\u1EA1i Ng\u1EEF - T\u1EEB \u0110i\u1EC3n",
				"S\u00E1ch Cho Tu\u1ED5i M\u1EDBi L\u1EDBn", "S\u00E1ch Truy\u1EC7n Thi\u1EBFu Nhi",
				"S\u00E1ch Th\u01B0\u1EDDng Th\u1EE9c - \u0110\u1EDDi S\u1ED1ng", "Truy\u1EC7n Tranh - Manga - Comic",
				"S\u00E1ch V\u0103n Ho\u00E1 - Ngh\u1EC7 Thu\u1EADt - Du L\u1ECBch", "T\u1EA1p Ch\u00ED",
				"S\u00E1ch Nu\u00F4i D\u1EA1y Con", "S\u00E1ch C\u0169 Gi\u00E1 T\u1ED1t",
				"Combo - Series S\u00E1ch \u0110\u1EB7c S\u1EAFc" }));
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
//		desText.setBounds(120, 380, 320, 80);
//		panel.add(desText);
		
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
