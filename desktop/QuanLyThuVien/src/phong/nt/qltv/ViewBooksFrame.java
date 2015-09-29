package phong.nt.qltv;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ViewBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JMenuBar menuBar;
	private JSONArray data;
	private List<Book> bookList;
	private BookTableModel model;

	public ViewBooksFrame() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Library Manager");
		setSize(800, 800);
		getContentPane().setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu optionMenu = new JMenu("Option");
		optionMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(optionMenu);

		JMenuItem refreshMenuItem = new JMenuItem("Refresh", KeyEvent.VK_R);
		optionMenu.add(refreshMenuItem);

		JMenuItem addBookMenuItem = new JMenuItem("Add book", KeyEvent.VK_A);
		addBookMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddNewBookFrame frame = new AddNewBookFrame();
				frame.setVisible(true);
			}
		});
		optionMenu.add(addBookMenuItem);

		JMenuItem logOutMenuItem = new JMenuItem("Log out", KeyEvent.VK_L);
		logOutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInFrame frame = new LogInFrame();
				frame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		optionMenu.add(logOutMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		data = Function.getAllBooks();
		bookList = new ArrayList<Book>();
		for (Object obj : data) {
			JSONObject object = (JSONObject) obj;
			bookList.add(new Book(object));
		}

		model = new BookTableModel(bookList);
		table = new JTable(model);

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String id = (String) ((BookTableModel) table.getModel()).getValueAt(modelRow, BookTableModel.ID);
				String title = (String) ((BookTableModel) table.getModel()).getValueAt(modelRow, BookTableModel.TITLE);
				int deleteResponse = JOptionPane.showConfirmDialog(null,
						"Do you want to delete this book: " + title + "?", "Confirm", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (deleteResponse == JOptionPane.NO_OPTION) {
					System.out.println("Book is not deleted!");
				} else if (deleteResponse == JOptionPane.YES_OPTION) {
					try {
						Function.delete(id);
						((BookTableModel) table.getModel()).deleteRow(modelRow);
						data.remove(modelRow);
						System.out.println("Book deleted! " + id + " " + title);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (deleteResponse == JOptionPane.CLOSED_OPTION) {

				}
			}
		};

		ButtonColumn deleteButtonColumn = new ButtonColumn(table, delete, BookTableModel.DELETE);
		deleteButtonColumn.setMnemonic(KeyEvent.VK_D);

		Action edit = new AbstractAction() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JSONObject obj = (JSONObject) data.get(modelRow);

				EditBookFrame frame = new EditBookFrame(obj);
				frame.setVisible(true);

				Action editOnFrame = new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String bookName = frame.panel.nameText.getText();
						String author = frame.panel.authorText.getText();
						int indexCategory = frame.panel.categoryBox.getSelectedIndex();
						if (indexCategory == -1)
							indexCategory = 0;
						String category = Helper.CATEGORY_CODE[indexCategory];
						String page = frame.panel.pageText.getText();
						String publisher = frame.panel.publisherText.getText();
						String date = frame.panel.dateText.getText();
						String imageLink = frame.panel.imageText.getText();
						String description = frame.panel.desText.getText();

						if (bookName.equals("")) {
							System.out.println("Error - Book has no name!");
							JOptionPane.showMessageDialog(frame, "Plese enter the book's name!", "Error",
									JOptionPane.ERROR_MESSAGE);
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
								int response = Function.editBook(frame.id, newBook);
								if (response == 200) {
									int result = JOptionPane.showConfirmDialog(frame, "Success!", "Infomation",
											JOptionPane.CLOSED_OPTION);
									if (result == 0) {
										JSONObject newEditedBook = new JSONObject();
										newEditedBook.put("_id", frame.id);
										newEditedBook.put("title", bookName);
										newEditedBook.put("author", author);
										newEditedBook.put("category", category);
										newEditedBook.put("publisher", publisher);
										newEditedBook.put("date", date);
										newEditedBook.put("image", imageLink);
										newEditedBook.put("description", description);
										newEditedBook.put("page", Long.parseLong(page));
										((BookTableModel) table.getModel()).editRow(modelRow, new Book(newEditedBook));
										data.remove(modelRow);
										data.add(modelRow, newEditedBook);
										System.out.println("Book edited! " + (String) newEditedBook.get("_id"));
										frame.setVisible(false);
										frame.dispose();
									}
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(frame, e.getMessage(), "Error",
										JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							}

						}

					}

				};
				frame.panel.addActionListenerToButton(editOnFrame);
			}
		};

		ButtonColumn editButtonColumn = new ButtonColumn(table, edit, BookTableModel.EDIT);
		editButtonColumn.setMnemonic(KeyEvent.VK_E);

		Action info = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JSONObject obj = (JSONObject) data.get(modelRow);
				
				MoreInfoFrame frame = new MoreInfoFrame(obj);
				frame.setVisible(true);
			}
		};

		ButtonColumn infoButtonColumn = new ButtonColumn(table, info, BookTableModel.MORE_INFO);
		infoButtonColumn.setMnemonic(KeyEvent.VK_I);

		refreshMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					data = Function.getAllBooks();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bookList = new ArrayList<Book>();
				for (Object obj : data) {
					JSONObject object = (JSONObject) obj;
					bookList.add(new Book(object));
				}
				((BookTableModel) table.getModel()).refresh(bookList);
			}
		});

		JScrollPane jsp = new JScrollPane(table);
		contentPane.add(jsp);
	}
}
