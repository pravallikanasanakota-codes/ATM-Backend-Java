package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	
	
	
		private static final String url= "jdbc:mysql://localhost:3306/atmdb"; 
		private static final String user="root";
		private static final String password="pravallika@123";
		
		public static  Connection  getConnection(){
			Connection con=null;
			try {
				con= DriverManager.getConnection(url, user, password);
				
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Database connection failed");
		}
			return con;
		
	}

		
}
