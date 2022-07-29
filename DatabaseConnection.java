package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DatabaseConnection {
	
	    public static Connection getConnection() {
	    	
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			   Connection con=DriverManager.getConnection("jdbc:mysql:///sanjay","root","root");
			return con;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            return null;
			
	    }
	    
	    public String openAccount(String fullname,int age,double intialdepost) {
	    	
	    	PreparedStatement pst=null;
	    	String fname=fullname;
	    	int agee=age;
	    	double indepo=intialdepost;
	    	 Random rand = new Random();
	    	  int rand_int1 = rand.nextInt(100000000);
	    	String acountString=String.valueOf(rand_int1);
	    	String sqlString="insert into customer(AccNo,FullName,Age,Amount)"+" values(?,?,?,?)";
	    	
	    	Connection con=getConnection();
	    	try {
				pst=con.prepareStatement(sqlString);
				
				pst.setString(1,acountString);
				pst.setString(2, fname);
				pst.setInt(3, agee);
				pst.setDouble(4, indepo);
				pst.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    	
	    	System.out.println("Hi Account is created successfully");
	    	System.out.println();
	    	
	    	return String.valueOf(rand_int1);
	    }
public boolean validaccount(String accountnumber) {
	
	   boolean b=false;
		   try {
			   PreparedStatement pst=null;
			    
			    String findString="select * from customer";
			    
			       Connection  con=getConnection();
			pst = con.prepareStatement(findString);
			
			ResultSet rSet=pst.executeQuery(findString);
		   while(rSet.next()) {
			   
			   String accountnumber1=rSet.getString("AccNo");
			   
			   if(accountnumber1.equals(accountnumber)) {
				   b=true;
			   }
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return b;  
	
		}

public double AvailabelBalance(String account) {
	
	        double balance=0;
	        
	        try {
	        	Connection con=getConnection();
		        String finString="select * from customer";
				PreparedStatement pst=con.prepareStatement(finString);
				ResultSet rSet=pst.executeQuery(finString);
				
				while(rSet.next()) {
					String accString=rSet.getString("AccNo");
					if(accString.equals(account)) {
						balance=rSet.getDouble("Amount");
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return balance;
	
}
public void withdraw_amount(String account,double withdraamount) {
	
	
	          double updateamt=0;
	           try {
				Connection con=getConnection();
				
				String finString="select * from customer";
				PreparedStatement pst=con.prepareStatement(finString);
				ResultSet rSet=pst.executeQuery(finString);
				
				while(rSet.next()) {
					int count=0;
				String accString=rSet.getString("AccNo");
				
				if(account.equals(accString)) {
					double amt=rSet.getDouble("Amount");
					
					 
					if(amt>withdraamount) {
						count+=1;
						/* String updateString="UPDATE customer SET Amount=? where AccNo=?"; */
						/*
						 * pst=con.prepareStatement(updateString); pst.setDouble(1,rem);
						 * pst.setString(2, account);
						 */
						double rem=amt-withdraamount;
						updateamt=rem;
						System.out.println("Please Collect Rs."+withdraamount);
						System.out.println("Available Balance :Rs."+rem);
						System.out.println();
						 
					}
					else {
						
						System.out.println("Insufficent Amount");
						System.out.println();
						
					}
					
				}
				
				if(count!=0) {
					break;
				}
				}
				//to update the remaining balance
				
				String finString1="select * from customer";
				PreparedStatement pst1=con.prepareStatement(finString1);
				ResultSet rSet1=pst1.executeQuery(finString1);
				while(rSet1.next()) {
					
					String acountString=rSet1.getString("AccNo");
					if(acountString.equals(account)) {
						Connection c=getConnection();
					PreparedStatement p1=c.prepareStatement("UPDATE customer SET Amount=? where AccNo=?");
					
					p1.setDouble(1, updateamt);
					p1.setString(2, acountString);
					p1.executeUpdate();
					break;
						}
				}
				
				
				
				
			} catch (Exception e) {
	
				e.printStackTrace();
			}
	
	
	
}
public void deposit(String account,double amount) {

	double available_bal=0;
	try {
		String findString1="select * from customer";
		Connection con=getConnection();
		PreparedStatement pst1=con.prepareStatement(findString1);
		ResultSet rSet1=pst1.executeQuery(findString1);
		while(rSet1.next()) {
			
			String acountString=rSet1.getString("AccNo");
			
			if(acountString.equals(account)) {
				double adamt=rSet1.getDouble("Amount");
				Connection c=getConnection();
			PreparedStatement p1=c.prepareStatement("UPDATE customer SET Amount=? where AccNo=?");
			available_bal=adamt+amount;
			p1.setDouble(1,available_bal);
			p1.setString(2,account);
			p1.executeUpdate();
			System.out.println(amount);
			break;
				}
		}
		System.out.println("Rs."+amount+" deposited successfully");
		System.out.println("Available balance Rs."+available_bal);
		
		
	} catch (Exception e) {
	}
	
}

	
}
	
