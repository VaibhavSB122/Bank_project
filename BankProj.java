package Bankingproj;

	import java.util.*;

	public class BankProj {

		public static void main(String[] args) {
			int option=0;
			Scanner sc=new Scanner(System.in);
			
			do {
				System.out.println("select an option");
				System.out.println("1, Employee Interface");
				System.out.println("2, customer interface");
				System.out.println("3, exit");
				option =sc.nextInt();
				
				switch(option) {
				
				case 1: 
					EmployeeLogin.addCustomerData();
					break;
					
				case 2: 
					//CustomerInterface.showInterface();
					break;
					
				case 3: 
					System.out.println("exiting the program . goodbye!");
					break;
				default:
				System.out.println("Invalid choice. please select again.");
				}
			}while(option!= 3);
			sc.close();
		}

	}
}
