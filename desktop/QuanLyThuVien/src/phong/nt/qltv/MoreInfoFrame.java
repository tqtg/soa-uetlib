package phong.nt.qltv;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class MoreInfoFrame extends JFrame {

	private JPanel contentPane;
	private JSONObject book;

	public MoreInfoFrame(JSONObject _book) throws MalformedURLException, IOException {
		this.book = _book;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		String imageLink = (String) book.get("image");
		BufferedImage img = ImageIO.read(new URL(imageLink));
		img.getScaledInstance(200, 280, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(img);
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBackground(Color.WHITE);
		imageLabel.setForeground(Color.BLACK);
		imageLabel.setBounds(70, 55, 200, 280);
		contentPane.add(imageLabel);

		JTextArea titleLabel = new JTextArea("title");
		titleLabel.setColumns(10);
		titleLabel.setLineWrap(true);
		titleLabel.setWrapStyleWord(true);
		titleLabel.setBackground(SystemColor.menu);
		titleLabel.setEditable(false);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		titleLabel.setBounds(377, 30, 325, 57);
		contentPane.add(titleLabel);

		JTextArea authorLabel = new JTextArea("author");
		authorLabel.setEditable(false);
		authorLabel.setBackground(SystemColor.menu);
		authorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		authorLabel.setBounds(477, 100, 225, 25);
		contentPane.add(authorLabel);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(377, 100, 80, 25);
		contentPane.add(lblAuthor);

		JTextArea catLabel = new JTextArea("unknown");
		catLabel.setEditable(false);
		catLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		catLabel.setBackground(SystemColor.menu);
		catLabel.setBounds(477, 150, 293, 25);
		contentPane.add(catLabel);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(377, 150, 80, 25);
		contentPane.add(lblCategory);

		JTextArea pageLabel = new JTextArea("unknown");
		pageLabel.setEditable(false);
		pageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pageLabel.setBackground(SystemColor.menu);
		pageLabel.setBounds(477, 200, 225, 25);
		contentPane.add(pageLabel);

		JLabel lblPage = new JLabel("Page");
		lblPage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPage.setBounds(377, 200, 80, 25);
		contentPane.add(lblPage);

		JTextArea pubLabel = new JTextArea("unknown");
		pubLabel.setEditable(false);
		pubLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pubLabel.setBackground(SystemColor.menu);
		pubLabel.setBounds(477, 250, 225, 25);
		contentPane.add(pubLabel);

		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPublisher.setBounds(377, 250, 80, 25);
		contentPane.add(lblPublisher);

		JTextArea dateLabel = new JTextArea("unknown");
		dateLabel.setEditable(false);
		dateLabel.setBackground(SystemColor.menu);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateLabel.setBounds(477, 300, 225, 25);
		contentPane.add(dateLabel);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(377, 300, 80, 25);
		contentPane.add(lblDate);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(12, 380, 80, 25);
		contentPane.add(lblDescription);

		JTextArea desText = new JTextArea();
		desText.setFont(new Font("Monospaced", Font.PLAIN, 20));
		desText.setEditable(false);
		desText.setWrapStyleWord(true);
		desText.setLineWrap(true);

		JScrollPane sp = new JScrollPane(desText);
		sp.setBounds(12, 418, 758, 322);
		contentPane.add(sp);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(377, 342, 80, 25);
		contentPane.add(lblId);

		JTextArea idLabel = new JTextArea("id");
		idLabel.setEditable(false);
		idLabel.setBackground(SystemColor.menu);
		idLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
		idLabel.setBounds(477, 342, 225, 25);
		contentPane.add(idLabel);

		String id = (String) book.get("_id");
		String title = (String) book.get("title");
		String author = (String) book.get("author");
		String category_code = (String) book.get("category");
		String category = Helper.collectionOfCode(category_code);
		Long pageLong = (Long) book.get("page");
		String page = "";
		if (pageLong != null) {
			page = pageLong.toString();
		}
		String publisher = (String) book.get("publisher");
		String date = (String) book.get("date");
		String description = (String) book.get("description");

		idLabel.setText(id);
		titleLabel.setText(title);
		if (author != null && author.length() > 0)
			authorLabel.setText(author);
		if (category != null && category.length() > 0)
			catLabel.setText(category);
		if (page.length() > 0)
			pageLabel.setText(page);
		if (publisher != null && publisher.length() > 0)
			pubLabel.setText(publisher);
		if (date != null && date.length() > 0)
			dateLabel.setText(date);
		if (description != null && description.length() > 0)
			desText.setText(description);
	}
}
