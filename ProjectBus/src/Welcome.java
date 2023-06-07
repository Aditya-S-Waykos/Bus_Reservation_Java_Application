import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Welcome extends JFrame {

	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1489, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(Welcome.class.getResourceAsStream("./background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int newWidth = 2520;
        int newHeight = 1280;
        
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);
        JButton LogIn = new JButton("Log In");
        LogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LogIn login = new LogIn();
        		login.setVisible(true);
        		setVisible(false);
        	}
        });
        
        JButton AdminLogin = new JButton("Admin Login");
        AdminLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminLogIn admin = new AdminLogIn();
        		admin.setVisible(true);
        		setVisible(false);
        	}
        });
        
        AdminLogin.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
        AdminLogin.setBounds(898, 485, 157, 43);
        contentPane.add(AdminLogin);
        LogIn.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
        LogIn.setBounds(716, 485, 113, 43);
        getContentPane().add(LogIn);
        
        JButton RegisterButton = new JButton("Register");
        RegisterButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Register register = new Register();
        		register.setVisible(true);
        		setVisible(false);
        	}
        });
        RegisterButton.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
        RegisterButton.setBounds(520, 485, 113, 43);
        getContentPane().add(RegisterButton);
        
        JLabel background = new JLabel(icon);
        background.setFont(new Font("Tahoma", Font.BOLD, 17));
        background.setForeground(Color.WHITE);
		background.setBounds(10, 0, 1520, 1080);
		getContentPane().add(background);
	}
}
