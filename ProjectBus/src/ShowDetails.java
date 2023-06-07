import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowDetails extends JFrame {

	private static JPanel contentPane;
	public static JTextField BusNumber;
	private static JTextField status;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowDetails frame = new ShowDetails();
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
	public ShowDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(UpdateBus.class.getResourceAsStream("./BusLogo5.jpg"));
		} catch (IOException e5) {
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
			BusImage = ImageIO.read(ShowDetails.class.getResourceAsStream("./HD_Bus_Logo.png"));
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		Image resizedImage1 = BusImage.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(resizedImage1);
		
		JButton goBack = new JButton("Go Back");
		
		goBack.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		goBack.setBounds(828, 522, 180, 36);
		contentPane.add(goBack);
		
		status = new JTextField();
		status.setFont(new Font("Sitka Subheading", Font.BOLD, 23));
		status.setColumns(10);
		status.setBounds(821, 404, 208, 36);
		contentPane.add(status);
		
		JComboBox seatDropdown = new JComboBox();
		
		seatDropdown.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		seatDropdown.setModel(new DefaultComboBoxModel(new String[] {"--SELECT SEAT NUMBER--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}));
		seatDropdown.setBounds(821, 309, 208, 36);
		contentPane.add(seatDropdown);
		
		BusNumber = new JTextField();
		BusNumber.setFont(new Font("Sitka Subheading", Font.BOLD, 23));
		BusNumber.setBounds(821, 218, 208, 36);
		contentPane.add(BusNumber);
		BusNumber.setColumns(10);
		JButton Book = new JButton("Book");
		
		Book.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		Book.setBounds(616, 522, 180, 36);
		contentPane.add(Book);
		
		JLabel seatStatus = new JLabel("Status : ");
		seatStatus.setForeground(new Color(250, 235, 215));
		seatStatus.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		seatStatus.setBounds(616, 404, 156, 36);
		contentPane.add(seatStatus);
		
		JLabel Seat_No = new JLabel("Seat Number : ");
		Seat_No.setForeground(new Color(250, 235, 215));
		Seat_No.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		Seat_No.setBounds(616, 309, 156, 36);
		contentPane.add(Seat_No);
		JLabel Bus_no = new JLabel("Bus Number : ");
		Bus_no.setForeground(new Color(250, 235, 215));
		Bus_no.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		Bus_no.setBounds(616, 218, 156, 36);
		contentPane.add(Bus_no);
		
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
		
		
		seatDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedSeatNumber = Integer.parseInt(seatDropdown.getSelectedItem().toString());
				String no = BusNumber.getText();
				String busName = "bus"+no;
				String query = "SELECT status from "+busName+" where seats = ?";
				Connection con = Database_Connection.getConnection();
				try {
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, seatDropdown.getSelectedItem().toString());
					ResultSet res = pst.executeQuery();
					while(res.next()) {
						status.setText(res.getString(1));
						status.setEditable(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchBus search = new SearchBus();
				setVisible(false);
				search.setVisible(true);
			}
		});
		
		
		Book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(status.getText().equals("unbooked")) {
						
					BookBus book = new BookBus();
					setVisible(false);
					book.setVisible(true);
					
					book.BusNoVar = BusNumber.getText().toString();
					String seletedSeat = seatDropdown.getSelectedItem().toString();
					book.yourSeatHere.setText(seletedSeat);
					book.yourSeatHere.setEditable(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "The Seat is already Booked !");
				}
				
			}
		});
	}
}
