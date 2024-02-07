package Bankingproj;




	import java.util.Scanner;
	import java.sql.

	public class EmployeeLogin {

		public static void addCustomerData() {
			Scanner sc= new Scanner(System.in);
			boolean login=false;
			int attempts=0;
			System.out.println("<---Employee Login--->");
			System.out.println("Welcome to the new bank");
			while(!login && attempts<3) 
			{
				System.out.println("Enter the employee ID:");
				int id=sc.nextInt();
				System.out.println("Enter the password :");
				String pwd=sc.next();
				
				if(loginValidation(id,pwd))
				{
					System.out.println("Login Successful");
					login=true;
				}
				else
				{
					attempts++;
					System.out.println("Login Falied. please retry.");
				}					
			}
			if(attempts>=3)
			{
				System.out.println("Max login attempts reached. Exiting.");
				return;
			}
			System.out.println("select an option:");
			System.out.println("1.Add  customer data");
			System.out.println("2.Update customer data");
			System.out.println("3.Delete customer data");
			System.out.println("4.View customer details");
			System.out.println("5.Go back to main menu");
			
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				addNewCustomer();
				break;
			case 2:
				updateCustomer();
				break;	
			case 3:
				deleteCustomer();
				break;
			case 4:
				viewCustomerdata();
				break;
			case 5:
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		private static void viewCustomerdata() {
			Scanner sc= new Scanner(System.in);
			Connection con =null;
			PreparedStatement ps5=null;
			ResultSet rs=null;
			String ViewData="SELECT * FROM customer WHERE AccNumber=?";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + "user=root&password=tiger");
				ps5=con.prepareStatement(ViewData);
				System.out.println("Enter the account number");
				int accno=sc.nextInt();
				ps5.setInt(1, accno);
				rs=ps5.executeQuery();
				
				if(rs.next())
				{
					String name=rs.getString(2);
					Long phone=rs.getLong(3);
					String mail=rs.getString(4);
					double bal=rs.getDouble(5);
					System.out.println("The accout holder name is"+name);
					System.out.println("The phone number is"+phone);
					System.out.println("The mail id is"+mail);
					System.out.println("The account balance is Rs."+bal);
					
				}
				else {
					System.out.println("Invalid account number");
					return;
				}
			} 
			catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			
			finally 
			{
				try
				{
					if(ps5!=null)ps5.close();
					if(con!=null)con.close();
				}
				catch(SQLException e )
				{
					e.printStackTrace();
				}
			}
		}
		private static void deleteCustomer() {
			Scanner sc= new Scanner(System.in);
			Connection con =null;
			PreparedStatement ps4=null;
			String Dquery="DELETE FROM customer WHERE AccNumber=?";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + "user=root&password=tiger");
				ps4=con.prepareStatement(Dquery);
				System.out.println("Enter the account number");
				int accno=sc.nextInt();
				ps4.setInt(1,accno);
				System.out.println("Do you like to delete the data of "+accno+"?");
				System.out.println("Press yes to delete.");
				System.out.println("Press any other key to main menu");
				String option=sc.next();
				int update=ps4.executeUpdate();
				if(option.equalsIgnoreCase("yes"))
				{
					ps4.executeUpdate();
					System.out.println("Data deleted successfully");
				}
				else {
					System.out.println("Returning to main menu");
				}
			} 
			catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			
			finally 
			{
				try
				{
					if(ps4!=null)ps4.close();
					if(con!=null)con.close();
				}
				catch(SQLException e )
				{
					e.printStackTrace();
				}
			}
		}
		private static void updateCustomer() {
			Scanner sc= new Scanner(System.in);
			Connection con =null;
			PreparedStatement ps3=null;
			String iQry="UPDATE customer SET CustomerName=?,PhoneNumber=?,Email=? WHERE AccNumber=?";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + "user=root&password=tiger");
				ps3=con.prepareStatement(iQry);
				System.out.println("Enter the name to be updates:");
				String name=sc.next();
				ps3.setString(1, name);
				System.out.println("Enter the phone number to be updated:");
				long phone=sc.nextLong();
				ps3.setLong(2, phone);
				System.out.println("Enter the email id to be updated:");
				String email=sc.next();
				ps3.setString(3, email);
				System.out.println("Enter the account number:");
				int accno=sc.nextInt();
				ps3.setInt(4,accno);
				int update= ps3.executeUpdate();
				if(update>0)
				{
					System.out.println("data updated successfully");
				}
				else
				{
					System.out.println("Failed to update the data");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block1
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					if(ps3!=null)ps3.close();
					if(con!=null)con.close();
				}
				catch(SQLException e )
				{
					e.printStackTrace();
				}
			}
			
		}
		private static void addNewCustomer() {
			Scanner sc= new Scanner(System.in);
			Connection con =null;
			PreparedStatement ps2=null;
			String iQry="INSERT INTO customer(CustomerName,PhoneNumber,Email)VALUES(?,?,?)";
			 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + "user=root&password=tiger");
				ps2=con.prepareStatement(iQry);
				System.out.println("Enter the cusotmer name:");
				String name=sc.next();
				ps2.setString(1, name);
				System.out.println("Enter the cusotmer phone number:");
				long phone=sc.nextLong();
				ps2.setLong(2, phone);
				System.out.println("Enter the cusotmer email id:");
				String email=sc.next();
				ps2.setString(3, email);
				System.out.println("Data added successfully");
				ps2.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			 finally 
				{
					try
					{
						if(ps2!=null)ps2.close();
						if(con!=null)con.close();
					}
					catch(SQLException e )
					{
						e.printStackTrace();
					}
				}
			
		}
		public static boolean loginValidation(int id, String pwd) {
			Scanner sc= new Scanner(System.in);
			Connection con =null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			String eVal="SELECT * FROM employee WHERE EID=? AND PASSWORD=?";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?" + "user=root&password=tiger");
				ps1=con.prepareStatement(eVal);
				ps1.setInt(1, id);
				ps1.setString(2, pwd);
				rs1=ps1.executeQuery();
				if(rs1.next())
				{
					String name=rs1.getString(3);
					System.out.println("welcome back" +name+",");
					return true;
				}
				else
				{
					System.out.println("Invalid employee id or password. please try again!!");
				}
			} catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}finally
			{
				try {
					if(rs1!=null)rs1.close();
					if(ps1!=null)ps1.close();
					if(con!=null)con.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			return false;
		}
	}
}
