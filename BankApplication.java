package com.project.application;
import java.util.Scanner;
import com.project.database.DatabaseConnection;

public class BankApplication {

	static DatabaseConnection connection=new DatabaseConnection();
	static Scanner objScanner = new Scanner( System.in );
	public static void method1() {	
		String cname="";
		int age;
		double inideposit;
		System.out.println("Enter your name ");
		cname=objScanner.nextLine();
		System.out.println("Enter your age");
		age=objScanner.nextInt();
		System.out.println("Enter your intial deposit ");
		inideposit=objScanner.nextDouble();
		
		  String nString= connection.openAccount(cname, age,inideposit);
		  
		  System.out.println("your account no is "+nString);
	}
	
	public static void method2() {
		
		System.out.println("Enter your account number");
		
		String accountString=objScanner.nextLine();
		
		if(connection.validaccount(accountString)==true) {
			
			double bal=connection.AvailabelBalance(accountString);
			
			System.out.println("Avaibale Balance :Rs."+bal);
		}
		else {
			System.out.println("Invalid Account Number");
			
		}
		
		
	}
	
	public static void method3() {
	
		System.out.println("Enter your account number");
		String accountString=objScanner.nextLine();
		System.out.println("Enter the amount");
		double amtString=objScanner.nextDouble();
		
		 connection.withdraw_amount(accountString, amtString);
		
		
	}
	
	public static void method4() {
		System.out.println("Enter your account number");
		
		String accountString=objScanner.nextLine();
		System.out.println("Enter the amount for Deposit");
		double amtString=objScanner.nextDouble();
		
		connection.deposit(accountString, amtString);
		
	}
	
	public static void main(String[] args) {
		
	      while(true) {
			System.out.println("Press 1 for opening an account");
			System.out.println("Press 2 for check balance");
			System.out.println("Press 3 for withdraw");
			System.out.println("Press 4 for deposit");
			System.out.println("Press 5 for exit");
			
			System.out.println("Enter your choice");
			Scanner objScanner = new Scanner( System.in );
			int choice=objScanner.nextInt();
			switch (choice) {
			case 1:method1();

				break;

			case 2:method2();

				break;

			case 3:method3();

				break;

			case 4:method4();

				break;
				
			case 5:System.exit(0);		
			
			}
		}
		
	}

}
