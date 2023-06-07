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
import java.text.SimpleDateFormat;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateBus extends JFrame {

	private JPanel contentPane;
	private JComboBox Start_location;
	private JComboBox Destination;
	private JDateChooser dateChooser;
	private JComboBox ChooseBus;
	public static String s1;
	public static String s2;
	public static String s3;
	public static String s4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBus frame = new UpdateBus();
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
	public UpdateBus() {
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
		
		JButton GoBack = new JButton("Go Back");
		
		GoBack.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		GoBack.setBounds(1234, 35, 180, 36);
		contentPane.add(GoBack);
		
		ChooseBus = new JComboBox();		
		ChooseBus.setModel(new DefaultComboBoxModel(new String[] {"--Select Bus Name--", "Hamsafar", "Sharma", "Khurana", "Saini", "Tirupati", "Balaji", "Kesari Tours", "Veena World", "Vishvakarma", "Ganraj Travels", "Gov Bus", "Laal Pari", "Shivshahi", "Shivneri", "Akash", "Akanksha Travels", "Pooja Travels", "Vaishnavi Travel"}));
		ChooseBus.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		ChooseBus.setBounds(781, 479, 238, 36);
		ChooseBus.setPreferredSize(new Dimension(150, 300));
		contentPane.add(ChooseBus);
		JButton updateBus = new JButton("Update Bus");
		
		updateBus.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		updateBus.setBounds(653, 610, 180, 45);
		contentPane.add(updateBus);
		
		JLabel ChooseBusLabel = new JLabel("Choose Bus : ");
		ChooseBusLabel.setForeground(new Color(250, 235, 215));
		ChooseBusLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		ChooseBusLabel.setBounds(492, 479, 221, 36);
		contentPane.add(ChooseBusLabel);
		

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
		
		GoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrator admin = new Administrator();
				setVisible(false);
				admin.setVisible(true);
			}
		});
		
		
		updateBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(Start_location.getSelectedItem().toString().equals("--Select City--") || Destination.getSelectedItem().toString().equals("--Select City--") || dateChooser.getDate() == null|| ChooseBus.getSelectedItem().toString().equals("--Select Bus Name--")) {
					JOptionPane.showMessageDialog(null, "Select All The Fields !");
				} 
				else {
					Connection con = Database_Connection.getConnection();
					String query = "update Buses set start_location = ? , destination = ? , date1 = ? , bus_name = ? where start_location = ? and destination = ? and date1 = ? and bus_name = ? LIMIT 1";
					
					try {
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, Start_location.getSelectedItem().toString());
					pst.setString(2, Destination.getSelectedItem().toString());
					SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = simpleDate.format(dateChooser.getDate());
					pst.setString(3, formattedDate);
					pst.setString(4, ChooseBus.getSelectedItem().toString());
					pst.setString(5, s1);
					pst.setString(6, s2);
					pst.setString(7, s3);
					pst.setString(8, s4);
					int i = pst.executeUpdate();
					if(i!=0) {
						JOptionPane.showMessageDialog(null, "Bus Updated Successfully !");
					} else {
						JOptionPane.showMessageDialog(null, "Bus Update Failed !");
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
			}
			}
		});
		
		
	}

	
	//class entity and not the object entity means doesn't depends on object for class.
	public static void previousData(String strLoc, String des, String da, String name) {
		s1 = strLoc;
		s2 = des;
		s3 = da;
		s4 = name;
	}
}
