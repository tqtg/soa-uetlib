package phong.nt.qltv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogInFrame extends JFrame implements ActionListener {
	JPanel panel;
	JLabel userLabel;
	JTextField userText;
	JLabel passwordLabel;
	JPasswordField passwordText;
	JButton loginButton;

	public LogInFrame() {
		setTitle("Sign in");
		setSize(320, 160);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		add(panel);
		panel.setLayout(null);

		userLabel = new JLabel("User");
		userLabel.setBounds(35, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(125, 10, 160, 25);
		panel.add(userText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(35, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(125, 40, 160, 25);
		panel.add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(120, 80, 80, 25);
		loginButton.addActionListener(this);
		panel.add(loginButton);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = userText.getText();
		char[] password = passwordText.getPassword();
		String pass = new String(password);

		if (username.equals("") || password.length == 0) {
			System.out.println("Error - Invalid username and password!");
			JOptionPane.showMessageDialog(this, "Plese enter valid username and password!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			boolean response;
			try {
				response = Function.login(username, pass);
				if (!response) {
					System.out.println("Error - Wrong username or password!");
					JOptionPane.showMessageDialog(this, "Wrong username or password!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("Login succesfully!");
					setVisible(false);
					dispose();
					ViewBooksFrame frame;
					try {
						frame = new ViewBooksFrame();
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, e2.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
