package phong.nt.qltv;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ViewBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public ViewBooksFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JSONArray data = Function.getAllBooks();
		List<Book> bookList = new ArrayList<Book>();
		for (Object obj : data) {
			JSONObject object = (JSONObject) obj;
			bookList.add(new Book(object));
		}
		
		BookTableModel model = new BookTableModel(bookList);
		table = new JTable(model);

		JScrollPane jsp = new JScrollPane(table);
        contentPane.add(jsp, BorderLayout.CENTER);
	}

}

class Book {
	private String id;
	private String title;
	private String author;
	private String collection;
	private Long page;
	private String publisher;
	private String date;
	private String image;
	private String description;

	Book(JSONObject obj) {
		this.id = (String) obj.get("_id");
		this.title = (String) obj.get("title");
		this.author = (String) obj.get("author");
		this.collection = (String) obj.get("collection");
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

	public String getCollection() {
		return collection;
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

@SuppressWarnings("serial")
class BookTableModel extends AbstractTableModel {
	public final static int ID = 0;
	public final static int TITLE = 1;
	public final static int AUTHOR = 2;
	public final static int COLLECTION = 3;
	public final static int PAGE = 4;
	public final static int PUBLISHER = 5;
	public final static int DATE = 6;
	public final static int IMAGE = 7;
	public final static int DESCRIPTION = 8;
	public final static int EDIT = 9;
	public final static int DELETE = 10;

	private List<Book> bookList = new ArrayList<Book>();
	private String[] columnNames = { "ID", "Title", "Author", "Collection", "Page", "Publisher", "Date", "Image",
			"Description", "Edit", "Delete" };

	public BookTableModel() {

	}

	public BookTableModel(List<Book> bookList) {
		this.bookList = bookList;
//		for (int i = 0; i< this.bookList.size(); i++){
//			System.out.println(this.bookList.get(i).getId());
//		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return bookList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (column == EDIT) {
			return (Object) new JButton("Edit");
		} else if (column == DELETE) {
			return (Object) new JButton("Delete");
		} else {
			Object bookAttribute = null;
			Book book = bookList.get(row);
			switch (column) {
			case ID:
				bookAttribute = book.getId();
				break;
			case TITLE:
				bookAttribute = book.getTitle();
				break;
			case AUTHOR:
				bookAttribute = book.getAuthor();
				break;
			case COLLECTION:
				bookAttribute = book.getCollection();
				break;
			case PAGE:
				bookAttribute = book.getPage();
				break;
			case PUBLISHER:
				bookAttribute = book.getPublisher();
				break;
			case DATE:
				bookAttribute = book.getDate();
				break;
			case IMAGE:
				bookAttribute = book.getImage();
				break;
			case DESCRIPTION:
				bookAttribute = book.getDescription();
				break;
			}
			return bookAttribute;
		}
	}

}
