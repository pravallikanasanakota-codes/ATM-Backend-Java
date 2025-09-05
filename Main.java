package atm;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) throws SQLException{
		System.out.println("Database connected successfully");
		AtmOperations atm=new AtmOperations();	
		System.out.println("WELCOME TO THE ATM MACHNE");
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1.Create Account");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.println("choose the option:");
			int choice =sc.nextInt();
			if (choice==1) {
				System.out.println("Enter your name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println(" Initial balance");
				double balance=sc.nextDouble();
				atm.createAccount(name,balance);
			}
			else if(choice==2) {
				System.out.println("Enter the account number:");
				int aacno =sc.nextInt();
				if(atm.login(aacno)) {
					System.out.println("Login successfully!");
					boolean logout=false;
		
	       while(!logout) {
			System.out.println("----ATM MENU-----");
		    System.out.println("1.view balance");
	        System.out.println("2.withdrawAmount");
		     System.out.println("3.deposit");
		     System.out.println("4.History of transcation ");
		     System.out.println("5.logout");
		    System.out.println("Choose the option from to 1-6:");
			
			int option=sc.nextInt();
			switch(option) {
			case 1:
				atm.viewbalance(aacno);
				break;
			
			case 2:
			System.out.println("Enter the amount for  withdraw:");
			atm.withdraw(aacno,sc.nextDouble());
			break;
		case 3:
			System.out.println("Enter the amount to deposit:");
				atm.deposit(aacno,sc.nextDouble());
			break;
			case 4: 
			atm.miniStatement(aacno);
			
			break;
			case 5:
				logout=true;
				System.out.println(" Logged out successfully!");
				break;
				default:
					System.out.println(" Invalid choice");
			}
	       }
				}else {
					System.out.println("Invalid account number");
				}
			}
			else if(choice==3) {
				System.out.println("Thank you for using the Pravallika Atm system!!");
			break;
			}
			else {
				System.out.println("Invalid option");
			}
		}
		sc.close();
	}
}

	       
			
				 
			
			
		
		
		
		
	
