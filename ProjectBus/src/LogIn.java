import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField email_ID;
	private JTextField passwordd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 854);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel lblNewLabel = new JLabel("Log In Here");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 29));
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setBounds(698, 113, 172, 39);
		getContentPane().add(lblNewLabel);

		JLabel lblEnterEmailId = new JLabel("Enter Email ID : ");
		lblEnterEmailId.setForeground(new Color(240, 255, 240));
		lblEnterEmailId.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		lblEnterEmailId.setBounds(585, 200, 172, 39);
		getContentPane().add(lblEnterEmailId);

		email_ID = new JTextField();
		email_ID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		email_ID.setBounds(767, 205, 243, 28);
		getContentPane().add(email_ID);
		email_ID.setColumns(10);

		JLabel lblEnterPassword = new JLabel("Enter Password : ");
		lblEnterPassword.setForeground(new Color(240, 255, 240));
		lblEnterPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		lblEnterPassword.setBounds(585, 280, 172, 39);
		getContentPane().add(lblEnterPassword);

		passwordd = new JTextField();
		passwordd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordd.setColumns(10);
		passwordd.setBounds(767, 285, 243, 28);
		getContentPane().add(passwordd);

		JButton LoginSubmit = new JButton("Submit");
		LoginSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean check = true;
				boolean EnterMainPage = false;
				if (email_ID.getText().isBlank() || passwordd.getText().isBlank()) {
					check = false;
				}

				if (check == false) {
					JOptionPane.showMessageDialog(null, "Fill All The Fields !");
					resetFields();
				} else { // means the fields are filled completely

					Connection con = Database_Connection.getConnection();

					String email = email_ID.getText();
					String password = passwordd.getText();

					String query = "select email_ID,password from user where email_ID =	?";

					try {
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, email);

						ResultSet resultSet = pst.executeQuery();

						String database_email = null;
						String database_password = null;

						if (resultSet.next()) {
							database_email = resultSet.getString("email_ID"); // resultSet.getString() takes argument as
																				// String column name
							database_password = resultSet.getString("password");
						} else {
							JOptionPane.showMessageDialog(null, "Email Not Registered !");
						}

						if (database_email.equals(email) && database_password.equals(password)) {
							EnterMainPage = true;
							JOptionPane.showMessageDialog(null, "Login Successfull !");
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Email Or Password !");
						}

					} catch (Exception e4) {
						e4.printStackTrace();
					}

				}

				if (EnterMainPage) {
					// Load main page.
					SearchBus searchBus = new SearchBus();
					setVisible(false);
					searchBus.setVisible(true);
				}

			}
		});
		LoginSubmit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		LoginSubmit.setBounds(626, 422, 105, 39);
		getContentPane().add(LoginSubmit);

		JButton RegisterSubmit = new JButton("Register");
		RegisterSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				setVisible(false);
			}
		});

		RegisterSubmit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		RegisterSubmit.setBounds(835, 422, 105, 39);
		contentPane.add(RegisterSubmit);

	}

	public void resetFields() {
		email_ID.setText(null);
		passwordd.setText(null);
	}
}
