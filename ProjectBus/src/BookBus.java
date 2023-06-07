import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public class BookBus extends JFrame {

	private JPanel contentPane;
	private JTextField nameee;
	private JTextField aadhar;
	private JTextField phone;
	boolean EnterMainPage = true;
	public String BusNoVar;
	public JTextField yourSeatHere;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBus frame = new BookBus();
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
	public BookBus() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 854);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(BookBus.class.getResourceAsStream("./BusLogo5.jpg"));
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}

		int newWidth = 2520;
		int newHeight = 1280;

		Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(resizedImage);

		int newWidth1 = 250;
		int newHeight1 = 120;

		BufferedImage BusImage = null;
		try {
			BusImage = ImageIO.read(BookBus.class.getResourceAsStream("./HD_Bus_Logo.png"));
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		Image resizedImage1 = BusImage.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(resizedImage1);

		JButton Book = new JButton("Book A Bus");
		
		
		JButton goBack = new JButton("Go Back");
		
		yourSeatHere = new JTextField();
		yourSeatHere.setFont(new Font("Sitka Subheading", Font.PLAIN, 24));
		yourSeatHere.setColumns(10);
		yourSeatHere.setBounds(724, 485, 386, 36);
		contentPane.add(yourSeatHere);
		
		JLabel yourSeat = new JLabel("Your Selected Seat :");
		yourSeat.setForeground(new Color(250, 235, 215));
		yourSeat.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		yourSeat.setBounds(492, 485, 221, 36);
		contentPane.add(yourSeat);
		
		
		
		phone = new JTextField();
		phone.setFont(new Font("Sitka Subheading", Font.PLAIN, 24));
		phone.setColumns(10);
		phone.setBounds(724, 397, 386, 36);
		contentPane.add(phone);
		
		aadhar = new JTextField();
		aadhar.setFont(new Font("Sitka Subheading", Font.PLAIN, 24));
		aadhar.setColumns(10);
		aadhar.setBounds(723, 310, 387, 36);
		contentPane.add(aadhar);
		
		nameee = new JTextField();
		nameee.setFont(new Font("Sitka Subheading", Font.PLAIN, 24));
		nameee.setBounds(724, 226, 386, 36);
		contentPane.add(nameee);
		nameee.setColumns(10);
		goBack.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		goBack.setBounds(880, 611, 169, 45);
		contentPane.add(goBack);
		
		
		Book.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		Book.setBounds(560, 611, 180, 45);
		contentPane.add(Book);

		

		JLabel customerPhone = new JLabel("Phone Number : ");
		customerPhone.setForeground(new Color(250, 235, 215));
		customerPhone.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		customerPhone.setBounds(492, 397, 221, 36);
		contentPane.add(customerPhone);

		JLabel aadharNo = new JLabel("Aadhar Number :");
		aadharNo.setForeground(new Color(250, 235, 215));
		aadharNo.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		aadharNo.setBounds(492, 310, 221, 36);
		contentPane.add(aadharNo);

		

		JLabel customerName = new JLabel("Your Name : ");
		customerName.setForeground(new Color(250, 235, 215));
		customerName.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		customerName.setBounds(492, 226, 221, 36);
		contentPane.add(customerName);
		JLabel Bus_Logo = new JLabel(icon1);
		Bus_Logo.setFont(new Font("Tahoma", Font.BOLD, 17));
		Bus_Logo.setForeground(Color.WHITE);
		Bus_Logo.setBounds(179, 25, 150, 100);
		getContentPane().add(Bus_Logo);
		JLabel background = new JLabel(icon);
		background.setFont(new Font("Tahoma", Font.BOLD, 17));
		background.setForeground(Color.WHITE);
		background.setBounds(10, 10, 1520, 1080);
		getContentPane().add(background);
		
		
		Book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean check = true;
				EnterMainPage = false;
				if (nameee.getText().isBlank() || phone.getText().isBlank() || aadhar.getText().isBlank()) {
					check = false;
				}

				if (check == false) {
					JOptionPane.showMessageDialog(null, "Fill All The Fields !");
					resetFields();
				} else {
					
					Connection con = Database_Connection.getConnection();
					String query = "INSERT INTO customer(name, aadharNo, phoneNo) VALUES (?, ?, ?)";
					try {
						
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, nameee.getText().toString());
						pst.setString(2, aadhar.getText().toString());
						pst.setString(3, phone.getText().toString());
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Bus Booked Sucessfully !");
						String query1= "";
						String busNo = BusNoVar;
						String tableName = "bus"+busNo;
						String seatss = yourSeatHere.getText();
						query1 = "UPDATE "+tableName+" set status = ? where seats = ?";
						pst = con.prepareStatement(query1);
						pst.setString(1,"booked");
						pst.setString(2,seatss);
						pst.executeUpdate();
						
						SearchBus s = new SearchBus();
						setVisible(false);
						s.setVisible(true);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowDetails details = new ShowDetails();
				setVisible(false);
				details.setVisible(true);
			}
		});
		
	}
	public void resetFields() {
		nameee.setText(null);
		phone.setText(null);
		aadhar.setText(null);
	}
}
