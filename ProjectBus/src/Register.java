import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	public Connection conn;
	private JTextField Register_TextField1;
	private JTextField Register_TextField2;
	private JTextField Register_TextField3;
	private JTextField Register_TextField4;
	private boolean databaseEntry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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

	public Register() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 854);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel lblNewLabel = new JLabel("Register Here");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.ITALIC, 29));
		lblNewLabel.setForeground(new Color(230, 230, 250));
		lblNewLabel.setBounds(663, 106, 179, 29);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Your Name : ");
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(451, 212, 168, 29);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Email ID : ");
		lblNewLabel_1_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(451, 262, 152, 29);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Enter Password : ");
		lblNewLabel_1_1_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(451, 309, 152, 29);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Confirm Password : ");
		lblNewLabel_1_1_2.setForeground(new Color(240, 255, 240));
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(451, 355, 179, 29);
		getContentPane().add(lblNewLabel_1_1_2);

		Register_TextField1 = new JTextField();
		Register_TextField1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Register_TextField1.setBounds(641, 212, 298, 22);
		getContentPane().add(Register_TextField1);
		Register_TextField1.setColumns(10);

		Register_TextField2 = new JTextField();
		Register_TextField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Register_TextField2.setColumns(10);
		Register_TextField2.setBounds(641, 264, 298, 22);
		getContentPane().add(Register_TextField2);

		Register_TextField3 = new JTextField();
		Register_TextField3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Register_TextField3.setColumns(10);
		Register_TextField3.setBounds(641, 312, 298, 22);
		getContentPane().add(Register_TextField3);

		Register_TextField4 = new JTextField();
		Register_TextField4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Register_TextField4.setColumns(10);
		Register_TextField4.setBounds(641, 355, 298, 22);
		getContentPane().add(Register_TextField4);

		JButton RegSubmit = new JButton("Register");
		RegSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regx = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
				boolean check = true;
				if (Register_TextField1.getText().isBlank() || Register_TextField2.getText().isBlank()
						|| Register_TextField3.getText().isBlank() || Register_TextField4.getText().isBlank()) {
					check = false;
				}

				if (check == false) {
					// pop up for mendatory fields
					JOptionPane.showMessageDialog(null, "Fill All The Fields !");
					resetFields();
				} else {  //all fields are filled
					databaseEntry = true;
					// code for checking up the email ID
					if (!Register_TextField2.getText().matches(regx)) {
						JOptionPane.showMessageDialog(null, "Enter Valid Email Address !");
						resetFields();
						databaseEntry = false;
					}

					// code for checking up the passwords same or not
					if (!Register_TextField3.getText().equals(Register_TextField4.getText())) {
						JOptionPane.showMessageDialog(null, "Password Not Matched !");
						// dispose();
						resetFields();
						// setVisible(true);
						databaseEntry = false;
					}
					
					if (databaseEntry == true) {
						try {
							Class.forName("com.mysql.jdbc.Driver");   //load driver class
							
							String url = "jdbc:mysql://localhost:3306/bus_reservation";
							String user = "root";
							String password = "akadbakad";
							
							conn = DriverManager.getConnection(url, user, password);  //connection stored in conn
						
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}

						try {
							String query = "INSERT INTO user (name, email_ID, password) VALUES (?, ?, ?)";
							PreparedStatement preparedStatement = conn.prepareStatement(query);
							preparedStatement.setString(1, Register_TextField1.getText());
							preparedStatement.setString(2, Register_TextField2.getText());
							preparedStatement.setString(3, Register_TextField3.getText());
							preparedStatement.executeUpdate();
							JOptionPane.showMessageDialog(null, "Registered Successfully !");
							SearchBus s = new SearchBus();
							setVisible(false);
							s.setVisible(true);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
				}

			}
		});

		RegSubmit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		RegSubmit.setBounds(527, 459, 98, 41);
		getContentPane().add(RegSubmit);

		JButton Register_Back = new JButton("Go Back");
		Register_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Welcome welcome = new Welcome();
				welcome.setVisible(true);
				setVisible(false);
			}
		});

		Register_Back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		Register_Back.setBounds(792, 459, 98, 41);
		contentPane.add(Register_Back);

	}

	public void resetFields() {
		Register_TextField1.setText(null);
		Register_TextField2.setText(null);
		Register_TextField3.setText(null);
		Register_TextField4.setText(null);
	}

}
