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

public class AdminLogIn extends JFrame {

	private JPanel contentPane;
	private JTextField email_ID;
	private JTextField pass;
	private ResultSet resultSet;
	private ResultSet res2;
	private String database_email;
	private String database_password;
	private boolean EnterMainPage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogIn frame = new AdminLogIn();
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
	public AdminLogIn() {
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

		pass = new JTextField();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pass.setColumns(10);
		pass.setBounds(767, 285, 243, 28);
		getContentPane().add(pass);

		JButton LoginSubmit = new JButton("Submit");

		LoginSubmit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		LoginSubmit.setBounds(617, 423, 116, 39);
		getContentPane().add(LoginSubmit);

		JButton Go_Back = new JButton("Go Back");
		Go_Back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		Go_Back.setBounds(882, 423, 128, 39);
		contentPane.add(Go_Back);

		Go_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome wel = new Welcome();
				wel.setVisible(true);
				setVisible(false);
			}
		});

		LoginSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean check = true;
				EnterMainPage = false;
				if (email_ID.getText().isBlank() || pass.getText().isBlank()) {
					check = false;
				}

				if (check == false) {
					JOptionPane.showMessageDialog(null, "Fill All The Fields !");
					resetFields();
				} else { // means the fields are filled completely

					Connection con = Database_Connection.getConnection();

					String email = email_ID.getText();
					String password = pass.getText();

					String query = "SELECT email_ID, passwordd from admin where email_ID = ?";

					try {
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, email);
						resultSet = pst.executeQuery();
						res2 = resultSet;
					} catch (Exception e4) {
						e4.printStackTrace();
					}
					try {
						database_email = null;
						database_password = null;
						if (resultSet.next()) {
							database_email = res2.getString("email_ID"); // resultSet.getString() takes argument as
																			// String column name
							database_password = res2.getString("passwordd");
						} else {
							JOptionPane.showMessageDialog(null, "Email Not Registered !");
						}
					} catch (Exception er) {
						er.printStackTrace();
					}
					if (database_email.equals(email) && database_password.equals(password)) {
						EnterMainPage = true;
						JOptionPane.showMessageDialog(null, "Login Successfull !");
						AdministratorPage();
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Email Or Password !");
					}
				}

			}
		});

	}
	
	public void AdministratorPage() {
		if(EnterMainPage) {
			Administrator adminis = new Administrator();
			adminis.setVisible(true);
			setVisible(false);
		}
	}
	
	public void resetFields() {
		email_ID.setText(null);
		pass.setText(null);
	}

}
