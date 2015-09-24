package phong.nt.qltv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ManagerFrame extends JFrame {
	JPanel panel;
	JButton viewButton;
	JButton addButton;
	JButton removeButton;
	JButton editButton;
	
	ManagerFrame(){
		setTitle("Menu");
		setSize(300, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		viewButton = new JButton("View");
		viewButton.setBounds(110, 50, 80, 25);
		panel.add(viewButton);
		
		addButton = new JButton("Add");
		addButton.setBounds(110, 125, 80, 25);
		panel.add(addButton);
		
		removeButton = new JButton("Remove");
		removeButton.setBounds(110, 200, 80, 25);
		panel.add(removeButton);
		
		editButton = new JButton("Edit");
		editButton.setBounds(110, 275, 80, 25);
		panel.add(editButton);
		
		setLocationRelativeTo(null);
		
		viewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewBooksFrame frame;
				try {
					frame = new ViewBooksFrame();
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNewBookFrame frame = new AddNewBookFrame();
				frame.setVisible(true);
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
