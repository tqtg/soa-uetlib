package phong.nt.qltv;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class BookTableModel extends AbstractTableModel {
	public final static int TITLE = 0;
	public final static int AUTHOR = 1;
	public final static int CATEGORY = 2;
	public final static int PAGE = 3;
	public final static int PUBLISHER = 4;
	public final static int DATE = 5;
	public final static int MORE_INFO = 6;
	public final static int EDIT = 7;
	public final static int DELETE = 8;

	private List<Book> bookList = new ArrayList<Book>();
	private String[] columnNames = { "Title", "Author", "Category", "Page", "Publisher", "Date", "", "", "" };

	public BookTableModel() {

	}

	public BookTableModel(List<Book> bookList) {
		this.bookList = bookList;
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
		if (column == MORE_INFO) {
			return "More info...";
		}
		if (column == EDIT) {
			return "Edit";
		} else if (column == DELETE) {
			return "Delete";
		} else {
			Object bookAttribute = null;
			Book book = bookList.get(row);
			switch (column) {
//			case ID:
//				bookAttribute = book.getId();
//				break;
			case TITLE:
				bookAttribute = book.getTitle();
				break;
			case AUTHOR:
				bookAttribute = book.getAuthor();
				break;
			case CATEGORY:
				String catCode = book.getCategory();
				String collection;
				if (catCode == null) {
					collection = "";
				} else {
					collection = Helper.collectionOfCode(catCode);
				}
				bookAttribute = collection;
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
//			case IMAGE:
//				bookAttribute = book.getImage();
//				break;
//			case DESCRIPTION:
//				bookAttribute = book.getDescription();
//				break;
			}
			return bookAttribute;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == MORE_INFO || column == EDIT || column == DELETE) {
			return true;
		}
		return false;
	}

	public void deleteRow(int row) {
		bookList.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void editRow(int row, Book newBookData) {
		bookList.remove(row);
		bookList.add(row, newBookData);
		fireTableDataChanged();
	}

	public void refresh(List<Book> bookList2) {
		this.bookList = bookList2;
		fireTableDataChanged();
	}
}
