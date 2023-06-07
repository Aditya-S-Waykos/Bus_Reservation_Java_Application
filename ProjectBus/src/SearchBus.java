import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;

import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.AncestorEvent;

public class SearchBus extends JFrame {

	private JPanel contentPane;
	private JComboBox Start_location;
	private JComboBox Destination;
	private JDateChooser dateChooser;
	private JComboBox ChooseBus;
	private boolean BusFound;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBus frame = new SearchBus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SearchBus() { // constructor
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
			originalImage = ImageIO.read(SearchBus.class.getResourceAsStream("./BusLogo5.jpg"));
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
			BusImage = ImageIO.read(SearchBus.class.getResourceAsStream("./HD_Bus_Logo.png"));
		} catch (IOException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		Image resizedImage1 = BusImage.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(resizedImage1);

		JButton Search_bus_button = new JButton("Show Details");
		
		JButton HomeScreen = new JButton("Go Back");
		HomeScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome wel = new Welcome();
				wel.setVisible(true);
				setVisible(false);
			}
		});
		HomeScreen.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		HomeScreen.setBounds(828, 611, 169, 45);
		contentPane.add(HomeScreen);
		
		ChooseBus = new JComboBox();		
		ChooseBus.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		ChooseBus.setBounds(781, 479, 238, 36);
		ChooseBus.setPreferredSize(new Dimension(150, 300));
		contentPane.add(ChooseBus);

		JLabel ChooseBusLabel = new JLabel("Choose Bus : ");
		ChooseBusLabel.setForeground(new Color(250, 235, 215));
		ChooseBusLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		ChooseBusLabel.setBounds(492, 479, 221, 36);
		contentPane.add(ChooseBusLabel);
		Search_bus_button.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		Search_bus_button.setBounds(480, 611, 180, 45);
		contentPane.add(Search_bus_button);

		dateChooser = new JDateChooser();

		dateChooser.setBounds(781, 397, 238, 36);
		dateChooser.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		contentPane.add(dateChooser);

		JLabel lblChooseDate = new JLabel("Choose Date:");
		lblChooseDate.setForeground(new Color(250, 235, 215));
		lblChooseDate.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblChooseDate.setBounds(492, 397, 221, 36);
		contentPane.add(lblChooseDate);

		JLabel destinationlabel = new JLabel("Choose destination:");
		destinationlabel.setForeground(new Color(250, 235, 215));
		destinationlabel.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		destinationlabel.setBounds(492, 310, 221, 36);
		contentPane.add(destinationlabel);

		Destination = new JComboBox();
		Destination.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		Destination.setModel(new DefaultComboBoxModel(new String[] { "--Select City--", "Ahmednagar", "Akola",
				"Amravati", "Aurangabad", "Beed", "Bhandara", "Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia",
				"Hingoli", "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai (formerly Bombay)", "Nagpur", "Nanded",
				"Nandurbar", "Nashik", "Osmanabad", "Parbhani", "Pune", "Raigad", "Ratnagiri", "Sangli", "Satara",
				"Sindhudurg", "Solapur", "Thane", "Wardha", "Washim", "Yavatmal" }));
		Destination.setBounds(781, 309, 238, 36);
		contentPane.add(Destination);

		Start_location = new JComboBox();

		Start_location.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		Start_location.setModel(new DefaultComboBoxModel(new String[] { "--Select City--", "Ahmednagar", "Akola",
				"Amravati", "Aurangabad", "Beed", "Bhandara", "Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia",
				"Hingoli", "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai (formerly Bombay)", "Nagpur", "Nanded",
				"Nandurbar", "Nashik", "Osmanabad", "Parbhani", "Pune", "Raigad", "Ratnagiri", "Sangli", "Satara",
				"Sindhudurg", "Solapur", "Thane", "Wardha", "Washim", "Yavatmal" }));
		Start_location.setBounds(781, 225, 238, 36);
		contentPane.add(Start_location);

		JLabel startlabel = new JLabel("Choose Start Location:");
		startlabel.setForeground(new Color(250, 235, 215));
		startlabel.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		startlabel.setBounds(492, 226, 221, 36);
		contentPane.add(startlabel);
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
		

		ChooseBus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet result = null;
				Connection con = Database_Connection.getConnection();
				String query = "SELECT bus_name from Buses where start_location = ? and destination = ? and date1 = ?";
				try {
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, Start_location.getSelectedItem().toString());
					pst.setString(2, Destination.getSelectedItem().toString());
					SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = simpleDate.format(dateChooser.getDate());
					pst.setString(3, formattedDate);
					result = pst.executeQuery();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					ResultSet result2 = result;
					if(!result.next()) {
						JOptionPane.showMessageDialog(null, "No Buses Found !");
						BusFound = false;
						ChooseBus.removeAllItems();
						
					} else {
						BusFound = true;
						ChooseBus.removeAllItems();
						do {
							ChooseBus.addItem(result2.getString("bus_name"));
						} while(result2.next());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Search_bus_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(BusFound == true) {
					// show the detaiils
					ShowDetails details = new ShowDetails();
					setVisible(false);
					details.setVisible(true);
					String query = "SELECT bus_no from Buses where start_location = ? and destination = ? and date1 = ? and bus_name = ?";
					Connection con = Database_Connection.getConnection();
					try {
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, Start_location.getSelectedItem().toString());
						pst.setString(2, Destination.getSelectedItem().toString());
						SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
						String formattedDate = simpleDate.format(dateChooser.getDate());
						pst.setString(3, formattedDate);
						pst.setString(4, ChooseBus.getSelectedItem().toString());
						
						ResultSet res1 = pst.executeQuery();
						if (res1.next()) { // Move the cursor to the first row
					        String no = res1.getString("bus_no");
					        ShowDetails.BusNumber.setText(no);
					        ShowDetails.BusNumber.setEditable(false);
					    }
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No Bus Found !");
				}
			}
		});
}	 
}
