package phong.nt.qltv;

import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookPanel extends JPanel {

	public JLabel nameLabel;
	public JLabel authorLabel;
	public JLabel pageLabel;
	public JLabel publisherLabel;
	public JTextField nameText;
	public JTextField authorText;
	public JTextField pageText;
	public JTextField publisherText;
	public JButton button;
	public JTextField dateText;
	public JTextField imageText;
	public JLabel categoryLabel;
	@SuppressWarnings("rawtypes")
	public JComboBox categoryBox;
	public JLabel dateLabel;
	public JLabel imageLabel;
	public JLabel desLabel;
	public JTextArea desText;
	public JScrollPane scrollPane;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BookPanel(String buttonName) {
		setLayout(null);
		nameLabel = new JLabel("Title");
		nameLabel.setBounds(30, 30, 80, 25);
		add(nameLabel);

		nameText = new JTextField(100);
		nameText.setBounds(120, 30, 320, 25);
		add(nameText);

		authorLabel = new JLabel("Author");
		authorLabel.setBounds(30, 80, 80, 25);
		add(authorLabel);

		authorText = new JTextField(50);
		authorText.setBounds(120, 80, 320, 25);
		add(authorText);

		pageLabel = new JLabel("Page");
		pageLabel.setBounds(30, 180, 80, 25);
		add(pageLabel);

		pageText = new JTextField(50);
		pageText.setBounds(120, 180, 320, 25);
		add(pageText);

		publisherLabel = new JLabel("Publisher");
		publisherLabel.setBounds(30, 230, 80, 25);
		add(publisherLabel);

		publisherText = new JTextField(50);
		publisherText.setBounds(120, 230, 320, 25);
		add(publisherText);

		button = new JButton(buttonName);
		button.setBounds(210, 485, 80, 25);
//		button.addActionListener(action);
		add(button);

		categoryLabel = new JLabel("Category");
		categoryLabel.setBounds(30, 130, 80, 25);
		add(categoryLabel);

		categoryBox = new JComboBox();
		categoryBox.setModel(new DefaultComboBoxModel(Helper.COLLECTION));
		categoryBox.setMaximumRowCount(16);
		categoryBox.setBounds(120, 130, 320, 25);
		add(categoryBox);

		dateLabel = new JLabel("Date");
		dateLabel.setBounds(30, 280, 80, 25);
		add(dateLabel);

		dateText = new JTextField(50);
		dateText.setBounds(120, 280, 320, 25);
		add(dateText);

		imageLabel = new JLabel("Image");
		imageLabel.setBounds(30, 330, 80, 25);
		add(imageLabel);

		imageText = new JTextField(200);
		imageText.setBounds(120, 330, 320, 25);
		add(imageText);

		desLabel = new JLabel("Description");
		desLabel.setBounds(30, 380, 80, 25);
		add(desLabel);

		desText = new JTextArea();
		desText.setWrapStyleWord(true);
		desText.setLineWrap(true);
		
		scrollPane = new JScrollPane(desText);
		scrollPane.setBounds(120, 380, 320, 80);
		add(scrollPane);

	}
	
	public BookPanel(String buttonName, ActionListener action){
		this(buttonName);
		this.addActionListenerToButton(action);
	}
	
	public void addActionListenerToButton(ActionListener action){
		this.button.addActionListener(action);
	}

}
