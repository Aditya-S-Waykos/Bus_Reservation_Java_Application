import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection {

	static Connection con;

	public static void main(String args[]) throws SQLException {
		
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_reservation", "root", "akadbakad");
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return con;
	}
}
