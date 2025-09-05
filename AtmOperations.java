package atm;
import java.sql.*;
public class AtmOperations {
	// create account
	public void createAccount(String name,double intialbalance) throws SQLException {
		String sql = "insert into accounts(holder_name,balance)values(?,?)";
		try(Connection con= DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1,name);
			ps.setDouble(2,intialbalance);
			int rows=ps.executeUpdate();
			if(rows>0) {
				ResultSet rs= ps.getGeneratedKeys();
				if(rs.next()) {
					int accountNo=rs.getInt(1);
					System.out.println(" Account created succesfully!");
					System.out.println("your account number is:" + accountNo);
				}
			}
		}catch(SQLException e) {
			System.out.println("Error creating account");
			e.printStackTrace();
			
		}
	}
	



// login
    public boolean login(int accountNo) throws SQLException {
    	try(Connection con=DBConnection.getConnection()){
    		String sql= "select holder_name,balance from accounts where account_no=?";
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setInt(1,accountNo);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    	
    			 String name = rs.getString("holder_name");
    		System.out.println("Login succesfully! welcome" + rs.getString("holder_name"));
    		return true;
    		
    	}
    		else {
    			System.out.println("Account is not found!");
    		}
    		
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    //view balance
    public void viewbalance(int accountNo) throws SQLException {
    	try(Connection con= DBConnection.getConnection()){
    		String sql ="select balance from accounts where account_no=?";
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setInt(1, accountNo);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    			double balance=rs.getDouble("balance");
    			System.out.println("current balance"  + balance);
    			
    		}
    		else {
    			System.out.println("Account is not found");
    		}
    				
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    	//deposit
    	public void deposit(int accountNo, double amount) {
    		try(Connection con=DBConnection.getConnection()){
    			String sql="update accounts set balance=balance +? where account_no=?";
    			PreparedStatement ps=con.prepareStatement(sql);
    			ps.setDouble(1, amount);
    			ps.setInt(2, accountNo);
    			int rows=ps.executeUpdate();
    			if (rows>0){
    				recordTransaction(accountNo, "deposit", amount);
    				System.out.println("Depostied" + amount + " successfully!");
    				
    				
    			}
    			else {
    				System.out.println(" Account is not found");
    			}
    			
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}

//withdraw 
    	public void withdraw(int accountNo,double amount) {
    	String sql=" update accounts set balance=balance-? where account_no=? and balance>=?";
    	try(Connection con=DBConnection.getConnection();
    		PreparedStatement ps=con.prepareStatement(sql)){
    			ps.setDouble(1, amount);
    			ps.setInt(2, accountNo);
    			ps.setDouble(3, amount);
    			int rows=ps.executeUpdate();
    			if(rows>0) {
    				System.out.println(" " + amount + " withdrawn successfully!");
    				recordTransaction(accountNo,"withdrawn", amount);
    			}
    			else {
    				System.out.println(" Insufficient balance!");
    			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    		
    	}
// mini statement
    	public void miniStatement(int accountNo) {
    		String sql= "select type,amount,txn_time from transactions where account_no=? order by txn_time desc limit 5";
    		try(Connection con= DBConnection.getConnection();
    				PreparedStatement ps=con.prepareStatement(sql)){
    				ps.setInt(1,accountNo);
    				ResultSet rs=ps.executeQuery();
    				System.out.println(" Last 5 transactions");
    				while(rs.next()) {
    					System.out.println(rs.getString("type") +" - " + rs.getDouble("amount") + " - " + rs.getTimestamp("txn_time"));
    				}
    		}
    				catch(SQLException e) {
    					e.printStackTrace();
    				}
    	}
    	//Record transactions

		private  void recordTransaction(int accountNo,String type, double amount) throws SQLException {
			String sql=" insert into transactions(account_no,type,amount)values(?,?,?)";
			try(Connection con=DBConnection.getConnection();
					PreparedStatement ps=con.prepareStatement(sql)){
				ps.setInt(1, accountNo);
				ps.setString(2, type);
				ps.setDouble(3, amount);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
    	}
    
    	
    

    

